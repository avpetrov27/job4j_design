package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    private final static String EXIT = "Exit";
    private final static Pattern PATTERN_MSG = Pattern.compile("\\?msg=\\S+");

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstLine = in.readLine();
                    System.out.println(firstLine);
                    Matcher matcher = PATTERN_MSG.matcher(firstLine);
                    if (matcher.find()) {
                        String msg = matcher.group().split("=", 2)[1];
                        if (EXIT.equals(msg)) {
                            server.close();
                        } else {
                            out.write(msg.getBytes());
                        }
                    }
                    for (String str = in.readLine(); str != null && !str.isEmpty();
                         str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }
}
