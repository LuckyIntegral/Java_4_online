package ua.com.alevel.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public interface ServiceContract {
    void createBook(Book book);
    void upgradeBookById(String id, Book book);
    Book findBookById(String id);
    List<Book> findAllBooks();
    void deleteBookById(String id);
    void deleteAllBooks();
    void createAuthor(Author author);
    void upgradeAuthorById(String id, Author author);
    Author findAuthorById(String id);
    List<Author> findAllAuthors();
    void deleteAuthorById(String id);
    void deleteAllAuthors();
    void preConfig();
    void exitProgram();
}
