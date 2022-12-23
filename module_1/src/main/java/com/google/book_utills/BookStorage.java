package com.google.book_utills;

import com.google.author_utills.AuthorStorage;

public class BookStorage {
    private static final int defaultValue = 10;
    private static Book[] values = new Book[defaultValue];
    private static int notes;

    private BookStorage() {}

    public static void addBook(Book book) {
        values[notes] = book;
        ++notes;
        if (notes == values.length) {
            Book[] books = new Book[values.length * 2];
            System.arraycopy(values, 0, books, 0, values.length);
            values = books;
        }
    }

    public static void removeBook(String  id) {
        boolean flag = false;
        for (int i = 0; i < notes; i++) {
            if (!flag) {
                if (values[i].getId().equals(id)) {
                    --notes;
                    flag = true;
                    AuthorStorage.cleanBookHistory(values[i].getId());
                    values[i] = null;
                }
            }else {
                    values[i - 1] = values[i];
            }
        }
    }

    public static void cleanAuthorHistory(String id) {
        if (notes == 0) return;
        for (int i = 0; i < notes; i++) {
            values[i].removeAuthor(id);
        }
    }

    public static Book getBook(String id) {
        for (int i = 0; i < notes; i++) {
            if (values[i].getId().equals(id)) return values[i];
        }
        return null;
    }

    public static Book[] getValues() {
        return values;
    }

    public static int getNotes() {
        return notes;
    }
}