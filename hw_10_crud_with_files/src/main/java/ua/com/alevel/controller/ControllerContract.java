package ua.com.alevel.controller;

import java.io.BufferedReader;

public interface ControllerContract {
    void createBook(BufferedReader reader);
    void attachAuthorToBook(BufferedReader reader);
    void deleteAuthorFromBook(BufferedReader reader);
    void readBookById(BufferedReader reader);
    void readAllBooks();
    void upgradeBook(BufferedReader reader);
    void deleteBook(BufferedReader reader);
    void createAuthor(BufferedReader reader);
    void attachBookToAuthor(BufferedReader reader);
    void deleteBookFromAuthor(BufferedReader reader);
    void readAuthorById(BufferedReader reader);
    void readAllAuthors();
    void upgradeAuthor(BufferedReader reader);
    void deleteAuthor(BufferedReader reader);
    void deleteAllBooks();
    void deleteAllAuthors();
    void preConfiguration();
    void exitProgram();
}
