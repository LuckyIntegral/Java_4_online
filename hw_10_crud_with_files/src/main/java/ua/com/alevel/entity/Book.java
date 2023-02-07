package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Book extends BaseEntity {
    private String name;
    private String  genre;
    private Set<String> authors = new HashSet<>();
    public Book(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public Book() {}

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", authors=" + authors +
                '}';
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String id) {
        authors.add(id);
    }

    public void removeAuthor(String id) {
        authors.remove(id);
    }

    public void deleteAllAuthors() {
        authors = new HashSet<>();
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
