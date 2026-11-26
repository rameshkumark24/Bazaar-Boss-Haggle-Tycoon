package io.github.rameshkumark24.bazaarboss.tools.guards;

import java.util.Collections;
import java.util.List;

/** LLD §9: a class past 300 lines is split by responsibility; past 500, the build fails. */
final class SizeRule {

    static final int MAX_LINES = 500;

    private SizeRule() {
    }

    static List<String> check(SourceFile file) {
        int lines = file.lineCount();
        if (lines <= MAX_LINES) {
            return Collections.emptyList();
        }
        return Collections.singletonList(file.path + ": " + lines + " lines. A class over " + MAX_LINES
                + " fails the build: split it by responsibility (LLD §9)");
    }
}
