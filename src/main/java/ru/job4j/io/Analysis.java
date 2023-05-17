package ru.job4j.io;

import java.io.*;

public class Analysis {
    private boolean unavailableFlag = false;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            read.lines()
                    .map(String::trim)
                    .map(s -> s.split(" ", 2))
                    .filter(s -> filterForPrint(s[0]))
                    .forEach(s -> printUnavailable(s[1], out));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean filterForPrint(String s0) {
        return (!unavailableFlag && ("400".equals(s0) || "500".equals(s0)))
                || (unavailableFlag && ("200".equals(s0) || "300".equals(s0)));
    }

    private void printUnavailable(String s1, PrintWriter out) {
        out.write(s1 + ";");
        if (unavailableFlag) {
            out.write(System.lineSeparator());
        }
        unavailableFlag = !unavailableFlag;
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
