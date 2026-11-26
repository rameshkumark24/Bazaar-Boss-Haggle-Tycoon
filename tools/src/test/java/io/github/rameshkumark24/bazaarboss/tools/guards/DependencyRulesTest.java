package io.github.rameshkumark24.bazaarboss.tools.guards;

import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertNone;
import static io.github.rameshkumark24.bazaarboss.tools.guards.Guards.assertProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * LLD §2.3's dependency matrix, held over the whole repository (TRD §4.11). The fixtures prove each rule
 * bites: a guard that never fails would pass the repository too.
 */
class DependencyRulesTest {

    private static final String ROOT = "io.github.rameshkumark24.bazaarboss.";

    @Test
    void theRepositoryFollowsTheMatrix() {
        List<SourceFile> files = Repo.productionSources(Repo.modules());
        Guards.assertScanned(files);
        List<String> problems = new ArrayList<>();
        for (SourceFile file : files) {
            problems.addAll(DependencyRules.check(file));
        }
        assertNone(problems);
    }

    @Test
    void aDomainFeatureUsesOnlyThoseToItsLeft() {
        assertProblem(check(logic("haggle", "import " + ROOT + "logic.auction.Lot;")), "haggle may not use auction");
        assertProblem(check(logic("stock", "import " + ROOT + "logic.player.Wallet;")), "stock may not use player");
        assertNone(check(logic("haggle",
                "import " + ROOT + "logic.player.Wallet;",
                "import " + ROOT + "logic.stock.OwnedItem;",
                "import " + ROOT + "logic.kernel.Money;")));
    }

    @Test
    void aDomainFeatureNeverSeesGamePersistShopOrAds() {
        for (String outer : Arrays.asList("game", "persist", "shop", "ads")) {
            assertProblem(check(logic("records", "import " + ROOT + "logic." + outer + ".Anything;")),
                    "records may not use " + outer);
        }
    }

    @Test
    void shopAndAdsUseOnlyKernelAndContent() {
        assertProblem(check(logic("shop", "import " + ROOT + "logic.player.Wallet;")), "shop may use only kernel and content");
        assertProblem(check(logic("ads", "import " + ROOT + "logic.haggle.Visit;")), "ads may use only kernel and content");
        assertNone(check(logic("shop", "import " + ROOT + "logic.content.ItemDef;", "import " + ROOT + "logic.kernel.Ids;")));
    }

    @Test
    void gameAndPersistMayUseAnythingInLogic() {
        assertNone(check(logic("game", "import " + ROOT + "logic.records.Stats;", "import " + ROOT + "logic.persist.SaveCodec;")));
        assertNone(check(logic("persist", "import " + ROOT + "logic.game.GameState;", "import " + ROOT + "logic.haggle.Visit;")));
    }

    @Test
    void aFullyQualifiedNameCountsAsAnImport() {
        assertProblem(check(logic("player", "final class F { " + ROOT + "logic.records.Stats stats; }")),
                "player may not use records");
        assertProblem(check(logic("haggle", "import static " + ROOT + "logic.day.Specials.availability;")),
                "haggle may not use day");
    }

    @Test
    void commentsAndStringsDontCount() {
        assertNone(check(logic("haggle",
                "// import " + ROOT + "logic.auction.Lot;",
                "final class F { String s = \"" + ROOT + "logic.auction.Lot\"; }")));
    }

    @Test
    void aFeatureMissingFromTheMatrixIsCaught() {
        assertProblem(check(logic("lottery", "final class F { }")), "isn't in LLD §2.3's matrix");
        assertProblem(check(logic("haggle", "import " + ROOT + "logic.lottery.Ticket;")), "isn't in LLD §2.3's matrix");
        assertProblem(check(file("logic", ROOT + "logic", "final class F { }")), "belongs to a feature package");
    }

    @Test
    void logicUsesLibGdxOnlyForJsonInsidePersist() {
        assertNone(check(logic("persist",
                "import com.badlogic.gdx.utils.JsonValue;",
                "import com.badlogic.gdx.utils.JsonWriter.OutputType;",
                "import com.badlogic.gdx.utils.SerializationException;")));
        assertProblem(check(logic("haggle", "import com.badlogic.gdx.utils.JsonValue;")), "uses libGDX");
        assertProblem(check(logic("persist", "import com.badlogic.gdx.math.MathUtils;")), "uses libGDX");
        assertProblem(check(logic("persist", "import com.badlogic.gdx.utils.*;")), "uses libGDX");
    }

