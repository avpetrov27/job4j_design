package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) throws IOException {
        preparePathTarget(target);
        try (ZipOutputStream zip =
                     new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out =
                             new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void preparePathTarget(File target) throws IOException {
        if (!target.mkdirs()) {
            throw new IOException(String.format("-o File %s could not be created", target));
        }
        if (!target.delete()) {
            throw new IOException(String.format("-o File %s could not be created", target));
        }
    }

    private void validate(ArgsName parameters) throws IOException {
        Path root = Path.of(parameters.get("d"));
        if (!Files.exists(root)) {
            throw new IOException(String.format("-d Directory %s does not exist.", root));
        }
        if (!Files.isDirectory(root)) {
            throw new IOException(String.format("-d %s is not a directory.", root));
        }
        if (!parameters.get("e").matches("\\..+")) {
            throw new IllegalArgumentException("-e parameter is incorrect. "
                    + "Extension must start with a \".\" "
                    + "and have at least one more character.");
        }
        String targetText = parameters.get("o");
        if (Files.exists(Path.of(targetText))) {
            throw new IOException(String.format(
                    "-o File %s cannot be created, because already exists.", targetText));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid number of arguments. "
                    + "Specify exactly 3 arguments: parameters -d, -e, -o.");
        }
        Zip zip = new Zip();
        ArgsName parameters = ArgsName.of(args);
        zip.validate(parameters);
        List<Path> listPathsToArchive = Search.search(Path.of(parameters.get("d")),
                p -> !p.toFile().getName().endsWith(parameters.get("e")));
        zip.packFiles(listPathsToArchive, new File(parameters.get("o")));
    }
}
