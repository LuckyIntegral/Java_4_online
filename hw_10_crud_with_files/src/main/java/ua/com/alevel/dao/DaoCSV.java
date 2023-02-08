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
        Book book = new Book();
        book.setName(bookDto.name());
        book.setGenre(bookDto.genre());
        book.setId(BaseEntity.generateId());
        bookList.add(book);
        refreshBookFile();
    }

    @Override
    public void upgradeBookByID(String id, Book book) {
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
        Optional<Book> book = findBookById(id);
        if (book.isPresent()) {
            bookList.remove(book.get());
            authorList.forEach(a -> a.removeBook(id));
        }
        refreshAuthorFile();
        refreshBookFile();
    }

    @Override
    public Optional<Book> findBookById(String id) {
        return bookList.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Book> findAllBooks() {
        return bookList;
    }

    @Override
    public void deleteAllBooks() {
        bookList = new ArrayList<>();
        authorList.forEach(Author::deleteAllBooks);
        refreshBookFile();
        refreshAuthorFile();
    }

    @Override
    public void createAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.name());
        author.setSurName(authorDto.surName());
        author.setId(BaseEntity.generateId());
        authorList.add(author);
        refreshAuthorFile();
    }

    @Override
    public void upgradeAuthorById(String id, Author author) {
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
        finalRefresh();
    }

    @Override
    public void deleteAuthorById(String id) {
        Optional<Author> author = findAuthorById(id);
        if (author.isPresent()) {
            authorList.remove(author.get());
            bookList.forEach(b -> b.removeAuthor(id));
        }
        finalRefresh();
    }

    @Override
    public Optional<Author> findAuthorById(String id) {
        return authorList.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorList;
    }

    @Override
    public void deleteAllAuthors() {
        authorList = new ArrayList<>();
        bookList.forEach(Book::deleteAllAuthors);
        finalRefresh();
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
        StringBuilder builder = new StringBuilder();
        for (Book book : bookList) {
            builder.append(book.getId()).append(',')
                    .append(book.getName()).append(',')
                    .append(book.getGenre()).append((book.getAuthors().size() > 0 ? ',' : ""))
                    .append(String.join(",", book.getAuthors())).append('\n');
        }
        try {
            Files.write(bookCsvFile, builder.toString().getBytes());
        } catch (IOException e) {
            System.out.println("The file was not backed up");
        }
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
        StringBuilder builder = new StringBuilder();
        for (Author author : authorList) {
            builder.append(author.getId()).append(',')
                    .append(author.getName()).append(',')
                    .append(author.getSurName()).append(',')
                    .append(String.join(",", author.getBooks())).append('\n');
        }
        try {
            Files.write(authorCsvFile, builder.toString().getBytes());
        } catch (IOException e) {
            System.out.println("The file was not backed up");
        }
    }

    @Override
    public void firstRefresh() {
        refreshAuthorList();
        refreshBookList();
    }

    @Override
    public void finalRefresh() {
        refreshBookFile();
        refreshAuthorFile();
    }
}
