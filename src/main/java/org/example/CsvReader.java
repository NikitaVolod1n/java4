package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader {

    public static void main(String[] args) {
        String csvFilePath = "foreign_names.csv";
        List<Person> persons = readPersonsFromCsv(csvFilePath);

        System.out.println("Всего прочитано людей: " + persons.size());
        System.out.println("-".repeat(50));

        for (int i = 0; i < Math.min(10, persons.size()); i++) {
            System.out.println(persons.get(i));
        }
    }

    public static List<Person> readPersonsFromCsv(String filePath) {
        try (InputStream in = CsvReader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (in == null) {
                throw new RuntimeException("Файл не найден: " + filePath);
            }
            return parseCsv(in);
        } catch (Exception e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Person> parseCsv(InputStream in) {
        List<Person> personList = new ArrayList<>();
        Map<String, Department> departmentsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] lineData = line.split(";");

                if (lineData.length < 6) {
                    continue;
                }

                String id = lineData[0].trim();
                String name = lineData[1].trim();
                String gender = lineData[2].trim();
                String birthDate = lineData[3].trim();
                String divisionName = lineData[4].trim();
                double salary = Double.parseDouble(lineData[5].trim());

                Department department = departmentsMap.computeIfAbsent(divisionName, Department::new);

                personList.add(new Person(id, name, gender, department, salary, birthDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personList;
    }
}