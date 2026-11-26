package io.github.rameshkumark24.bazaarboss.tools.guards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LLD §2.3's dependency matrix, written as code: the one place the guard keeps it. Read it with LLD §2.2,
 * where an arrow means "depends on". A row changes in LLD §2.3 first, then here.
 *
 * <p>It reads every reference to the game's own packages (imports, static imports and fully qualified
 * names) in code with comments and strings blanked, plus libGDX references and the JDK calls LLD §2.1
 * forbids in {@code logic}.
 */
final class DependencyRules {

    static final String ROOT = "io.github.rameshkumark24.bazaarboss.";

    /** The domain features, in order: each may use only those to its left (LLD §2.2). */
    static final List<String> DOMAIN = Arrays.asList(
            "kernel", "content", "stock", "player", "haggle", "auction", "day", "records");
    /** The use cases and the data access they call: they may use anything in logic. */
    static final List<String> OUTER = Arrays.asList("game", "persist");
    /** Purchases and ads see only kernel and content, so nothing can depend on spend or ads (DR5, DR36). */
    static final List<String> WALLED = Arrays.asList("shop", "ads");
    /** The features that take a Clock. Haggles count rounds, and auctions are given elapsed time (DR12, DR25). */
    static final List<String> CLOCK_USERS = Arrays.asList("kernel", "game", "day", "records", "ads");
    /** Held to the screens' row: they reach logic only through what the game hands out. */
    static final List<String> OUTSIDE_LOGIC = Arrays.asList("core", "android", "lwjgl3", "devtools");
    /** The entities that live in game, never handed to a screen. */
    static final List<String> GAME_ENTITIES = Collections.singletonList("GameState");
    /** What modules outside logic may use from content and persist, by class (LLD §2.3, §3.1). */
    static final Map<String, List<String>> PORTS = new HashMap<>();

    static {
        PORTS.put("content", Arrays.asList("LineId", "Voice", "ContentPack"));
        PORTS.put("persist", Arrays.asList("LoadOutcome", "SaveFiles", "SaveLoader", "ContentPackReader"));
    }

    private static final Pattern REFERENCE = Pattern.compile(
            "\\bio\\.github\\.rameshkumark24\\.bazaarboss\\.([\\w$]+(?:\\.[\\w$]+)*(?:\\.\\*)?)");
    private static final Pattern PACKAGE_STATEMENT = Pattern.compile("\\bpackage\\s+[\\w.]+\\s*;");
    private static final Pattern LIBGDX = Pattern.compile("\\bcom\\.badlogic\\.gdx(?:\\.[\\w$]+)*(?:\\.\\*)?");
    private static final Pattern JSON_UTILITY = Pattern.compile(
            "com\\.badlogic\\.gdx\\.utils\\.(?:Json[\\w$]*|SerializationException)(?:\\.[\\w$]+)*");
    private static final Pattern ANDROID = Pattern.compile("(?<![\\w.])android\\.[a-z]");

    private static final String WALL_CLOCK = "reads the wall clock: take a Clock (DR25; LLD §2.1)";
    private static final String THREADS = "uses threads or timers: logic runs on its caller's thread, and time moves"
            + " only when the player acts (DR25; LLD §2.1)";
    private static final String FILES = "touches files: bytes leave logic through the SaveFiles and SaveSink ports"
            + " (LLD §2.1)";
    /** LLD §2.1: the domain may not use files, threads or the wall clock. Neither may game or persist. */
    private static final Object[][] LOGIC_FORBIDDEN = {
        {Pattern.compile("\\bSystem\\s*\\.\\s*(?:currentTimeMillis|nanoTime)\\b"), WALL_CLOCK},
        {Pattern.compile("\\bjava\\.util\\.(?:Date|Calendar|GregorianCalendar|TimeZone)\\b"), WALL_CLOCK},
        {Pattern.compile("\\bnew\\s+Date\\s*\\(|\\bCalendar\\s*\\.\\s*getInstance\\b|\\bTimeZone\\s*\\.\\s*getDefault\\b"),
            WALL_CLOCK},
        {Pattern.compile("\\bjava\\.util\\.(?:Timer|TimerTask)\\b|\\bjava\\.util\\.concurrent\\b|\\bThread\\b"), THREADS},
        {Pattern.compile("\\bjava\\.io\\.(?:File|FileInputStream|FileOutputStream|FileReader|FileWriter"
                + "|RandomAccessFile)\\b|\\bjava\\.nio\\.(?:file|channels)\\b"), FILES},
    };

    private DependencyRules() {
    }

    static List<String> check(SourceFile file) {
        if (file.module.equals("logic")) {
            return checkLogic(file);
        }
        if (OUTSIDE_LOGIC.contains(file.module)) {
            return checkOutsideLogic(file);
        }
        return Collections.emptyList(); // tools and sim: development only, so they may use anything (LLD §1)
    }

