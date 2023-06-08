package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);
        final Person person = new Person(false, 30, "Moscow", new Contact("11-111"),
                "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("address", person.getAddress());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        System.out.println(jsonObject);
        JSONObject jsonPerson = new JSONObject(person);
        System.out.println(jsonPerson);
        final Gson gson = new GsonBuilder().create();
        final Person personBack = gson.fromJson(String.valueOf(jsonPerson), Person.class);
        System.out.println(personBack);
    }
}
