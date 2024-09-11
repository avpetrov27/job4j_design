package ru.job4j.gc;

import static java.lang.Thread.sleep;

public class GCDemo {
    private static final long KB = 1024;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory);
        System.out.printf("Total: %d%n", totalMemory);
        System.out.printf("Max: %d%n", maxMemory);
    }

    public static void main(String[] args) throws InterruptedException {
        info();
        for (int i = 0; i < 100; i++) {
            new Person(i, "N" + i);
        }
        System.gc();
        sleep(1);
        info();
    }
}
