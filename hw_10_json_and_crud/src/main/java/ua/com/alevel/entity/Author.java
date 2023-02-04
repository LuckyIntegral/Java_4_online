package ua.com.alevel.entity;

import java.util.ArrayList;

public class Author extends BaseEntity {
    private String name;
    private String surName;
    private ArrayList<Book> books = new ArrayList<>();
    public Author(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
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
