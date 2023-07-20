package ru.job4j.search;

import ru.job4j.io.ArgsName;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Predicate;

import static ru.job4j.io.Search.search;

public class Search {
    private static final String TYPE_MASK = "mask";
    private static final String TYPE_NAME = "name";
    private static final String TYPE_REGEX = "regex";
    private static final List<String> VALID_TYPES = List.of(TYPE_MASK, TYPE_NAME, TYPE_REGEX);

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments. "
                    + "Specify exactly 4 arguments: parameters -d, -n, -t, -o.");
        }
        ArgsName parameters = ArgsName.of(args);
        validate(parameters);
        try (PrintWriter out =
                     new PrintWriter(
                             new BufferedWriter(
                                     new FileWriter(parameters.get("o"))))) {
            search(Path.of(parameters.get("d")),
                    condition(parameters.get("n"), parameters.get("t"))
            ).forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> condition(String name, String type) throws IOException {
        return switch (type) {
            case TYPE_MASK -> p -> p.toFile().getName().matches(maskToRegex(name));
            case TYPE_NAME -> p -> p.toFile().getName().equalsIgnoreCase(name);
            case TYPE_REGEX -> p -> p.toFile().getName().matches(name);
            default -> throw new IOException(String.format("-t parameter is incorrect."
                    + "Type must have one of the following values: %s", VALID_TYPES));
        };
    }

    private static String maskToRegex(String mask) {
        return mask
                .replace(".", "\\.")
                .replace("*", ".*")
                .replace('?', '.');
    }

    private static void validate(ArgsName parameters) throws IOException {
        Path root = Path.of(parameters.get("d"));
        if (!Files.exists(root)) {
            throw new IOException(String.format("-d Directory %s does not exist.", root));
        }
        if (!Files.isDirectory(root)) {
            throw new IOException(String.format("-d %s is not a directory.", root));
        }
        if (!VALID_TYPES.contains(parameters.get("t"))) {
            throw new IOException(String.format("-t parameter is incorrect."
                    + "Type must have one of the following values: %s", VALID_TYPES));
        }
        parameters.get("n");
        String targetText = parameters.get("o");
        if (Files.exists(Path.of(targetText))) {
            throw new IOException(String.format(
                    "-o File %s cannot be created, because already exists.", targetText));
        }
    }
}
