package io.github.rameshkumark24.bazaarboss.tools.guards;

import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertNone;
import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertProblem;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * LLD §9, TRD §4.11 (L-8): {@code Palette} is exactly what {@code palette.py --json} wrote, and no colour
 * is written anywhere else (CLAUDE.md: colours come only from palette.py).
 */
class PaletteGuardTest {

    private static final String JSON = "{\n  \"surface\": \"#1b2232\",\n  \"text-muted\": \"#c0c6d3\"\n}\n";
    private static final String PATH = "core/src/main/java/io/github/rameshkumark24/bazaarboss/core/app/Fixture.java";

    @Test
    void paletteJavaMatchesPaletteJson() {
        String json = Repo.read(Repo.root().resolve(PaletteRules.PALETTE_JSON));
        String java = Repo.read(Repo.root().resolve(PaletteRules.PALETTE_JAVA));
        assertTrue(PaletteRules.roles(json) >= 20, "palette.json should hold DESIGN §2.3's roles");
        assertNone(PaletteRules.compare(json, java));
    }

    @Test
    void theGeneratedFormPasses() {
        assertNone(PaletteRules.compare(JSON, java(
                "public static final Color SURFACE = rgb(0x1b2232); // surface",
                "public static final Color TEXT_MUTED = rgb(0xc0c6d3); // text-muted")));
    }

    @Test
    void aChangedValueIsCaught() {
        assertProblem(PaletteRules.compare(JSON, java(
                "public static final Color SURFACE = rgb(0x1b2233); // surface",
                "public static final Color TEXT_MUTED = rgb(0xc0c6d3); // text-muted")), "surface");
    }

    @Test
    void aMissingRoleIsCaught() {
        assertProblem(PaletteRules.compare(JSON, java(
                "public static final Color SURFACE = rgb(0x1b2232); // surface")), "text-muted");
    }

    @Test
    void aHandAddedColourIsCaught() {
        assertProblem(PaletteRules.compare(JSON, java(
                "public static final Color SURFACE = rgb(0x1b2232); // surface",
                "public static final Color TEXT_MUTED = rgb(0xc0c6d3); // text-muted",
                "public static final Color HIGHLIGHT = new Color(1f, 0.8f, 0f, 1f);")), "isn't in palette.json");
        assertProblem(PaletteRules.compare(JSON, java(
                "public static final Color SURFACE = rgb(0x1b2232); // surface",
                "public static final Color TEXT_MUTED = rgb(0xc0c6d3); // text-muted",
                "public static final Color GOLD = rgb(0xffd700); // gold")), "gold");
    }

    @Test
    void aNameThatDoesntMatchItsRoleIsCaught() {
        assertProblem(PaletteRules.compare(JSON, java(
                "public static final Color BACKGROUND = rgb(0x1b2232); // surface",
                "public static final Color TEXT_MUTED = rgb(0xc0c6d3); // text-muted")), "BACKGROUND");
    }

    @Test
    void noColourIsWrittenOutsideThePalette() {
        List<SourceFile> files = Repo.productionSources(PaletteRules.MODULES);
        Guards.assertScanned(files);
        List<String> problems = new ArrayList<>();
        for (SourceFile file : files) {
            problems.addAll(PaletteRules.colourLiterals(file));
        }
        assertNone(problems);
    }

    @Test
    void eachColourLiteralIsCaught() {
        assertProblem(literals("Color c = new Color(1f, 0.8f, 0f, 1f);"), "a colour written outside the palette");
        assertProblem(literals("Color c = new Color(.5f, .5f, .5f, 1);"), "a colour written outside the palette");
        assertProblem(literals("Color c = new Color(0xffcc00ff);"), "a colour written outside the palette");
        assertProblem(literals("Color c = Color.valueOf(\"ffcc00\");"), "a colour written outside the palette");
        assertProblem(literals("Color c = Color.GOLD;"), "a colour written outside the palette");
        assertProblem(literals("ScreenUtils.clear(0.1f, 0.1f, 0.2f, 1f);"), "a colour written outside the palette");
        assertProblem(literals("Gdx.gl.glClearColor(0, 0, 0, 1);"), "a colour written outside the palette");
        assertProblem(literals("font.setColor(1, 0, 0, 1);"), "a colour written outside the palette");
        assertProblem(literals("batch.setColor(0f, 0f, 0f, 0.5f);"), "a colour written outside the palette");
    }

    @Test
    void copiesWhiteAndTheUntintedBatchAreFine() {
        assertNone(literals("Color c = new Color(Palette.TEXT);"));
        assertNone(literals("Color scratch = new Color();"));
        assertNone(literals("ScreenUtils.clear(Palette.SURFACE);"));
        assertNone(literals("batch.setColor(Color.WHITE);"));
        assertNone(literals("batch.setColor(1f, 1f, 1f, alpha);"));
        assertNone(literals("batch.setPackedColor(Color.WHITE_FLOAT_BITS);"));
        assertNone(literals("// new Color(1f, 0f, 0f, 1f) would be wrong here"));
    }

    @Test
    void thePaletteItselfIsExempt() {
        assertNone(PaletteRules.colourLiterals(SourceFile.fixture(PaletteRules.PALETTE_JAVA,
                "final class Palette { static Color rgb(int v) { return new Color((v << 8) | 0xff); } }")));
    }

    private static List<String> literals(String line) {
        return PaletteRules.colourLiterals(SourceFile.fixture(PATH,
                "package fixture;", "final class Fixture {", "  void m() {", "    " + line, "  }", "}"));
    }

    private static String java(String... fields) {
        StringBuilder java = new StringBuilder("package io.github.rameshkumark24.bazaarboss.core.ui.style;\n\n");
        java.append("public final class Palette {\n\n");
        for (String field : fields) {
            java.append("    ").append(field).append('\n');
        }
        java.append("\n    private static Color rgb(int rgb) {\n        return new Color((rgb << 8) | 0xff);\n    }\n}\n");
        return java.toString();
    }
}
