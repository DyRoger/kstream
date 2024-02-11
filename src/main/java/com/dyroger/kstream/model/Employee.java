package com.dyroger.kstream.model;

public class Employee {
    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void modifyData() {
        // Modify the data as needed (for example, update a field)
        this.name = "ModifiedName";
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }
}
