package io.github.rameshkumark24.bazaarboss.tools.guards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LLD §4.4. Every catch turns the error into a typed value, rethrows it, or (in the crash handler) records
 * it and passes it on. So a catch block is never empty, and a comment inside one doesn't count. Broad catches,
 * which would swallow bugs too, are allowed in four places only.
 */
final class CatchRules {

    /** LLD §4.4's four places, by file: the save's writer and loader, the crash handler, the hand-off adapters. */
    static final List<String> BROAD_ALLOWED = Arrays.asList(
            "core/src/main/java/io/github/rameshkumark24/bazaarboss/core/persist/SaveWriter.java",
            "logic/src/main/java/io/github/rameshkumark24/bazaarboss/logic/persist/SaveLoader.java",
            "android/src/main/java/io/github/rameshkumark24/bazaarboss/android/CrashHandler.java",
            "android/src/main/java/io/github/rameshkumark24/bazaarboss/android/AndroidPlatformServices.java");
    /** The catches that would swallow bugs too. */
    static final List<String> BROAD = Arrays.asList("Throwable", "Exception", "RuntimeException", "Error");

    private static final Pattern EMPTY = Pattern.compile("\\bcatch\\s*\\([^)]*\\)\\s*\\{[\\s;]*\\}");
    private static final Pattern CATCH = Pattern.compile(
            "\\bcatch\\s*\\(\\s*(?:final\\s+)?([\\w$.]+(?:\\s*\\|\\s*[\\w$.]+)*)\\s+[\\w$]+\\s*\\)");

    private CatchRules() {
    }

    static List<String> check(SourceFile file) {
        List<String> problems = new ArrayList<>();
        Matcher empty = EMPTY.matcher(file.code);
        while (empty.find()) {
            problems.add(file.at(empty.start(), "an empty catch block: turn the error into a typed value, or rethrow"
                    + " it. A comment doesn't count as handling (LLD §4.4)"));
        }
        if (BROAD_ALLOWED.contains(file.path)) {
            return problems;
        }
        Matcher c = CATCH.matcher(file.code);
        while (c.find()) {
            for (String type : c.group(1).split("\\|")) {
                String simple = type.trim().replaceFirst("^java\\.lang\\.", "");
                if (BROAD.contains(simple)) {
                    problems.add(file.at(c.start(), "catches " + simple + ", which swallows bugs too: catch the"
                            + " exception you expect. Broad catches belong only in SaveWriter, SaveLoader, the crash"
                            + " handler and the hand-off adapters (LLD §4.4)"));
                }
            }
        }
        return problems;
    }
}
