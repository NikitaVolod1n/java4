package org.example;

import java.util.Objects;

public class Person {
    private String id;
    private String name;
    private String gender;
    private Department department;
    private double salary;
    private String birthDate;

    public Person(String id, String name, String gender, Department department, double salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{id='" + id + "', name='" + name + "', gender='" + gender +
                "', department=" + department.getName() + " (depID: " + department.getId() + ")" +
                ", salary=" + salary + ", birthDate='" + birthDate + "'}";
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getBirthDate() { return birthDate; }
    public Department getDepartment() { return department; }
    public double getSalary() { return salary; }
}