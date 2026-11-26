package io.github.rameshkumark24.bazaarboss.tools.guards;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** One Java file as the guards see it: its text, and its code with comments and literals blanked out. */
final class SourceFile {

    private static final Pattern PACKAGE = Pattern.compile("\\bpackage\\s+([\\w.]+)\\s*;");

    /** Relative to the repository's root, with forward slashes. */
    final String path;
    /** The Gradle module: logic, core, android and so on. */
    final String module;
    /** The source set: main, test, debug, release. */
    final String sourceSet;
    final String text;
    /**
     * The text with comments, strings and character literals blanked to spaces. It keeps the length and
     * the line breaks, so an offset in it is an offset in the text, and a comment never counts as code.
     */
    final String code;

    SourceFile(String path, String module, String sourceSet, String text) {
        this.path = path;
        this.module = module;
        this.sourceSet = sourceSet;
        this.text = text;
        this.code = blank(text);
    }

    /** A fixture: a file in a module's main source set, made of the given lines. */
    static SourceFile fixture(String path, String... lines) {
        String module = path.substring(0, path.indexOf('/'));
        return new SourceFile(path, module, "main", String.join("\n", lines) + "\n");
    }

    /** The package it declares, or "" if none. */
    String packageName() {
        Matcher m = PACKAGE.matcher(code);
        return m.find() ? m.group(1) : "";
    }

    /** The 1-based line an offset falls on. */
    int lineOf(int offset) {
        int line = 1;
        for (int i = 0; i < offset && i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                line++;
            }
        }
        return line;
    }

    int lineCount() {
        if (text.isEmpty()) {
            return 0;
        }
        int lines = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                lines++;
            }
        }
        return text.charAt(text.length() - 1) == '\n' ? lines : lines + 1;
    }

    /** A problem at an offset, written "path:line: message". */
    String at(int offset, String message) {
        return path + ":" + lineOf(offset) + ": " + message;
    }

    /** Blanks comments, strings (text blocks included) and character literals, keeping quotes and line breaks. */
    static String blank(String text) {
        StringBuilder out = new StringBuilder(text.length());
        int i = 0;
        int n = text.length();
        while (i < n) {
            char c = text.charAt(i);
            if (text.startsWith("//", i)) {
                while (i < n && text.charAt(i) != '\n') {
                    out.append(' ');
                    i++;
                }
            } else if (text.startsWith("/*", i)) {
                int end = text.indexOf("*/", i + 2);
                end = end < 0 ? n : end + 2;
                for (; i < end; i++) {
                    out.append(text.charAt(i) == '\n' ? '\n' : ' ');
                }
            } else if (text.startsWith("\"\"\"", i)) {
                out.append("\"\"\"");
                i += 3;
                while (i < n && !text.startsWith("\"\"\"", i)) {
                    int step = text.charAt(i) == '\\' && i + 1 < n ? 2 : 1;
                    for (int k = 0; k < step; k++, i++) {
                        out.append(text.charAt(i) == '\n' ? '\n' : ' ');
                    }
                }
                if (i < n) {
                    out.append("\"\"\"");
                    i += 3;
                }
            } else if (c == '"' || c == '\'') {
                out.append(c);
                i++;
                while (i < n && text.charAt(i) != c && text.charAt(i) != '\n') {
                    int step = text.charAt(i) == '\\' && i + 1 < n ? 2 : 1;
                    for (int k = 0; k < step; k++, i++) {
                        out.append(' ');
                    }
                }
                if (i < n && text.charAt(i) == c) {
                    out.append(c);
                    i++;
                }
            } else {
                out.append(c);
                i++;
            }
        }
        return out.toString();
    }
}
