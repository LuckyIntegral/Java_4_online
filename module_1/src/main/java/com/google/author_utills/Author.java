package com.google.author_utills;

import com.google.book_utills.Book;
import com.google.book_utills.BookStorage;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Author {
    private String name;
    private String surname;
    private String country;
    private String id;
    private static final String defaultValue = "Unspecified";
    private static final Set<String> passwords = new HashSet<>();
    public static final String TITLE = "Name        Surname     Country             Author id";
    private static final int defaultSize = 5;
    private Book[] books = new Book[defaultSize];
    private int notes;

    public Author() {setId();}

    @Override
    public String toString() {
        return name + " ".repeat(12 - name.length()) +
                surname + " ".repeat(12 - surname.length()) +
                country + " ".repeat(20 - country.length()) +
                id + " ".repeat(10 - id.length()) +
                "Number of books - '" + notes + '\'';
    }

    public String getId() {return id;}

    public void addBookToAuthor(Book book, boolean isFirst) {
        if (identityCheck(book)) return;
        books[notes] = book;
        if (isFirst) book.addAuthorToBook(this, false);
        notes++;
        if (notes == books.length) {
            Book[] values = new Book[books.length * 2];
            System.arraycopy(books, 0, values, 0, books.length);
            books = values;
        }
    }

    private boolean identityCheck(Book book) {
        for (int i = 0; i < notes; i++) {
            if (books[i].getId().equals(book.getId())) return true;
        }
        return false;
    }

    public void removeBook(String id) {
        boolean flag = true;
        for (int i = 0; i < notes; i++) {
            if (flag) {
                if (books[i].getId().equals(id)) {
                    notes--;
                    books[i] = null;
                    flag = false;
                }
            } else {
                books[i - 1] = books[i];
            }
        }
        if (!flag) {
            Book book = BookStorage.getBook(id);
            if (book == null) return;
            book.removeAuthor(getId());
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

    public void setName(String name) {
        if (name.matches("\\W+")) {
            System.out.println("This name is incorrect");
            this.name = defaultValue;
        } else if (name.length() > 12) {
            System.out.println("This name is too long");
            this.name = defaultValue;
        } else if (name.trim().equals("")) {
            this.name = defaultValue;
        } else {
            this.name = name;
        }
    }

    public void setSurname(String surname) {
        if (surname.matches("\\W+")) {
            System.out.println("This surname is incorrect");
            this.surname = defaultValue;
        } else if (surname.length() > 12) {
            System.out.println("This surname is too long");
            this.surname = defaultValue;
        } else if (surname.trim().equals("")) {
            this.surname = defaultValue;
        } else {
            this.surname = surname;
        }
    }

    public void setCountry(String country) {
        if (country.matches("[^\\w ]+")) {
            System.out.println("This country name is incorrect");
            this.country = defaultValue;
        } else if (country.length() > 20) {
            System.out.println("This country name is too long");
            this.country = defaultValue;
        } else if (country.trim().equals("")) {
            this.country = defaultValue;
        } else {
            this.country = country;
        }
    }

    public Book[] getBooks() {
        return books;
    }

    public int getNotes() {
        return notes;
    }
}