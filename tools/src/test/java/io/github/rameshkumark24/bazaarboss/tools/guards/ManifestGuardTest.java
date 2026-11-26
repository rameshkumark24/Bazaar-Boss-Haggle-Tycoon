package io.github.rameshkumark24.bazaarboss.tools.guards;

import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertNone;
import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertProblem;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * The merged-manifest guard's rules (TRD §4.11, BR-1), on fixtures shaped like the real merged manifest.
 * The real manifests are checked by {@code android}'s {@code check<Variant>MergedManifest} tasks.
 */
class ManifestGuardTest {

    private static final String DEV = "io.github.rameshkumark24.bazaarboss.dev";
    private static final String PLAY = "io.github.rameshkumark24.bazaarboss";
    private static final String LAUNCHER = activity("io.github.rameshkumark24.bazaarboss.android.AndroidLauncher",
            "android:exported=\"true\"", true);
    private static final String STARTUP = "<provider android:name=\"androidx.startup.InitializationProvider\""
            + " android:authorities=\"" + DEV + ".androidx-startup\" android:exported=\"false\"/>";
    private static final String PROFILE_INSTALLER = receiver("androidx.profileinstaller.ProfileInstallReceiver",
            "android:exported=\"true\" android:permission=\"android.permission.DUMP\"");

    @Test
    void todaysShapePasses() throws IOException {
        assertNone(check(DEV, uses(DEV + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION") + uses("android.permission.VIBRATE"),
                LAUNCHER + STARTUP + PROFILE_INSTALLER));
    }

    @Test
    void internetIsRefused() throws IOException {
        assertProblem(check(DEV, uses("android.permission.INTERNET"), LAUNCHER),
                "asks for android.permission.INTERNET, which is off the permission allowlist");
        assertProblem(check(DEV, "<uses-permission-sdk-23 android:name=\"android.permission.ACCESS_NETWORK_STATE\"/>", LAUNCHER),
                "asks for android.permission.ACCESS_NETWORK_STATE");
    }

    @Test
    void theAndroidXPermissionFollowsTheApplicationId() throws IOException {
        assertNone(check(PLAY, uses(PLAY + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION"), LAUNCHER));
        assertProblem(check(DEV, uses(PLAY + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION"), LAUNCHER),
                "asks for " + PLAY + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION");
    }

    @Test
    void anExportedComponentIsRefused() throws IOException {
        assertProblem(check(DEV, "", LAUNCHER + receiver("com.example.Leak", "android:exported=\"true\"")),
                "exports the receiver com.example.Leak");
        assertProblem(check(DEV, "", LAUNCHER + "<service android:name=\"com.example.Sync\" android:exported=\"true\"/>"),
                "exports the service com.example.Sync");
        assertProblem(check(DEV, "", LAUNCHER + activity("com.example.Share", "android:exported=\"true\"", false)),
                "exports the activity com.example.Share");
    }

    @Test
    void aFilterWithNoExportedFlagCountsAsExported() throws IOException {
        assertProblem(check(DEV, "", LAUNCHER + activity("com.example.Deep", "", true)), "exports the activity com.example.Deep");
        assertNone(check(DEV, "", LAUNCHER + activity("com.example.Inner", "", false)));
    }

    @Test
    void anExportFromAResourceCountsAsExported() throws IOException {
        assertProblem(check(DEV, "", LAUNCHER + receiver("com.example.Maybe", "android:exported=\"@bool/maybe\"")),
                "exports the receiver com.example.Maybe");
    }

    @Test
    void aProviderIsPrivateUnlessItSaysSo() throws IOException {
        assertNone(check(DEV, "", LAUNCHER + "<provider android:name=\"com.example.Files\" android:authorities=\"x\"/>"));
        assertProblem(check(DEV, "", LAUNCHER
                + "<provider android:name=\"com.example.Files\" android:authorities=\"x\" android:exported=\"true\"/>"),
                "exports the provider com.example.Files");
    }

    @Test
    void theProfileInstallerNeedsDump() throws IOException {
        assertProblem(check(DEV, "", LAUNCHER + receiver("androidx.profileinstaller.ProfileInstallReceiver",
                "android:exported=\"true\"")), "ProfileInstallReceiver is exported without DUMP");
    }

    private static List<String> check(String applicationId, String permissions, String components) throws IOException {
        String xml = "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\" package=\"" + applicationId + "\">\n"
                + permissions + "\n<application android:label=\"x\">\n" + components + "\n</application>\n</manifest>\n";
        return ManifestGuard.problems(ManifestGuard.parse(xml), applicationId);
    }

    private static String uses(String permission) {
        return "<uses-permission android:name=\"" + permission + "\"/>";
    }

    private static String activity(String name, String attributes, boolean filter) {
        return "<activity android:name=\"" + name + "\" " + attributes + ">"
                + (filter ? "<intent-filter><action android:name=\"android.intent.action.MAIN\"/></intent-filter>" : "")
                + "</activity>";
    }

    private static String receiver(String name, String attributes) {
        return "<receiver android:name=\"" + name + "\" " + attributes + ">"
                + "<intent-filter><action android:name=\"com.example.ACTION\"/></intent-filter></receiver>";
    }
}
