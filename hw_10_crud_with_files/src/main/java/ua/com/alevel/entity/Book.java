package ua.com.alevel.entity;

import java.util.ArrayList;

public class Book extends BaseEntity {
    private String name;
    private String  genre;
    private final ArrayList<String> authors = new ArrayList<>();
    public Book(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", authors=" + authors +
                '}';
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String id) {
        if (!authors.contains(id)) {
            authors.add(id);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String  genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
