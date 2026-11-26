package io.github.rameshkumark24.bazaarboss.tools.guards;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** The repository, as the guards read it: the modules settings.gradle includes, and their Java files. */
final class Repo {

    private static final Pattern INCLUDED = Pattern.compile("'([\\w-]+)'");

    private Repo() {
    }

    /** The repository's root, which tools/build.gradle passes in. */
    static Path root() {
        String root = System.getProperty("repo.root");
        if (root == null) {
            throw new IllegalStateException("repo.root isn't set: run the guards through Gradle (tools/build.gradle)");
        }
        return Paths.get(root);
    }

    static String read(Path file) {
        try {
            return new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** The modules settings.gradle includes, in its order. */
    static List<String> modules() {
        List<String> modules = new ArrayList<>();
        for (String line : read(root().resolve("settings.gradle")).split("\n")) {
            if (line.trim().startsWith("include")) {
                Matcher m = INCLUDED.matcher(line);
                while (m.find()) {
                    modules.add(m.group(1));
                }
            }
        }
        if (modules.isEmpty()) {
            throw new IllegalStateException("settings.gradle includes no modules, so the guards would check nothing");
        }
        return modules;
    }

    /** Every Java file in every source set of the given modules, sorted by path. */
    static List<SourceFile> sources(Collection<String> modules) {
        List<SourceFile> files = new ArrayList<>();
        for (String module : modules) {
            Path src = root().resolve(module).resolve("src");
            if (!Files.isDirectory(src)) {
                continue;
            }
            for (Path set : list(src)) {
                Path java = set.resolve("java");
                if (!Files.isDirectory(java)) {
                    continue;
                }
                for (Path file : walk(java)) {
                    String path = root().relativize(file).toString().replace('\\', '/');
                    files.add(new SourceFile(path, module, set.getFileName().toString(), read(file)));
                }
            }
        }
        files.sort(Comparator.comparing(f -> f.path));
        return files;
    }

    /** What ships or runs on a device or a desktop: every source set except the tests. */
    static List<SourceFile> productionSources(Collection<String> modules) {
        return sources(modules).stream()
                .filter(f -> !f.sourceSet.equals("test") && !f.sourceSet.endsWith("Test"))
                .collect(Collectors.toList());
    }

    private static List<Path> list(Path dir) {
        try (Stream<Path> children = Files.list(dir)) {
            return children.filter(Files::isDirectory).sorted().collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static List<Path> walk(Path dir) {
        try (Stream<Path> tree = Files.walk(dir)) {
            return tree.filter(p -> p.toString().endsWith(".java")).sorted().collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
