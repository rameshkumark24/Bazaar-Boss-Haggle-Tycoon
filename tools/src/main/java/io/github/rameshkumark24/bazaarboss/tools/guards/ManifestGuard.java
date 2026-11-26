package io.github.rameshkumark24.bazaarboss.tools.guards;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The merged-manifest guard (TRD §4.11; EDGE-CASES BR-1), Phase 6's part: the permission allowlist and the
 * exports allowlist. {@code android}'s {@code check<Variant>MergedManifest} tasks run it on each variant's merged
 * manifest, so a library that merges in a permission or an exported component fails the build (IDEA H4-1).
 * Phase 25 adds the rest of TRD §4.11's list.
 */
public final class ManifestGuard {

    static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";
    /** The code's package. The applicationId differs: debug builds add ".dev" (BR-5). */
    static final String NAMESPACE = "io.github.rameshkumark24.bazaarboss";
    static final String LAUNCHER = NAMESPACE + ".android.AndroidLauncher";
    /** AndroidX's, exported but reachable only by callers holding the system's DUMP permission (TD-30). */
    static final String PROFILE_INSTALL_RECEIVER = "androidx.profileinstaller.ProfileInstallReceiver";
    static final String DUMP = "android.permission.DUMP";

    private static final List<String> PERMISSION_REQUESTS = Arrays.asList(
            "uses-permission", "uses-permission-sdk-23", "uses-permission-sdk-m");
    private static final List<String> COMPONENTS = Arrays.asList(
            "activity", "activity-alias", "service", "receiver", "provider");

    private ManifestGuard() {
    }

    /** Args: the variant's name, its applicationId, and its merged manifest. Exits with 1 on any problem. */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("usage: ManifestGuard <variant> <applicationId> <merged manifest>");
        }
        List<String> problems = problems(parse(new File(args[2])), args[1]);
        if (problems.isEmpty()) {
            System.out.println("ManifestGuard: the " + args[0] + " manifest passes: its permissions and exports"
                    + " are on the allowlists (TRD §4.11)");
            return;
        }
        for (String problem : problems) {
            System.err.println("ManifestGuard, " + args[0] + ": " + problem);
        }
        System.exit(1);
    }

    /**
     * The permissions v1 may ask for (PRD CR13; TRD §4.11): VIBRATE for the haptics (F14), and the signature-level
     * permission AndroidX Core declares for the app's own receivers, which grants nothing (TD-24). It's named
     * after the applicationId, so it differs between debug and release.
     */
    static Set<String> allowedPermissions(String applicationId) {
        return new HashSet<>(Arrays.asList(
                "android.permission.VIBRATE", applicationId + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION"));
    }

    public static List<String> problems(Document manifest, String applicationId) {
        List<String> problems = new ArrayList<>();
        Set<String> allowed = allowedPermissions(applicationId);
        for (String tag : PERMISSION_REQUESTS) {
            NodeList requests = manifest.getElementsByTagName(tag);
            for (int i = 0; i < requests.getLength(); i++) {
                String name = android((Element) requests.item(i), "name");
                if (!allowed.contains(name)) {
                    problems.add("asks for " + name + ", which is off the permission allowlist."
                            + " Change PRD §8.5's data table first (CR13; TRD §4.11)");
                }
            }
        }
        for (String tag : COMPONENTS) {
            NodeList components = manifest.getElementsByTagName(tag);
            for (int i = 0; i < components.getLength(); i++) {
                Element component = (Element) components.item(i);
                if (!isExported(component)) {
                    continue;
                }
                String name = qualified(android(component, "name"));
                if (tag.equals("activity") && name.equals(LAUNCHER)) {
                    continue;
                }
                if (tag.equals("receiver") && name.equals(PROFILE_INSTALL_RECEIVER)) {
                    if (!DUMP.equals(android(component, "permission"))) {
                        problems.add("ProfileInstallReceiver is exported without DUMP, so any app could reach it"
                                + " (TRD §4.7; BR-1)");
                    }
                    continue;
                }
                problems.add("exports the " + tag + " " + name + ". Only the launcher, and AndroidX's"
                        + " ProfileInstallReceiver behind DUMP, may be exported (TRD §4.7; BR-1)");
            }
        }
        return problems;
    }

    /**
     * Whether other apps can reach a component. Only an explicit "false" counts as private: a value from a
     * resource can't be judged here. With no flag, a filter means exported, except for a provider, which has
     * been private by default since API 17 (minSdk is 24).
     */
    static boolean isExported(Element component) {
        String exported = android(component, "exported");
        if (!exported.isEmpty()) {
            return !exported.equals("false");
        }
        if (component.getTagName().equals("provider")) {
            return false;
        }
        return component.getElementsByTagName("intent-filter").getLength() > 0;
    }

    public static Document parse(File manifest) throws IOException {
        return parse(new InputSource(manifest.toURI().toString()), manifest.getPath());
    }

    public static Document parse(String xml) throws IOException {
        return parse(new InputSource(new StringReader(xml)), "the manifest");
    }

    private static Document parse(InputSource source, String what) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            return factory.newDocumentBuilder().parse(source);
        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException("Couldn't read " + what + ": " + e.getMessage(), e);
        }
    }

    private static String android(Element element, String attribute) {
        return element.getAttributeNS(ANDROID_NS, attribute);
    }

    /** A class name in full: the merger writes them so, but a relative one is resolved against the namespace. */
    private static String qualified(String name) {
        if (name.startsWith(".")) {
            return NAMESPACE + name;
        }
        return name.contains(".") ? name : NAMESPACE + "." + name;
    }
}
