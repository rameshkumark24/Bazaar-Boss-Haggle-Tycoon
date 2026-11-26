package io.github.rameshkumark24.bazaarboss.tools.guards;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

/** The guards' shared assertions. */
final class Guards {

    /** A file every scan of the repository must see, so a wrong root can't pass by checking nothing. */
    static final String KNOWN_FILE = "core/src/main/java/io/github/rameshkumark24/bazaarboss/core/app/BazaarBossGame.java";

    private Guards() {
    }

    static void assertNone(List<String> problems) {
        assertTrue(problems.isEmpty(), () -> problems.size() + " problem(s):\n" + String.join("\n", problems));
    }

    static void assertProblem(List<String> problems, String expected) {
        assertTrue(problems.stream().anyMatch(p -> p.contains(expected)),
                () -> "expected a problem containing \"" + expected + "\", got " + problems);
    }

    /** The scan saw the repository: at least the known file. */
    static void assertScanned(List<SourceFile> files) {
        assertTrue(files.stream().anyMatch(f -> f.path.equals(KNOWN_FILE)),
                () -> "the scan didn't see " + KNOWN_FILE + "; it saw " + files.size() + " files");
    }
}
