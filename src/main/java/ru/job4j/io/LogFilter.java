package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LogFilter {
    public List<String> filter(String file) {
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        for (String s : log) {
            System.out.println(s);
        }
    }
}
