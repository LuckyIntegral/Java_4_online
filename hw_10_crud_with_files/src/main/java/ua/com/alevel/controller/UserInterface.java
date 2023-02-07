package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.ServiceBusinessLogic;
import ua.com.alevel.service.ServiceContract;
import ua.com.alevel.util.PrintUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface implements ControllerContract {
    private final ServiceContract service = new ServiceBusinessLogic();
    public void start() {
        options();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            preConfiguration();
            String option;
            do {
                option = reader.readLine();
                switch (option) {
                    case "1" -> createBook(reader);
                    case "2" -> attachAuthorToBook(reader);
                    case "3" -> deleteAuthorFromBook(reader);
                    case "4" -> readBookById(reader);
                    case "5" -> readAllBooks();
                    case "6" -> upgradeBook(reader);
                    case "7" -> deleteBook(reader);
                    case "8" -> createAuthor(reader);
                    case "9" -> attachBookToAuthor(reader);
                    case "10" -> deleteBookFromAuthor(reader);
                    case "11" -> readAuthorById(reader);
                    case "12" -> readAllAuthors();
                    case "13" -> upgradeAuthor(reader);
                    case "14" -> deleteAuthor(reader);
                    case "15" -> deleteAllBooks();
                    case "16" -> deleteAllAuthors();
                    case "17" -> exitProgram();
                }
                options();
            } while (!option.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            System.out.println("Something went wrong with IO");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    public static void options() {
        System.out.println("----------------MAIN MENU----------------");
        System.out.println("To create a book, enter ---------------- 1");
        System.out.println("To attach an author to book, enter ----- 2");
        System.out.println("To delete an author from book, enter --- 3");
        System.out.println("To read a book, enter ------------------ 4");
        System.out.println("To read all books, enter --------------- 5");
        System.out.println("To upgrade the book, enter ------------- 6");
        System.out.println("To delete the book, enter -------------- 7");
        System.out.println("||---||----||----||----||----||----||---||");
        System.out.println("To create an author, enter ------------- 8");
        System.out.println("To attach a book to author, enter ------ 9");
        System.out.println("To delete a book from author, enter --- 10");
        System.out.println("To read an author, enter -------------- 11");
        System.out.println("To read all authors, enter ------------ 12");
        System.out.println("To upgrade an author, enter ----------- 13");
        System.out.println("To delete an author, enter ------------ 14");
        System.out.println("||---||----||----||----||----||----||---||");
        System.out.println("To delete all books, enter ------------ 15");
        System.out.println("To delete all authors, enter ---------- 16");
        System.out.println("To exit the program, enter ------------ 17");
        System.out.println("------------------------------------------");
    }

    @Override
    public void createBook(BufferedReader reader) {
        try {
            System.out.println("Please enter the name of new book");
            String name = reader.readLine();
            System.out.println("Please enter the genre of new book");
            String genre = reader.readLine();
            Book book = new Book(name, genre);
            service.createBook(book);
        } catch (IOException e) {
            System.out.println("Something went wrong with io");
        }
    }

    @Override
    public void attachAuthorToBook(BufferedReader reader) {
        try {
            String bookId = getBookId(reader);
            Book book = service.findBookById(bookId);
            if (book == null) {return;}
            String authorId = getAuthorId(reader);
            Author author = service.findAuthorById(authorId);
            if (author == null) {return;}
            book.addAuthor(authorId);
            service.upgradeBookById(bookId, book);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void deleteAuthorFromBook(BufferedReader reader) {
        try {
            String bookId = getBookId(reader);
            Book book = service.findBookById(bookId);
            if (book == null) {return;}
            String authorId = getAuthorId(reader);
            Author author = service.findAuthorById(authorId);
            if (author == null) {return;}
            book.removeAuthor(authorId);
            service.upgradeBookById(bookId, book);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void readBookById(BufferedReader reader) {
        try {
            String id = getBookId(reader);
            Book book = service.findBookById(id);
            if (book == null) {return;}
            System.out.println(PrintUtils.bookToString(book));
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void readAllBooks() {
        System.out.println(PrintUtils.BOOK_HEADER);
        for (Book book : service.findAllBooks()) {
            System.out.println(PrintUtils.bookToString(book));
        }
    }

    @Override
    public void upgradeBook(BufferedReader reader) {
        try {
            String id = getBookId(reader);
            Book book = service.findBookById(id);
            if (book == null) {return;}

            System.out.println("Please enter the new name");
            String name = reader.readLine();
            System.out.println("PLease enter the new genre");
            String genre = reader.readLine();

            book.setName(name);
            book.setGenre(genre);
            service.upgradeBookById(id, book);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void deleteBook(BufferedReader reader) {
        try {
            String id = getBookId(reader);
            service.deleteBookById(id);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void createAuthor(BufferedReader reader) {
        try {
            System.out.println("Please enter the name");
            String name = reader.readLine();
            System.out.println("Please enter the surname");
            String surname = reader.readLine();
            Author author = new Author(name, surname);
            service.createAuthor(author);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void attachBookToAuthor(BufferedReader reader) {
        try {
            String authorId = getAuthorId(reader);
            Author author = service.findAuthorById(authorId);
            if (author == null) {return;}

            String bookId = getBookId(reader);
            Book book = service.findBookById(bookId);
            if (book == null) {return;}

            author.addBook(bookId);
            service.upgradeAuthorById(authorId, author);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void deleteBookFromAuthor(BufferedReader reader) {
        try {
            String authorId = getAuthorId(reader);
            Author author = service.findAuthorById(authorId);
            if (author == null) {return;}

            String bookId = getBookId(reader);
            Book book = service.findBookById(bookId);
            if (book == null) {return;}

            author.removeBook(bookId);
            service.upgradeAuthorById(authorId, author);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void readAuthorById(BufferedReader reader) {
        try {
            String id = getAuthorId(reader);
            Author author = service.findAuthorById(id);
            if (author == null) {return;}
            System.out.println(PrintUtils.authorToString(author));
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void readAllAuthors() {
        System.out.println(PrintUtils.AUTHOR_HEADER);
        for (Author author : service.findAllAuthors()) {
            System.out.println(PrintUtils.authorToString(author));
        }
    }

    @Override
    public void upgradeAuthor(BufferedReader reader) {
        try {
            String id = getAuthorId(reader);
            Author author = service.findAuthorById(id);
            if (author == null) {return;}

            System.out.println("Please enter the new name");
            String name = reader.readLine();
            System.out.println("Please enter the new surname");
            String surname = reader.readLine();

            author.setName(name);
            author.setSurName(surname);
            service.upgradeAuthorById(id, author);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void deleteAuthor(BufferedReader reader) {
        try {
            String id = getAuthorId(reader);
            service.deleteAuthorById(id);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    @Override
    public void deleteAllBooks() {
        service.deleteAllBooks();
    }

    @Override
    public void deleteAllAuthors() {
        service.deleteAllAuthors();
    }

    @Override
    public void preConfiguration() {
        service.preConfig();
    }

    @Override
    public void exitProgram() {
        service.exitProgram();
    }

    private String getAuthorId(BufferedReader reader) throws IOException {
        System.out.println("Please enter the author id below");
        return reader.readLine();
    }
    private String getBookId(BufferedReader reader) throws IOException {
        System.out.println("Please enter the book id below");
        return reader.readLine();
    }
}
