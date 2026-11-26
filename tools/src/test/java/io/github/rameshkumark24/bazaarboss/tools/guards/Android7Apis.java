package io.github.rameshkumark24.bazaarboss.tools.guards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EDGE-CASES SV-2, TRD §4.11: Java 8 APIs that reached Android only in API 26. {@code --release 8} allows
 * them (verified 2026-11-25), so they compile and then crash on Android 7. The modules here run on the phone;
 * the android module itself is checked by Lint's NewApi.
 */
final class Android7Apis {

    static final List<String> MODULES = Arrays.asList("logic", "core", "devtools");

    private static final Object[][] API_26 = {
        {Pattern.compile("\\bjava\\s*\\.\\s*nio\\s*\\.\\s*file\\b"), "java.nio.file"},
        {Pattern.compile("\\bjava\\s*\\.\\s*time\\b"), "java.time"},
        {Pattern.compile("\\bjava\\s*\\.\\s*util\\s*\\.\\s*Base64\\b|\\bBase64\\s*\\.\\s*get\\w*(?:En|De)coder\\b"),
            "java.util.Base64"},
        {Pattern.compile("\\bString\\s*\\.\\s*join\\s*\\("), "String.join"},
        {Pattern.compile("\\.\\s*toPath\\s*\\(\\s*\\)"), "File.toPath"},
    };

    private Android7Apis() {
    }

    static List<String> check(SourceFile file) {
        List<String> problems = new ArrayList<>();
        for (Object[] api : API_26) {
            Matcher m = ((Pattern) api[0]).matcher(file.code);
            while (m.find()) {
                problems.add(file.at(m.start(), api[1] + " is API 26: it compiles under --release 8, then crashes on"
                        + " Android 7 (SV-2; TRD §4.11)"));
            }
        }
        return problems;
    }
}
