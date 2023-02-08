package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.*;

import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTests {
    private static final ServiceContract service = new ServiceBusinessLogic();

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < 10; i++) {
            service.createBook(new Book("Demo book " + i, "Demo"));
        }
        for (int i = 0; i < 10; i++) {
            service.createAuthor(new Author("Demo author " + i, "Number " + i * 37 % 10));
        }
    }

    @AfterAll public static void cleanFiles() {
        service.deleteAllAuthors();
        service.deleteAllBooks();
    }

    @Test
    @Order(1)
    public void createBookTest() {
        int serviceSize = service.findAllBooks().size();
        service.createBook(new Book("Name", "Genre"));
        Assertions.assertEquals(serviceSize + 1, service.findAllBooks().size());
    }
    @Test
    @Order(2)
    public void upgradeBookByIdTest() {
        String id = service.findAllBooks().get(0).getId();
        Book book = new Book("Test upgrade f", "Test upgrade f");
        int authorSize = 0;
        for (Author author : service.findAllAuthors()) {
            book.addAuthor(author.getId());
            authorSize++;
        }
        service.upgradeBookById(id, book);
        Assertions.assertEquals(service.findBookById(id).getName(), book.getName());
        Assertions.assertEquals(service.findBookById(id).getGenre(), book.getGenre());
        Assertions.assertEquals(service.findBookById(id).getAuthors().size(), authorSize);
    }

    @Test
    @Order(3)
    public void findBookByIdTest(){
        String randomId = UUID.randomUUID().toString().substring(0, 8);
        Assertions.assertNull(service.findBookById(randomId));
    }

    @Test
    @Order(4)
    public void findAllBooksTest(){
        Assertions.assertNotEquals(service.findAllBooks().size(), 0);
    }

    @Test
    @Order(5)
    public void deleteBookByIdTest(){
        String id = service.findAllBooks().get(0).getId();
        service.deleteBookById(id);
        Assertions.assertNull(service.findBookById(id));
    }

    @Test
    @Order(6)
    public void createAuthorTest(){
        int serviceSize = service.findAllAuthors().size();
        service.createAuthor(new Author("Name", "Surname"));
        Assertions.assertEquals(serviceSize + 1, service.findAllAuthors().size());
    }

    @Test
    @Order(7)
    public void upgradeAuthorByIdTest(){
        String id = service.findAllAuthors().get(0).getId();
        Author author = new Author("Test upgrade f", "Test upgrade f");
        int booksSize = 0;
        for (Book book : service.findAllBooks()) {
            author.addBook(book.getId());
            booksSize++;
        }
        service.upgradeAuthorById(id, author);
        Assertions.assertEquals(service.findAuthorById(id).getName(), author.getName());
        Assertions.assertEquals(service.findAuthorById(id).getBooks().size(), booksSize);
    }

    @Test
    @Order(8)
    public void findAuthorByIdTest(){
        String randomId = UUID.randomUUID().toString();
        Assertions.assertNull(service.findAuthorById(randomId));
    }

    @Test
    @Order(9)
    public void findAllAuthorsTest(){
        service.createAuthor(new Author("Test find all", "Test find all"));
        Assertions.assertNotEquals(service.findAllAuthors().size(), 0);
    }

    @Test
    @Order(10)
    public void deleteAuthorByIdTest(){
        String id = service.findAllAuthors().get(0).getId();
        service.deleteAuthorById(id);
        Assertions.assertNull(service.findAuthorById(id));
    }

    @Test
    @Order(11)
    public void deleteAllAuthorsTest(){
        service.createAuthor(new Author("Test delete all", "Test delete all"));
        service.deleteAllAuthors();
        Assertions.assertEquals(service.findAllAuthors().size(), 0);
    }

    @Test
    @Order(12)
    public void deleteAllBooksTest(){
        service.createBook(new Book("Test delete all", "Same test"));
        service.deleteAllBooks();
        Assertions.assertEquals(service.findAllBooks().size(), 0);
    }
}
