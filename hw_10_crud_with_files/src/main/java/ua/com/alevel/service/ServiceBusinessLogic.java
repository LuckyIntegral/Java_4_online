package ua.com.alevel.service;

import ua.com.alevel.dao.DaoCSV;
import ua.com.alevel.dao.DaoContract;
import ua.com.alevel.dto.AuthorDto;
import ua.com.alevel.dto.BookDto;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;
import java.util.Optional;

public class ServiceBusinessLogic implements ServiceContract {
    private final DaoContract dao = new DaoCSV();
    private static final String DEFAULT_VALUE = "Unspecified";
    @Override
    public void createBook(Book book) {
        if (!book.getName().trim().matches("^[a-zA-Z0-9 _]{3,15}$")) {
            book.setName(DEFAULT_VALUE);
        }
        if (!book.getGenre().trim().matches("^[a-zA-Z_ -]{3,20}$")) {
            book.setGenre(DEFAULT_VALUE);
        }
        dao.createBook(new BookDto(book.getName(), book.getGenre()));
    }

    @Override
    public void upgradeBookById(String id, Book book) {
        if (isIdInvalid(id)) {
            System.out.println("Invalid id");
            return;
        }
        if (findBookById(id) == null) {
            System.out.println("Incorrect id");
            return;
        }
        if (!book.getName().trim().matches("^[a-zA-Z0-9 _]{3,15}$")) {
            book.setName(DEFAULT_VALUE);
        }
        if (!book.getGenre().trim().matches("^[a-zA-Z_ -]{3,20}$")) {
            book.setGenre(DEFAULT_VALUE);
        }
        book.getAuthors().stream().filter(this::isIdInvalid).forEach(book::removeAuthor);
        dao.upgradeBookByID(id, book);
    }

    @Override
    public Book findBookById(String id) {
        if (isIdInvalid(id)) {
            System.out.println("Invalid id");
        }
        Optional<Book> book = dao.findBookById(id);
        return book.orElse(null);
    }

    @Override
    public List<Book> findAllBooks() {
        return dao.findAllBooks();
    }

    @Override
    public void deleteBookById(String id) {
        if (isIdInvalid(id)) {
            System.out.println("Invalid id");
        } else {
            dao.deleteBookById(id);
        }
    }

    @Override
    public void deleteAllBooks() {
        dao.deleteAllBooks();
    }

    @Override
    public void createAuthor(Author author) {
        if (!author.getName().trim().matches("^[a-zA-Z0-9 _-]{3,20}$")) {
            author.setName(DEFAULT_VALUE);
        }
        if (!author.getSurName().trim().matches("^[a-zA-Z0-9 _-]{3,20}$")) {
            author.setName(DEFAULT_VALUE);
        }
        dao.createAuthor(new AuthorDto(author.getName(), author.getSurName()));
    }

    @Override
    public void upgradeAuthorById(String id, Author author) {
        if (isIdInvalid(id)) {
            System.out.println("Invalid id");
            return;
        }
        if (findAuthorById(id) == null) {
            System.out.println("Incorrect id");
            return;
        }
        if (!author.getName().trim().matches("^[a-zA-Z0-9 _-]{3,20}$")) {
            author.setName(DEFAULT_VALUE);
        }
        if (!author.getSurName().trim().matches("^[a-zA-Z0-9 _-]{3,20}$")) {
            author.setName(DEFAULT_VALUE);
        }
        author.getBooks().stream().filter(this::isIdInvalid).forEach(author::removeBook);
        dao.upgradeAuthorById(id, author);
    }

    @Override
    public Author findAuthorById(String id) {
        if (isIdInvalid(id)) {
            System.out.println("Invalid id");
        }
        Optional<Author> author = dao.findAuthorById(id);
        return author.orElse(null);
    }

    @Override
    public List<Author> findAllAuthors() {
        return dao.findAllAuthors();
    }

    @Override
    public void deleteAuthorById(String id) {
        if (isIdInvalid(id)) {
            System.out.println("Invalid id");
        } else {
            dao.deleteAuthorById(id);
        }
    }

    @Override
    public void deleteAllAuthors() {
        dao.deleteAllAuthors();
    }

    @Override
    public void exitProgram() {
        dao.finalRefresh();
        System.exit(0);
    }

    private boolean isIdInvalid(String id) {
        return !id.matches("^[a-zA-Z0-9]{8}$");
    }
}