    @Test
    void onlyDayRecordsAndAdsTakeAClock() {
        assertProblem(check(logic("haggle", "import " + ROOT + "logic.kernel.Clock;")), "haggle takes no Clock");
        assertProblem(check(logic("auction", "import " + ROOT + "logic.kernel.Clock;")), "auction takes no Clock");
        for (String user : Arrays.asList("day", "records", "ads", "game")) {
            assertNone(check(logic(user, "import " + ROOT + "logic.kernel.Clock;")));
        }
    }

    @Test
    void logicNeverReadsTheWallClockStartsAThreadOrTouchesAFile() {
        assertProblem(check(logic("day", "final class F { long t = System.currentTimeMillis(); }")), "wall clock");
        assertProblem(check(logic("records", "final class F { long t = System.nanoTime(); }")), "wall clock");
        assertProblem(check(logic("records", "import java.util.Calendar;")), "wall clock");
        assertProblem(check(logic("game", "final class F { void f() { new Thread(() -> { }).start(); } }")), "threads");
        assertProblem(check(logic("game", "import java.util.concurrent.Executors;")), "threads");
        assertProblem(check(logic("persist", "import java.io.FileOutputStream;")), "files");
        assertNone(check(logic("persist", "import java.io.IOException;", "import java.io.FileNotFoundException;")));
    }

    @Test
    void screensUseGameKernelViewsAndThePortsOnly() {
        assertNone(check(core("haggle",
                "import " + ROOT + "logic.game.Game;",
                "import " + ROOT + "logic.game.TurnRef;",
                "import " + ROOT + "logic.haggle.HaggleView;",
                "import " + ROOT + "logic.haggle.DealResult;",
                "import " + ROOT + "logic.haggle.HaggleStep;",
                "import " + ROOT + "logic.kernel.Outcome;",
                "import " + ROOT + "logic.content.Voice;",
                "import " + ROOT + "logic.persist.LoadOutcome;",
                "import " + ROOT + "logic.persist.SaveFiles.Slot;")));
        assertProblem(check(core("haggle", "import " + ROOT + "logic.haggle.Visit;")), "logic.haggle.Visit");
        assertProblem(check(core("haggle", "import " + ROOT + "logic.haggle.HaggleRules;")), "the rule class HaggleRules");
        assertProblem(check(core("haggle", "import " + ROOT + "logic.haggle.VisitFactory;")), "the rule class VisitFactory");
        assertProblem(check(core("app", "import " + ROOT + "logic.game.GameState;")), "the entity GameState");
        assertProblem(check(core("app", "import " + ROOT + "logic.haggle.*;")), "name each class");
    }

    @Test
    void theOtherModulesOutsideLogicAreHeldToTheScreensRow() {
        assertProblem(check(file("android", ROOT + "android", "import " + ROOT + "logic.stock.OwnedItem;")),
                "logic.stock.OwnedItem");
        assertProblem(check(file("devtools", ROOT + "devtools", "import " + ROOT + "logic.player.Wallet;")),
                "logic.player.Wallet");
    }

    @Test
    void toolsAndTheSimulatorMayUseAnything() {
        assertNone(check(file("tools", ROOT + "tools", "import " + ROOT + "logic.haggle.HaggleRules;")));
        assertNone(check(file("sim", ROOT + "sim", "import " + ROOT + "logic.game.GameState;")));
    }

    private static List<String> check(SourceFile file) {
        return DependencyRules.check(file);
    }

    private static SourceFile logic(String feature, String... body) {
        return file("logic", ROOT + "logic." + feature, body);
    }

    private static SourceFile core(String feature, String... body) {
        return file("core", ROOT + "core." + feature, body);
    }

    private static SourceFile file(String module, String pkg, String... body) {
        List<String> lines = new ArrayList<>();
        lines.add("package " + pkg + ";");
        lines.add("");
        lines.addAll(Arrays.asList(body));
        String path = module + "/src/main/java/" + pkg.replace('.', '/') + "/Fixture.java";
        return SourceFile.fixture(path, lines.toArray(new String[0]));
    }
}
