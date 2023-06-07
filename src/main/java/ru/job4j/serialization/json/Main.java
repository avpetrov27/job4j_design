package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, "Moscow", new Contact("11-111"),
                new String[]{"Worker", "Married"});
        final Gson gson = new GsonBuilder().create();
        final String personJson = gson.toJson(person);
        System.out.println(personJson);
        final Person personBack = gson.fromJson(personJson, Person.class);
        System.out.println(personBack);
    }
}
