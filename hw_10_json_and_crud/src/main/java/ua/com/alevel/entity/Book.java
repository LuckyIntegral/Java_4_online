package ua.com.alevel.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Book extends BaseEntity {
    private String name;
    private int year;
    private final ArrayList<Author> authors = new ArrayList<>();
    public Book(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        if (authors.contains(author)) {
            authors.add(author);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
}
