package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<Path, FileProperty> map = new TreeMap<>(
            Comparator.comparing(Path::getFileName)
                    .thenComparing(p -> p.toFile().length())
                    .thenComparing(p -> p)
    );

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        map.put(file, new FileProperty(file.toFile().length(), file.getFileName().toString()));
        return super.visitFile(file, attrs);
    }

    public void getDuplicate() {
        Iterator<Map.Entry<Path, FileProperty>> iteratorMap = map.entrySet().iterator();
        Map.Entry<Path, FileProperty> prev = null;
        boolean flagDuplicate = false;
        while (iteratorMap.hasNext()) {
            Map.Entry<Path, FileProperty> next = iteratorMap.next();
            if (prev != null && next.getValue().equals(prev.getValue())) {
                if (!flagDuplicate) {
                    System.out.printf("%s - %d%n", prev.getValue().getName(),
                            prev.getValue().getSize());
                    System.out.printf("\t%s%n", prev.getKey());
                    flagDuplicate = true;
                }
                System.out.printf("\t%s%n", next.getKey());
            } else {
                flagDuplicate = false;
            }
            prev = next;
        }
    }
}
