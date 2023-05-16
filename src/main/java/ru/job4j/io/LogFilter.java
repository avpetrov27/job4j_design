package ru.job4j.io;

import java.io.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines()
                    .filter(l -> {
                        String[] arr = l.split(" ");
                        return "404".equals(arr[arr.length - 2]);
                    }).collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("data/log.txt");
        save(log, "data/404.txt");
    }
}
