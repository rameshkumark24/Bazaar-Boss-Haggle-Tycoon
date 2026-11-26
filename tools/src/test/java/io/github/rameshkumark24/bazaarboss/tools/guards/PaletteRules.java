package io.github.rameshkumark24.bazaarboss.tools.guards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Colours come only from {@code docs/design/palette.py} (CLAUDE.md; LLD §9; TRD §4.11, L-8). Two checks:
 * {@code Palette} holds exactly what {@code palette.py --json} wrote, and no other code writes a colour.
 */
final class PaletteRules {

    static final String PALETTE_JSON = "docs/design/palette.json";
    static final String PALETTE_JAVA = "core/src/main/java/io/github/rameshkumark24/bazaarboss/core/ui/style/Palette.java";
    /** The modules that draw: everything that could write a colour. */
    static final List<String> MODULES = Arrays.asList("core", "devtools", "android", "lwjgl3");

    private static final Pattern JSON_ENTRY = Pattern.compile("\"([a-z0-9-]+)\"\\s*:\\s*\"#([0-9a-f]{6})\"");
    private static final Pattern JAVA_ENTRY = Pattern.compile(
            "public static final Color ([A-Z0-9_]+) = rgb\\(0x([0-9a-f]{6})\\); // ([a-z0-9-]+)");
    private static final Pattern JAVA_COLOUR_FIELD = Pattern.compile("\\bstatic\\s+final\\s+Color\\s+[A-Z0-9_]+");
    private static final String NUMBER = "\\s*[-+]?\\.?\\d";
    private static final String ONE = "\\s*1(?:\\.0*)?[fF]?\\s*";
    /** Each way code can write a colour of its own. White, as an untinted batch, is not a colour choice. */
    private static final Pattern[] LITERALS = {
        Pattern.compile("\\bnew\\s+Color\\s*\\(" + NUMBER),
        Pattern.compile("\\bColor\\s*\\.\\s*(?:valueOf|rgba8888ToColor|argb8888ToColor|rgb888ToColor"
                + "|rgba4444ToColor|rgb565ToColor|abgr8888ToColor)\\s*\\("),
        Pattern.compile("\\bColor\\s*\\.\\s*(?!WHITE\\b|WHITE_FLOAT_BITS\\b)[A-Z][A-Z0-9_]*\\b"),
        Pattern.compile("\\bScreenUtils\\s*\\.\\s*clear\\s*\\(" + NUMBER),
        Pattern.compile("\\bglClearColor\\s*\\(" + NUMBER),
        Pattern.compile("\\bsetColor\\s*\\((?!" + ONE + "," + ONE + "," + ONE + "[,)])" + NUMBER),
    };

    private PaletteRules() {
    }

    /** How many roles palette.json holds. */
    static int roles(String json) {
        return entries(json).size();
    }

    /** Palette.java against palette.json: every role, its constant's name and its value. */
    static List<String> compare(String json, String java) {
        List<String> problems = new ArrayList<>();
        Map<String, String> want = entries(json);
        Map<String, String> got = new LinkedHashMap<>();
        Matcher entry = JAVA_ENTRY.matcher(java);
        while (entry.find()) {
            String name = entry.group(1);
            String role = entry.group(3);
            got.put(role, entry.group(2));
            if (!name.equals(role.toUpperCase(Locale.ROOT).replace('-', '_'))) {
                problems.add("Palette." + name + " should be named for its role, " + role);
            }
        }
        for (Map.Entry<String, String> role : want.entrySet()) {
            String value = got.get(role.getKey());
            if (value == null) {
                problems.add("Palette is missing the role " + role.getKey() + ": run palette.py --json --java");
            } else if (!value.equals(role.getValue())) {
                problems.add("Palette's " + role.getKey() + " is #" + value + ", but palette.json says #"
                        + role.getValue() + ": run palette.py --json --java");
            }
        }
        for (String role : got.keySet()) {
            if (!want.containsKey(role)) {
                problems.add("Palette's " + role + " isn't in palette.json: change palette.py first");
            }
        }
        Matcher field = JAVA_COLOUR_FIELD.matcher(SourceFile.blank(java));
        int fields = 0;
        while (field.find()) {
            fields++;
        }
        if (fields != got.size()) {
            problems.add("Palette has " + fields + " colours, " + got.size() + " of them in palette.py's generated"
                    + " form: a colour that isn't in palette.json was added by hand");
        }
        return problems;
    }

    /** Colours written outside the palette. */
    static List<String> colourLiterals(SourceFile file) {
        List<String> problems = new ArrayList<>();
        if (file.path.equals(PALETTE_JAVA)) {
            return problems;
        }
        for (Pattern literal : LITERALS) {
            Matcher m = literal.matcher(file.code);
            while (m.find()) {
                problems.add(file.at(m.start(), "a colour written outside the palette (" + excerpt(file, m.start())
                        + "): use a Palette role, and add a new one to palette.py first (CLAUDE.md; DESIGN §2.3)"));
            }
        }
        return problems;
    }

    /** The code from an offset to the end of its line, at most 60 characters. */
    private static String excerpt(SourceFile file, int offset) {
        int end = file.text.indexOf('\n', offset);
        String rest = file.text.substring(offset, end < 0 ? file.text.length() : end).trim();
        return rest.length() > 60 ? rest.substring(0, 57) + "..." : rest;
    }

    private static Map<String, String> entries(String json) {
        Map<String, String> entries = new LinkedHashMap<>();
        Matcher m = JSON_ENTRY.matcher(json);
        while (m.find()) {
            entries.put(m.group(1), m.group(2));
        }
        return entries;
    }
}
