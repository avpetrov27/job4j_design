package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip =
                     new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            Path targetToOneLevelUp = new File(target.getPath() + "\\..\\").toPath();
            for (Path source : sources) {
                Path relativePath = targetToOneLevelUp.relativize(source);
                zip.putNextEntry(new ZipEntry(relativePath.toFile().getPath()));
                try (BufferedInputStream out =
                             new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName parameters) throws IOException {
        Path dir = Path.of(parameters.get("d"));
        if (!Files.exists(dir)) {
            throw new IOException("Directory does not exist.");
        }
        if (!Files.isDirectory(dir)) {
            throw new IOException("This is not a directory.");
        }
        parameters.get("e");
        parameters.get("o");
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName parameters = ArgsName.of(args);
        zip.validate(parameters);
        Path targetPath = Path.of(parameters.get("d"));
        String archiveName = parameters.get("o");
        List<Path> listPathToArchive = Search.search(targetPath,
                p -> {
                    String name = p.toFile().getName();
                    return !name.endsWith(parameters.get("e")) && !name.equals(archiveName);
                });
        zip.packFiles(listPathToArchive, new File(targetPath + "\\" + archiveName));
    }
}
