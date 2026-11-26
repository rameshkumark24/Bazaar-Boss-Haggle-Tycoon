package io.github.rameshkumark24.bazaarboss.tools.guards;

import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertNone;
import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertProblem;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/** LLD §4.4: no empty catch blocks, and broad catches in four places only. */
class CatchGuardTest {

    private static final String ELSEWHERE = "core/src/main/java/io/github/rameshkumark24/bazaarboss/core/app/Fixture.java";

    @Test
    void theRepositoryHasNoEmptyOrBroadCatches() {
        List<SourceFile> files = Repo.productionSources(Repo.modules());
        Guards.assertScanned(files);
        List<String> problems = new ArrayList<>();
        for (SourceFile file : files) {
            problems.addAll(CatchRules.check(file));
        }
        assertNone(problems);
    }

    @Test
    void anEmptyCatchIsCaught() {
        assertProblem(check(ELSEWHERE, "try { f(); } catch (IOException e) { }"), "an empty catch block");
        assertProblem(check(ELSEWHERE, "try { f(); } catch (IOException e) {}"), "an empty catch block");
        assertProblem(check(ELSEWHERE, "try { f(); } catch (IOException e) { ; }"), "an empty catch block");
    }

    @Test
    void aCommentDoesntCountAsHandling() {
        assertProblem(check(ELSEWHERE, "try { f(); } catch (IOException e) { /* can't happen */ }"), "an empty catch block");
        assertProblem(check(ELSEWHERE, "try { f(); } catch (IOException e) {", "    // ignored", "}"), "an empty catch block");
    }

    @Test
    void aBroadCatchIsCaughtOutsideTheFourPlaces() {
        for (String broad : new String[] {"Exception", "Throwable", "RuntimeException", "Error", "java.lang.Exception"}) {
            assertProblem(check(ELSEWHERE, "try { f(); } catch (" + broad + " e) { throw new IllegalStateException(e); }"),
                    "catches " + broad.replace("java.lang.", ""));
        }
        assertProblem(check(ELSEWHERE, "try { f(); } catch (final Exception e) { log(e); }"), "catches Exception");
    }

    @Test
    void aMultiCatchWithABroadTypeIsBroad() {
        assertProblem(check(ELSEWHERE, "try { f(); } catch (IOException | RuntimeException e) { log(e); }"),
                "catches RuntimeException");
    }

    @Test
    void theFourPlacesMayCatchBroadly() {
        for (String place : CatchRules.BROAD_ALLOWED) {
            assertNone(check(place, "try { f(); } catch (Exception e) { return SaveProblem.from(e); }"));
        }
    }

    @Test
    void theFourPlacesStillMayNotCatchAndIgnore() {
        assertProblem(check(CatchRules.BROAD_ALLOWED.get(0), "try { f(); } catch (Throwable t) { }"), "an empty catch block");
    }

    @Test
    void aNarrowCatchThatHandlesPasses() {
        assertNone(check(ELSEWHERE, "try { f(); } catch (IOException e) { return Problem.IO; }"));
        assertNone(check(ELSEWHERE, "try { f(); } catch (ActivityNotFoundException e) { return HandOff.NO_APP; }"));
        assertNone(check(ELSEWHERE, "String s = \"catch (Exception e) { }\";"));
    }

    private static List<String> check(String path, String... body) {
        List<String> lines = new ArrayList<>();
        lines.add("package fixture;");
        lines.add("final class Fixture {");
        lines.add("  Object m() {");
        for (String line : body) {
            lines.add("    " + line);
        }
        lines.add("    return null;");
        lines.add("  }");
        lines.add("}");
        return CatchRules.check(SourceFile.fixture(path, lines.toArray(new String[0])));
    }
}
