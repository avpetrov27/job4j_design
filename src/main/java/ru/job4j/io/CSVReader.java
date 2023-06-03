package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public class CSVReader {
    private static final String STDOUT = "stdout";

    private static PrintWriter getPWStdout() {
        return new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(System.out, StandardCharsets.UTF_8)));
    }

    private static PrintWriter getPWFile(String out) throws IOException {
        return new PrintWriter(
                new BufferedWriter(
                        new FileWriter(out, StandardCharsets.UTF_8)));
    }

    private static Scanner getFileScannerLineByLine(String path) throws IOException {
        return new Scanner(
                new File(path), StandardCharsets.UTF_8).useDelimiter(System.lineSeparator());
    }

    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter");
        try (Scanner scanner = getFileScannerLineByLine(path);
             PrintWriter pw = STDOUT.equals(out) ? getPWStdout() : getPWFile(out)) {
            String[] dataHeader = scanner.nextLine().split(delimiter);
            Map<String, Integer> header = new HashMap<>();
            IntStream.range(0, dataHeader.length).forEach(i -> header.put(dataHeader[i], i));
            List<Integer> order = new LinkedList<>();
            Arrays.stream(filter.split(",")).forEach(el -> order.add(header.get(el)));
            printLine(pw, dataHeader, delimiter, order);
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(delimiter);
                printLine(pw, line, delimiter, order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printLine(PrintWriter pw, String[] line, String delimiter,
                                  List<Integer> order) {
        pw.println(order
                .stream()
                .map(i -> line[i])
                .reduce((a, b) -> a + delimiter + b)
                .orElse(""));
        pw.flush();
    }

    private static void validate(ArgsName argsName) throws IOException {
        Path source = Path.of(argsName.get("path"));
        if (!Files.exists(source)) {
            throw new IOException(String.format("-path. File %s does not exist.", source));
        }
        if (!Files.isRegularFile(source)) {
            throw new IOException(String.format("-path. %s is not a file.", source));
        }
        String targetText = argsName.get("out");
        if (!STDOUT.equalsIgnoreCase(targetText) && Files.exists(Path.of(targetText))) {
            throw new IOException(String.format(
                    "-out. File %s cannot be created, because already exists.", targetText));
        }
        argsName.get("delimiter");
        argsName.get("filter");
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments. "
                    + "Specify exactly 4 arguments: -path, -delimiter, -out, -filter");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}
