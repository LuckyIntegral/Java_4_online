package ua.com.alevel.controller;

import ua.com.alevel.service.BusinessLogicService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
    private final BusinessLogicService service = new BusinessLogicService();
    public void start() {
        options();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String option;
            do {
                option = reader.readLine();
                switch (option) {
//                    case "1" -> ;
//                    case "2" -> ;
//                    case "3" -> ;
//                    case "4" -> ;
//                    case "5" -> ;
//                    case "6" -> ;
//                    case "7" -> ;
//                    case "8" -> ;
//                    case "9" -> ;
//                    case "10" -> ;
//                    case "11" -> ;
//                    case "12" -> ;
//                    case "13" -> ;
//                    case "14" -> ;
                    case "17" -> exit();
                }
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

    private void exit() {
        System.exit(0);
    }
}
