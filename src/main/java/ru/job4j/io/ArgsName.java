package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String retValue = values.get(key);
        if (retValue == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return retValue;
    }

    private void parse(String[] args) {
        values.putAll(Arrays.stream(args)
                .map(String::trim)
                .filter(this::validate)
                .map(el -> el.split("=", 2))
                .collect(Collectors.toMap(
                        e -> e[0].split("-")[1],
                        e -> e[1],
                        (first, second) -> first
                )));
    }

    private boolean validate(String name) {
        if (!name.startsWith("-")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not start with a '-' character", name));
        }
        if (!name.contains("=")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain an equal sign", name));
        }
        if (name.startsWith("-=")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain a key", name));
        }
        if (name.indexOf("=") == name.length() - 1) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain a value", name));
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
