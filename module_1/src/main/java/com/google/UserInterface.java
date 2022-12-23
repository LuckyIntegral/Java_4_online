package com.google;

import com.google.author_utills.Author;
import com.google.author_utills.AuthorStorage;
import com.google.book_utills.Book;
import com.google.book_utills.BookStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("Choose your option:");
        menu();
        while ((line = reader.readLine()) != null) {
            crud(reader, line);
        }
    }

    private void crud(BufferedReader reader, String line) throws IOException {
        switch (line) {
            case "1" -> addNewBook(reader);
            case "2" -> readBook(reader);
            case "3" -> readAllBooks();
            case "4" -> upgradeBook(reader);
            case "5" -> removeBook(reader);
            case "6" -> addAuthorToBook(reader);
            case "7" -> readAllAuthorsByBook(reader);
            case "8" -> removeAuthorFromBook(reader);
            case "9" -> addNewAuthor(reader);
            case "10" -> readAuthor(reader);
            case "11" -> readAllAuthors();
            case "12" -> upgradeAuthor(reader);
            case "13" -> removeAuthor(reader);
            case "14" -> addBookToAuthor(reader);
            case "15" -> readAllBooksByAuthor(reader);
            case "16" -> removeBookFromAuthor(reader);
            case "17" -> viewAllInfo();
            case "18" -> exit(reader);
        }
        menu();
    }

    private void viewAllInfo() {
        readAllBooks();
        System.out.println("----------------------------------------------------------------");
        readAllAuthors();
        System.out.println("----------------------------------------------------------------");
    }

    private void removeBookFromAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the author id");
        Author author = AuthorStorage.getAuthor(reader.readLine());
        if (author == null) {
            System.out.println("This id doesn't exist");
        } else {
            System.out.println("Please enter the book id");
            Book book = BookStorage.getBook(reader.readLine());
            if (book == null) {
                System.out.println("This id doesn't exist");
            } else {
                author.removeBook(book.getId());
            }
        }
    }

    private void readAllBooksByAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the author id");
        Author author = AuthorStorage.getAuthor(reader.readLine());
        if (author == null) {
            System.out.println("This id doesn't exist");
        } else {
            System.out.println(Book.TITLE);
            Book[] books = author.getBooks();
            for (int i = 0; i < author.getNotes(); i++) {
                System.out.println(books[i]);
            }
        }
    }

    private void addBookToAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the author id");
        Author author = AuthorStorage.getAuthor(reader.readLine());
        if (author == null) {
            System.out.println("This id is incorrect");
        } else {
            System.out.println("Please enter the book id");
            Book book = BookStorage.getBook(reader.readLine());
            if (book == null) {
                System.out.println("This id is incorrect");
            } else {
                author.addBookToAuthor(book, true);
            }
        }
    }

    private void removeAuthorFromBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter the book id");
        Book book = BookStorage.getBook(reader.readLine());
        if (book == null) {
            System.out.println("This id doesn't exist");
        } else {
            System.out.println("Please enter the author id");
            Author author = AuthorStorage.getAuthor(reader.readLine());
            if (author == null) {
                System.out.println("This id doesn't exist");
            } else {
                book.removeAuthor(author.getId());
            }
        }
    }

    private void readAllAuthorsByBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter the book id");
        Book book = BookStorage.getBook(reader.readLine());
        if (book == null) {
            System.out.println("This id is incorrect");
        } else {
            System.out.println(Author.TITLE);
            Author[] authors = book.getAuthors();
            for (int i = 0; i < book.getNotes(); i++) {
                System.out.println(authors[i]);
            }
        }
    }

    private void addAuthorToBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter the book id");
        Book book = BookStorage.getBook(reader.readLine());
        if (book == null) {
            System.out.println("This id is incorrect");
        } else {
            System.out.println("Please enter the author id");
            Author author = AuthorStorage.getAuthor(reader.readLine());
            if (author == null) {
                System.out.println("This id is incorrect");
            } else {
                book.addAuthorToBook(author,true);
            }
        }
    }

    private void removeAuthor(BufferedReader reader) throws IOException {
        System.out.println("PLease enter author id");
        AuthorStorage.removeAuthor(reader.readLine(), true);
    }

    private void upgradeAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter author id");
        Author author = AuthorStorage.getAuthor(reader.readLine());
        if (author == null) {
            System.out.println("This id doesn't exist");
            return;
        }
        upgradeAuthorMenu();
        String option = reader.readLine();
        switch (option) {
            case "1" -> {
                System.out.println("Please enter the name");
                author.setName(reader.readLine());
            }
            case "2" -> {
                System.out.println("Please enter the surname");
                author.setSurname(reader.readLine());
            }
            case "3" -> {
                System.out.println("Please enter the country of origin");
                author.setCountry(reader.readLine());
            }
            case "4" -> {
                System.out.println("Please enter book id");
                Book book = BookStorage.getBook(reader.readLine());
                if (book == null) {
                    System.out.println("This id is incorrect");
                } else {
                    author.addBookToAuthor(book, true);
                }
            }
            case "5" -> {
                System.out.println("Please enter book id");
                author.removeBook(reader.readLine());
            }
        }
    }

    private void upgradeAuthorMenu() {
        System.out.println("For upgrade name, please enter 1");
        System.out.println("For upgrade surname, please enter 2");
        System.out.println("For upgrade country of origin, please enter 3");
        System.out.println("For add new book to author, please enter 4");
        System.out.println("For remove book from author, please enter 5");
    }

    private void readAllAuthors() {
        System.out.println(Author.TITLE);
        Author[] array = AuthorStorage.getValues();
        for (int i = 0; i < AuthorStorage.getNotes(); i++) {
            System.out.println(array[i]);
        }
    }

    private void readAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter author id");
        Author author = AuthorStorage.getAuthor(reader.readLine());
        if (author == null) {
            System.out.println("This id doesn't exist");
        } else {
            System.out.println(Author.TITLE);
            System.out.println(author);
        }
    }

    private void addNewAuthor(BufferedReader reader) throws IOException {
        Author author = new Author();
        System.out.println("PLease enter the name");
        author.setName(reader.readLine());
        System.out.println("Please enter the surname");
        author.setSurname(reader.readLine());
        System.out.println("Please enter the country of origin");
        author.setCountry(reader.readLine());
        System.out.println(Author.TITLE);
        System.out.println(author);
        AuthorStorage.addAuthor(author);
    }

    private void removeBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter book id");
        BookStorage.removeBook(reader.readLine());
    }

    private void upgradeBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter book id");
        Book book = BookStorage.getBook(reader.readLine());
        if (book == null) {
            System.out.println("This id doesn't exist");
            return;
        }
        upgradeBookMenu();
        String option = reader.readLine();
        switch (option) {
            case "1" -> {
                System.out.println("Please enter the name");
                book.setName(reader.readLine());
            }
            case "2" -> {
                System.out.println("Please enter the genre");
                book.setGenre(reader.readLine());
            }
            case "3" -> {
                System.out.println("Please enter the year");
                try {
                    book.setProductionYear(Integer.parseInt(reader.readLine()));
                } catch (NumberFormatException e) {System.out.println("It is not a number");}
            }
            case "4" -> {
                System.out.println("Please enter author id");
                Author author = AuthorStorage.getAuthor(reader.readLine());
                if (author == null) {
                    System.out.println("This id is incorrect");
                } else {
                    book.addAuthorToBook(author, true);
                }
            }
            case "5" -> {
                System.out.println("Please enter author id");
                book.removeAuthor(reader.readLine());
            }
        }
    }

    private void upgradeBookMenu() {
        System.out.println("For upgrade name, please enter 1");
        System.out.println("For upgrade genre, please enter 2");
        System.out.println("For upgrade year pf production, please enter 3");
        System.out.println("For add new author to book, please enter 4");
        System.out.println("For remove author from book, please enter 5");
    }

    private void readAllBooks() {
        System.out.println(Book.TITLE);
        Book[] array = BookStorage.getValues();
        for (int i = 0; i < BookStorage.getNotes(); i++) {
            System.out.println(array[i]);
        }
    }

    private void readBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter book id");
        Book book = BookStorage.getBook(reader.readLine());
        if (book == null) {
            System.out.println("This id doesn't exist");
        } else {
            System.out.println(Book.TITLE);
            System.out.println(book);
        }
    }

    private void addNewBook(BufferedReader reader) throws IOException {
        Book book = new Book();
        System.out.println("Write the name of book");
        book.setName(reader.readLine());
        System.out.println("Write the genre of book");
        book.setGenre(reader.readLine());
        System.out.println("Write the year of production");
        try {
            book.setProductionYear(Integer.parseInt(reader.readLine()));
        } catch (NumberFormatException e) {System.out.println("It is not a number");}
        System.out.println(Book.TITLE);
        System.out.println(book);
        BookStorage.addBook(book);
    }

    private void exit(BufferedReader reader) throws IOException {
        reader.close();
        System.exit(0);
    }

    private void menu() {
        System.out.println();
        System.out.println("---------------------------- MENU ------------------------------");
        System.out.println("For create a new book, please enter -------------------------- 1");
        System.out.println("For find a book, please enter -------------------------------- 2");
        System.out.println("For find all books, please enter ----------------------------- 3");
        System.out.println("For upgrade the book (name, genre or year), please enter ----- 4");
        System.out.println("For delete the book, please enter ---------------------------- 5");
        System.out.println("For add an author to book, please enter ---------------------- 6");
        System.out.println("For read the authors by book, please enter ------------------- 7");
        System.out.println("For remove the author from the book, please enter ------------ 8");
        System.out.println("-------------|||------------|||||||-------------|||-------------");
        System.out.println("For create a new author, please enter ------------------------ 9");
        System.out.println("For find an author, please enter ---------------------------- 10");
        System.out.println("For find all authors, please enter -------------------------- 11");
        System.out.println("For upgrade an author (name, surname, country) please enter - 12");
        System.out.println("For delete the author, please enter ------------------------- 13");
        System.out.println("For add a book to an author, please enter ------------------- 14");
        System.out.println("For read the books by author, please enter ------------------ 15");
        System.out.println("For remove the book from the author, please enter ----------- 16");
        System.out.println("----------------------------------------------------------------");
        System.out.println("For view all information, please enter ---------------------- 17");
        System.out.println("For exit the program, please enter -------------------------- 18");
        System.out.println();
    }
}