    private static List<String> checkLogic(SourceFile file) {
        List<String> problems = new ArrayList<>();
        String own = featureOf(file.packageName(), "logic");
        if (own.isEmpty()) {
            problems.add(file.at(0, "every logic class belongs to a feature package (LLD §1.1)"));
            return problems;
        }
        if (!known(own)) {
            problems.add(file.at(0, unknown(own)));
        }
        for (Ref ref : references(file)) {
            if (!ref.module.equals("logic")) {
                problems.add(file.at(ref.offset, "logic may not use the " + ref.module + " module (LLD §2.2)"));
                continue;
            }
            String why = logicMayUse(own, ref.feature);
            if (why != null) {
                problems.add(file.at(ref.offset, why));
            }
            if (ref.feature.equals("kernel") && ref.type.equals("Clock") && !CLOCK_USERS.contains(own)) {
                problems.add(file.at(ref.offset, own + " takes no Clock: only day, records and ads read the time,"
                        + " and haggles count rounds (DR12, DR25; LLD §2.3)"));
            }
        }
        Matcher gdx = LIBGDX.matcher(file.code);
        while (gdx.find()) {
            if (!own.equals("persist") || !JSON_UTILITY.matcher(gdx.group()).matches()) {
                problems.add(file.at(gdx.start(), "uses libGDX (" + gdx.group() + "): logic may use only its JSON"
                        + " utilities, named one by one, and only in persist (LLD §2.3)"));
            }
        }
        Matcher android = ANDROID.matcher(file.code);
        while (android.find()) {
            problems.add(file.at(android.start(), "uses Android: logic is plain Java (LLD §2.1)"));
        }
        for (Object[] rule : LOGIC_FORBIDDEN) {
            Matcher m = ((Pattern) rule[0]).matcher(file.code);
            while (m.find()) {
                problems.add(file.at(m.start(), (String) rule[1]));
            }
        }
        return problems;
    }

    /** Null if a logic feature may use another; otherwise why not. */
    private static String logicMayUse(String own, String target) {
        if (target.isEmpty() || !known(target)) {
            return unknown(target.isEmpty() ? "(the root package)" : target);
        }
        if (own.equals(target) || OUTER.contains(own) || !known(own)) {
            return null;
        }
        if (WALLED.contains(own)) {
            return target.equals("kernel") || target.equals("content")
                    ? null : own + " may use only kernel and content, not " + target + " (DR5, DR36; LLD §2.3)";
        }
        if (OUTER.contains(target) || WALLED.contains(target)) {
            return own + " may not use " + target + ": a domain feature never sees game, persist, shop or ads"
                    + " (LLD §2.3)";
        }
        return DOMAIN.indexOf(target) < DOMAIN.indexOf(own)
                ? null : own + " may not use " + target + ": a domain feature uses only those to its left,"
                        + " kernel ← content ← stock ← player ← haggle ← auction ← day ← records (LLD §2.2)";
    }

    private static List<String> checkOutsideLogic(SourceFile file) {
        List<String> problems = new ArrayList<>();
        for (Ref ref : references(file)) {
            if (!ref.module.equals("logic")) {
                continue;
            }
            String why = screensMayUse(ref);
            if (why != null) {
                problems.add(file.at(ref.offset, why));
            }
        }
        return problems;
    }

    /** Null if a module outside logic may use this logic class; otherwise why not (LLD §2.3, row 4). */
    private static String screensMayUse(Ref ref) {
        String type = ref.type;
        if (type.equals("*")) {
            return "imports logic." + ref.feature + ".*: name each class, so the guard can check it (LLD §2.3)";
        }
        if (type.endsWith("Rules") || type.endsWith("Factory")) {
            return "uses the rule class " + type + ": screens render views and never repeat a rule (LLD §2.4)";
        }
        if (ref.feature.equals("game")) {
            return GAME_ENTITIES.contains(type)
                    ? "uses the entity " + type + ": outside logic, code gets views, never entities (LLD §2.3)" : null;
        }
        if (ref.feature.equals("kernel") || type.endsWith("View") || type.endsWith("Result") || type.endsWith("Step")) {
            return null;
        }
        List<String> ports = PORTS.get(ref.feature);
        if (ports != null && ports.contains(type)) {
            return null;
        }
        return "uses logic." + ref.feature + "." + type + ": outside logic, only game, kernel, views (*View, *Result,"
                + " *Step) and the ports in DependencyRules.PORTS may be used. Never an entity (LLD §2.3)";
    }

    private static boolean known(String feature) {
        return DOMAIN.contains(feature) || OUTER.contains(feature) || WALLED.contains(feature);
    }

    private static String unknown(String feature) {
        return "logic." + feature + " isn't in LLD §2.3's matrix: add it there, and to DependencyRules, first";
    }

    /** The feature a package belongs to: the segment after the module, or "" if there's none. */
    private static String featureOf(String pkg, String module) {
        String prefix = ROOT + module;
        if (!pkg.startsWith(prefix + ".")) {
            return "";
        }
        String rest = pkg.substring(prefix.length() + 1);
        int dot = rest.indexOf('.');
        return dot < 0 ? rest : rest.substring(0, dot);
    }

    /** Every reference to the game's own packages outside the package statement. */
    private static List<Ref> references(SourceFile file) {
        Matcher statement = PACKAGE_STATEMENT.matcher(file.code);
        int skipFrom = -1;
        int skipTo = -1;
        if (statement.find()) {
            skipFrom = statement.start();
            skipTo = statement.end();
        }
        List<Ref> refs = new ArrayList<>();
        Matcher m = REFERENCE.matcher(file.code);
        while (m.find()) {
            if (m.start() >= skipFrom && m.start() < skipTo) {
                continue;
            }
            String[] parts = m.group(1).split("\\.");
            String feature = parts.length > 1 && Character.isLowerCase(parts[1].charAt(0)) ? parts[1] : "";
            String type = "";
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].equals("*") || Character.isUpperCase(parts[i].charAt(0))) {
                    type = parts[i];
                    break;
                }
            }
            refs.add(new Ref(parts[0], feature, type, m.start()));
        }
        return refs;
    }

    /** A reference: the module, the feature package and the top-level class it names. */
    private static final class Ref {
        final String module;
        final String feature;
        final String type;
        final int offset;

        Ref(String module, String feature, String type, int offset) {
            this.module = module;
            this.feature = feature;
            this.type = type;
            this.offset = offset;
        }
    }
}
