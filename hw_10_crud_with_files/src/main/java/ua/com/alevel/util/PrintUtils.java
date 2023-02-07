package ua.com.alevel.util;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public abstract class PrintUtils {
    public static final String BOOK_HEADER = "ID       Name            Genre                Authors";
    public static final String AUTHOR_HEADER = "ID       Name                 Surname              Books";
    public static String bookToString(Book book) {
        return book.getId() + " " +
                book.getName() + " ".repeat(16 - book.getName().length()) +
                book.getGenre() + " ".repeat(21 - book.getGenre().length()) +
                (book.getAuthors().size() > 0 ? String.join(", ", book.getAuthors()) : "<empty>");
    }
    public static String authorToString(Author author) {
        return author.getId() + " " +
                author.getName() + " ".repeat(21 - author.getName().length()) +
                author.getSurName() + " ".repeat(21 - author.getSurName().length()) +
                (author.getBooks().size() > 0 ? String.join(",", author.getBooks()) : "<empty>");
    }
}
