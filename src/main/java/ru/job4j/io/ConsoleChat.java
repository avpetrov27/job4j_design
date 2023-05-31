package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        Path logPath = Path.of(path);
        if (!Files.exists(logPath)) {
            Files.createFile(logPath);
        }
        try (PrintWriter pwOut = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)));
             BufferedReader brIn = new BufferedReader(
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            List<String> log = new LinkedList<>();
            String input = brIn.readLine();
            log.add(String.format("%s %s %s", LocalDateTime.now(), "User", input));
            int size = readPhrases().size();
            boolean botIsOn = true;
            while (!OUT.equalsIgnoreCase(input)) {
                if (STOP.equalsIgnoreCase(input)) {
                    botIsOn = false;
                }
                if (CONTINUE.equalsIgnoreCase(input)) {
                    botIsOn = true;
                }
                if (botIsOn) {
                    String answer = readPhrases().get((int) (Math.random() * size));
                    pwOut.println(answer);
                    pwOut.flush();
                    log.add(String.format("%s %s %s", LocalDateTime.now(), "Bot", answer));
                }
                input = brIn.readLine();
                log.add(String.format("%s %s %s", LocalDateTime.now(), "User", input));
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> ret = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            ret.addAll(br.lines().toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8, true)))) {
            log.forEach(pw::println);
            pw.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./data/ConsoleChat/log.txt",
                "./data/ConsoleChat/botAnswers.txt");
        cc.run();
    }
}
