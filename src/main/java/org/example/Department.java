package org.example;

public class Department {
    private static int idGenerator = 1;

    private int id;
    private String name;

    public Department(String name) {
        this.id = idGenerator++;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}
