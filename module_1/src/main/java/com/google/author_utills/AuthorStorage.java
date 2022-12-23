package com.google.author_utills;

import com.google.book_utills.BookStorage;

public class AuthorStorage {
    private static final int defaultSize = 10;
    private static Author[] values = new Author[defaultSize];
    private static int notes;

    private AuthorStorage() {}

    public static void addAuthor(Author author) {
        values[notes] = author;
        ++notes;
        if (notes == values.length) {
            Author[] authors = new Author[values.length * 2];
            System.arraycopy(values, 0, authors, 0, values.length);
            values = authors;
        }
    }

    public static void removeAuthor(String id, boolean isFirst) {
        if (!isFirst) return;
        boolean flag = false;
        for (int i = 0; i < notes; i++) {
            if (!flag) {
                if (values[i].getId().equals(id)) {
                    --notes;
                    BookStorage.cleanAuthorHistory(values[i].getId());
                    values[i] = null;
                    flag = true;
                }
            } else {
                values[i - 1] = values[i];
            }
        }
    }

    public static void cleanBookHistory(String id) {
        if (notes == 0) return;
        for (int i = 0; i < notes; i++) {
            values[i].removeBook(id);
        }
    }

    public static Author getAuthor(String id) {
        for (int i = 0; i < notes; i++) {
            if (values[i].getId().equals(id)) return values[i];
        }
        return null;
    }

    public static Author[] getValues() {
        return values;
    }

    public static int getNotes() {
        return notes;
    }
}
