package ru.job4j.serialization.json;

import java.util.Arrays;

public class Person implements java.io.Serializable {
    private final boolean sex;
    private final int age;
    private final String address;
    private final Contact contact;
    private final String[] statuses;

    public Person(boolean sex, int age, String address, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.statuses = statuses;
    }

    public boolean getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", address='" + address + '\''
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
