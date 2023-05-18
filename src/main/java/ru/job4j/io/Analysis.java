package ru.job4j.io;

import java.io.*;

public class Analysis {
    private boolean unavailableFlag = false;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] s = line.trim().split(" ", 2);
                if (("400".equals(s[0]) || "500".equals(s[0])) ^ unavailableFlag) {
                    out.write(s[1] + ";" + (unavailableFlag ? System.lineSeparator() : ""));
                    unavailableFlag = !unavailableFlag;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
