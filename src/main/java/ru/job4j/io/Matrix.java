package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static void multiple(int size) {
        try (FileOutputStream out = new FileOutputStream("data/matrixResult.txt")) {
            for (int row = 0; row < size; row++) {
                for (int cell = 0; cell < size; cell++) {
                    out.write((cell == 0 ? "" : "\t").getBytes());
                    out.write(Integer.toString((row + 1) * (cell + 1)).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiple(9);
    }
}
