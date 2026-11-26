package io.github.rameshkumark24.bazaarboss.tools.guards;

import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertNone;
import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertProblem;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * EDGE-CASES SV-2, TRD §4.11: Java 8 APIs that Android 7 lacks. {@code --release 8} allows them, so they
 * compile and then crash on Android 7. The android module is left to Lint's NewApi check.
 */
class Android7ApiGuardTest {

    private static final String PATH = "core/src/main/java/fixture/Fixture.java";

    @Test
    void logicCoreAndDevtoolsUseNoApi26() {
        List<SourceFile> files = Repo.productionSources(Android7Apis.MODULES);
        Guards.assertScanned(files);
        List<String> problems = new ArrayList<>();
        for (SourceFile file : files) {
            problems.addAll(Android7Apis.check(file));
        }
        assertNone(problems);
    }

    @Test
    void eachApi26CallIsCaught() {
        assertProblem(check("import java.nio.file.Files;"), "java.nio.file is API 26");
        assertProblem(check("import java.time.Instant;"), "java.time is API 26");
        assertProblem(check("import java.util.Base64;"), "java.util.Base64 is API 26");
        assertProblem(check("final class F { Object e = Base64.getEncoder(); }"), "java.util.Base64 is API 26");
        assertProblem(check("final class F { String s = String.join(\",\", list); }"), "String.join is API 26");
        assertProblem(check("final class F { Object p = file.toPath(); }"), "File.toPath is API 26");
        assertProblem(check("final class F { long t = java.time.Instant.now().toEpochMilli(); }"), "java.time is API 26");
    }

    @Test
    void mentioningThemInACommentOrAStringIsFine() {
        assertNone(check("// String.join is API 26; java.time too", "final class F { String s = \"java.nio.file\"; }"));
        assertNone(check("import com.badlogic.gdx.utils.Base64Coder;", "final class F { String s = Base64Coder.encodeString(x); }"));
    }

    private static List<String> check(String... lines) {
        return Android7Apis.check(SourceFile.fixture(PATH, lines));
    }
}
