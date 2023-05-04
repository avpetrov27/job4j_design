package ru.job4j.map;

import java.util.*;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(1988, Calendar.DECEMBER, 27);
        User u1 = new User("Sasha", 0, calendar);
        User u2 = new User("Sasha", 0, calendar);
        int hashCode1 = u1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        int hashCode2 = u2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        Map<User, Object> map = new HashMap<>(16);
        map.put(u1, new Object());
        map.put(u2, new Object());
        for (User key : map.keySet()) {
            Object value = map.get(key);
            System.out.println(key + " = " + value);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
