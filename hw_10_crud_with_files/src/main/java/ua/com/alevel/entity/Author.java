package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Author extends BaseEntity {
    private String name;
    private String surName;
    private Set<String> books = new HashSet<>();
    public Author(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public Author() {}

    @Override
    public String toString() {
        return "Author{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", books=" + books +
                '}';
    }

    public Set<String> getBooks() {
        return books;
    }

    public void addBook(String id) {
        books.add(id);
    }

    public void removeBook(String id) {
        books.remove(id);
    }

    public void deleteAllBooks() {
        books = new HashSet<>();
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
