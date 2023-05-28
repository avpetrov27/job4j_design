package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new LinkedHashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newKey = new FileProperty(file.toFile().length(), file.getFileName().toString());
        map.putIfAbsent(newKey, new LinkedList<>());
        map.get(newKey).add(file);
        return super.visitFile(file, attrs);
    }

    public void getDuplicate() {
        map.entrySet()
                .stream()
                .filter(k -> k.getValue().size() > 1)
                .forEach(k -> {
                    FileProperty key = k.getKey();
                    System.out.println(key.getName() + " - " + key.getSize());
                    k.getValue().forEach(v -> System.out.println("\t" + v));
                });
    }
}
