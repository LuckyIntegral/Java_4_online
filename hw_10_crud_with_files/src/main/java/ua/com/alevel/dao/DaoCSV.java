package ua.com.alevel.dao;

import ua.com.alevel.dto.*;
import ua.com.alevel.entity.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoCSV implements DaoContract {
    private final Path bookCsvFile = Paths.get("Books.csv");
    private final Path authorCsvFile = Paths.get("Author.csv");
    private List<Book> bookList = new ArrayList<>();
    private List<Author> authorList = new ArrayList<>();

    public DaoCSV() {
        if (!Files.exists(bookCsvFile)) {
            try {
                Files.createFile(bookCsvFile);
            } catch (IOException e) {
                System.out.println("System bug");
            }
        }
        if (!Files.exists(authorCsvFile)) {
            try {
                Files.createFile(authorCsvFile);
            } catch (IOException e) {
                System.out.println("System bug");
            }
        }
    }

    @Override
    public void createBook(BookDto bookDto) {
        refreshBookList();
        Book book = new Book();
        book.setName(bookDto.name());
        book.setGenre(bookDto.genre());
        book.setId(BaseEntity.generateId());
        bookList.add(book);
        refreshBookFile();
    }

    @Override
    public void upgradeBookByID(String id, Book book) {
        refreshBookList();
        if (findBookById(id).isPresent()) {
            deleteBookById(id);
            book.setId(id);
            book.getAuthors()
                    .forEach(a -> findAuthorById(a).ifPresent(author -> author.addBook(id)));
            book.getAuthors()
                    .stream()
                    .filter(a -> findAuthorById(a).isEmpty())
                    .forEach(book::removeAuthor);
            bookList.add(book);
            refreshAuthorFile();
        }
        refreshBookFile();
    }

    @Override
    public void deleteBookById(String id) {
        refreshBookList();
        if (findBookById(id).isPresent()) {
            findBookById(id).get()
                    .getAuthors()
                    .forEach(s -> findAuthorById(s).ifPresent(author -> author.removeBook(id)));
            bookList.remove(findBookById(id).get());
        }
        refreshAuthorFile();
        refreshBookFile();
    }

    @Override
    public Optional<Book> findBookById(String id) {
        refreshBookList();
        return bookList.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Book> findAllBooks() {
        refreshBookList();
        return bookList;
    }

    @Override
    public void deleteAllBooks() {
        refreshBookList();
        refreshAuthorList();
        bookList = new ArrayList<>();
        authorList.forEach(Author::deleteAllBooks);
        refreshBookFile();
        refreshAuthorFile();
    }

    @Override
    public void createAuthor(AuthorDto authorDto) {
        refreshAuthorList();
        Author author = new Author();
        author.setName(authorDto.name());
        author.setSurName(authorDto.surName());
        author.setId(BaseEntity.generateId());
        authorList.add(author);
        refreshAuthorFile();
    }

    @Override
    public void upgradeAuthorById(String id, Author author) {
        refreshAuthorList();
        if (findAuthorById(id).isPresent()) {
            deleteAuthorById(id);
            author.setId(id);
            author.getBooks()
                    .forEach(b -> findBookById(b).ifPresent(book -> book.addAuthor(id)));
            author.getBooks()
                    .stream()
                    .filter(b -> findBookById(b).isEmpty())
                    .forEach(author::removeBook);
            authorList.add(author);
            refreshBookFile();
        }
        refreshAuthorFile();
    }

    @Override
    public void deleteAuthorById(String id) {
        refreshAuthorList();
        if (findAuthorById(id).isPresent()) {
            findAuthorById(id).get()
                    .getBooks()
                    .forEach(s -> findBookById(id).ifPresent(book -> book.removeAuthor(id)));
            findAuthorById(id).ifPresent(authorList::remove);
            authorList.remove(findAuthorById(id).get());
            refreshBookFile();
        }
        refreshAuthorFile();
    }

    @Override
    public Optional<Author> findAuthorById(String id) {
        refreshAuthorList();
        return authorList.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Author> findAllAuthors() {
        refreshAuthorList();
        return authorList;
    }

    @Override
    public void deleteAllAuthors() {
        refreshAuthorList();
        refreshBookList();
        authorList = new ArrayList<>();
        bookList.forEach(Book::deleteAllAuthors);
        refreshAuthorList();
        refreshBookFile();
    }

    private void refreshBookList() {
        try {
            List<String[]> strings = Files.readAllLines(bookCsvFile).stream().map(s -> s.split(",")).toList();
            bookList = new ArrayList<>();
            for (String[] s : strings) {
                if (s.length < 3) continue;
                Book book = new Book(s[1], s[2]);
                book.setId(s[0]);
                for (int i = 3; i < s.length; i++) {
                    book.addAuthor(s[i]);
                }
                bookList.add(book);
            }
            BaseEntity.refreshBaseEntity(bookList.stream().map(BaseEntity::getId).toList());
        } catch (Exception e) {
            System.out.println("System bug");
        }
    }

    private void refreshBookFile() {
        bookList.stream()
                .map(e -> e.getId() + "," +
                        e.getName() + "," +
                        e.getGenre() +
                        (e.getAuthors().size() > 0 ?"," + String.join(",", e.getAuthors()) : "") + "\n")
                .reduce((s, s2) -> s + s2)
                .ifPresent(e -> {
                    try {
                        Files.write(bookCsvFile, e.getBytes());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }

    private void refreshAuthorList() {
        try {
            List<String[]> strings = Files.readAllLines(authorCsvFile).stream().map(s -> s.split(",")).toList();
            authorList = new ArrayList<>();
            for (String[] s : strings) {
                if (s.length < 3) continue;
                Author author = new Author(s[1], s[2]);
                author.setId(s[0]);
                for (int i = 3; i < s.length; i++) {
                    author.addBook(s[i]);
                }
                authorList.add(author);
            }
            BaseEntity.refreshBaseEntity(authorList.stream().map(BaseEntity::getId).toList());
        } catch (Exception e) {
            System.out.println("System bug");
        }
    }

    private void refreshAuthorFile() {
        authorList.stream()
                .map(e -> e.getId() + "," +
                        e.getName() + "," +
                        e.getSurName() +
                        (e.getBooks().size() > 0 ? "," + String.join(",", e.getBooks()) : "") + "\n")
                .reduce((s, s2) -> s + s2)
                .ifPresent(e -> {
                    try {
                        Files.write(authorCsvFile, e.getBytes());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }

    @Override
    public void finalRefresh() {
        refreshBookFile();
        refreshAuthorFile();
    }
}
