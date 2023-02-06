package ua.com.alevel.dao;

import ua.com.alevel.dto.AuthorDto;
import ua.com.alevel.dto.BookDto;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DaoCSV implements DaoInterface {
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
    public void createBook(BookDto book) {
        refreshBookList();
        refreshAuthorList();
        authorList.forEach(System.out::println);
        bookList.forEach(System.out::println);
        refreshAuthorFile();
        refreshBookFile();
    }

    @Override
    public void upgradeBookByID(String id, Book book) {

    }

    @Override
    public void deleteBookById(String id) {

    }

    @Override
    public Optional<Book> findBookById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public void createAuthor(AuthorDto author) {

    }

    @Override
    public void upgradeAuthorById(String id, Author author) {

    }

    @Override
    public void deleteAuthorById(String id) {

    }

    @Override
    public Optional<Author> findAuthorById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Author> findAllAuthors() {
        return null;
    }

    private void refreshBookList() {
        try {
            List<String[]> strings = Files.readAllLines(bookCsvFile).stream().map(s -> s.split(",")).toList();
            for (String[] s : strings) {
                if (s.length < 3) continue;
                Book book = new Book(s[1], s[2]);
                book.setId(s[0]);
                for (int i = 3; i < s.length; i++) {
                    book.addAuthor(s[i]);
                }
                bookList.add(book);
            }
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
            for (String[] s : strings) {
                if (s.length < 3) continue;
                Author author = new Author(s[1], s[2]);
                author.setId(s[0]);
                for (int i = 3; i < s.length; i++) {
                    author.addBook(s[i]);
                }
                authorList.add(author);
            }
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
}
