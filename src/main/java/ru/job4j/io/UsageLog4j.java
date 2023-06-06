package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean a = true;
        byte b = 50;
        char c = 0x266B;
        short d = 1000;
        int e = 123_456_789;
        float f = 1.0f;
        long g = 0xffffffffL;
        double h = 340.01e16D;
        LOG.debug("User info a : {},"
                        + " b : {},"
                        + " c : {},"
                        + " d : {},"
                        + " e : {},"
                        + " f : {},"
                        + " g : {},"
                        + " h : {}",
                a, b, c, d, e, f, g, h);
    }
}
