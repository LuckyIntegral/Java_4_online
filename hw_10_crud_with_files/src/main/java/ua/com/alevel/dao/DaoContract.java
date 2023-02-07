package ua.com.alevel.dao;

import ua.com.alevel.dto.AuthorDto;
import ua.com.alevel.dto.BookDto;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;
import java.util.Optional;

public interface DaoContract {
    void createBook(BookDto book);
    void upgradeBookByID(String id, Book book);
    void deleteBookById(String id);
    Optional<Book> findBookById(String id);
    List<Book> findAllBooks();
    void deleteAllBooks();
    void createAuthor(AuthorDto author);
    void upgradeAuthorById(String id, Author author);
    void deleteAuthorById(String id);
    Optional<Author> findAuthorById(String id);
    List<Author> findAllAuthors();
    void deleteAllAuthors();
    void firstRefresh();
    void finalRefresh();
}
