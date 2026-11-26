package io.github.rameshkumark24.bazaarboss.tools.guards;

import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertNone;
import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertProblem;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/** LLD §9: a class past 500 lines fails the build. Tests count too: they're reviewed the same way. */
class FileSizeGuardTest {

    @Test
    void noClassIsOver500Lines() {
        List<SourceFile> files = Repo.sources(Repo.modules());
        Guards.assertScanned(files);
        List<String> problems = new ArrayList<>();
        for (SourceFile file : files) {
            problems.addAll(SizeRule.check(file));
        }
        assertNone(problems);
    }

    @Test
    void a501LineClassIsCaught() {
        assertProblem(SizeRule.check(lines(501)), "501 lines");
    }

    @Test
    void a500LineClassPasses() {
        assertNone(SizeRule.check(lines(500)));
    }

    private static SourceFile lines(int count) {
        String[] lines = new String[count];
        lines[0] = "final class Fixture {";
        for (int i = 1; i < count - 1; i++) {
            lines[i] = "    int f" + i + ";";
        }
        lines[count - 1] = "}";
        return SourceFile.fixture("logic/src/main/java/fixture/Fixture.java", lines);
    }
}
