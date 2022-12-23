package com.google.book_utills;

import com.google.author_utills.Author;
import com.google.author_utills.AuthorStorage;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Book {
    private String name;
    private String genre;
    private int productionYear;
    private String id;
    private static final String defaultValue = "Unspecified";
    private static final Set<String> passwords = new HashSet<>();
    public static final String TITLE = "Name                Genre               Year  Book id";
    private static final int defaultSize = 5;
    private Author[] authors = new Author[defaultSize];
    private int notes;

    public Book() {
        setId();
    }

    @Override
    public String toString() {
        return  name + " ".repeat(20 - name.length()) +
                genre + " ".repeat(20 - genre.length()) +
                productionYear + " ".repeat(6 - String.valueOf(productionYear).length()) +
                id + " ".repeat(10 - id.length()) +
                "Number of authors - '" + notes + '\'';
    }

    public void addAuthorToBook(Author author, boolean isFirst) {
        if (identityCheck(author)) return;
        authors[notes] = author;
        if (isFirst) author.addBookToAuthor(this, false);
        notes++;
        if (notes == authors.length) {
            Author[] values = new Author[authors.length * 2];
            System.arraycopy(authors, 0, values, 0, authors.length);
            authors = values;
        }
    }

    private boolean identityCheck(Author author) {
        for (int i = 0; i < notes; i++) {
            if (authors[i].getId().equals(author.getId())) return true;
        }
        return false;
    }

    public void removeAuthor(String id) {
        boolean flag = false;
        for (int i = 0; i < notes; i++) {
            if (!flag) {
                if (authors[i].getId().equals(id)) {
                    notes--;
                    authors[i] = null;
                    flag = true;
                }
            } else {
                authors[i - 1] = authors[i];
            }
        }
        if (flag) {
            Author author = AuthorStorage.getAuthor(id);
            if (author == null) return;
            author.removeBook(getId());
        }
    }

    private void setId() {
        String id;
        do {
            id = UUID.randomUUID().toString().substring(0, 8);
        } while (passwords.contains(id));
        passwords.add(id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        if (name.matches("\\W")) {
            System.out.println("Name is incorrect");
            this.name = defaultValue;
        } else if (name.length() > 20) {
            System.out.println("The name is too long");
            this.name = defaultValue;
        }else if (name.trim().equals("")){
            this.name = defaultValue;
        } else {
            this.name = name;
        }
    }

    public void setGenre(String genre) {
        if (genre.matches("\\W+")) {
            System.out.println("This genre is incorrect");
            this.genre = defaultValue;
        } else if (genre.length() > 20) {
            System.out.println("The genre is too long");
            this.genre = defaultValue;
        } else if (genre.trim().equals("")) {
            this.genre = defaultValue;
        } else {
            this.genre = genre;
        }
    }

    public void setProductionYear(int productionYear) {
        if (productionYear > 2022 || productionYear < 0) {
            System.out.println("This year of production is incorrect");
        } else {
            this.productionYear = productionYear;
        }
    }

    public Author[] getAuthors() {
        return authors;
    }

    public int getNotes() {
        return notes;
    }
}