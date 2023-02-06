package ua.com.alevel.entity;

import java.util.ArrayList;

public class Author extends BaseEntity {
    private String name;
    private String surName;
    private ArrayList<String> books = new ArrayList<>();
    public Author(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", books=" + books +
                '}';
    }

    public ArrayList<String> getBooks() {
        return books;
    }

    public void addBook(String id) {
        if (!books.contains(id)) {
            books.add(id);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }
}
