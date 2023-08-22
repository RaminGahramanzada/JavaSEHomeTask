package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeToFile(String fileName, List<Person> people) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Person person : people) {
                writer.write(person.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Person> parseDataAndCreatePeople(List<String> lines) {
        List<Person> people = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            String name = parts[0].split("=")[1];
            String surname = parts[1].split("=")[1];
            int age = Integer.parseInt(parts[2].split("=")[1]);

            Person person = new Person(name, surname, age);
            people.add(person);
        }

        return people;
    }
}
