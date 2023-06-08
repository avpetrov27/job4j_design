package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, "Moscow", new Contact("11-111"),
                "Worker", "Married");
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/java/ru/job4j/serialization/xml/Person.xml",
                StandardCharsets.UTF_8))) {
            Person resultFromFile = (Person) unmarshaller.unmarshal(br);
            System.out.println(resultFromFile);
        }
    }
}
