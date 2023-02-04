package ua.com.alevel.controller;

import ua.com.alevel.service.BusinessLogicService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
    private final BusinessLogicService service = new BusinessLogicService();
    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

        } catch (IOException e) {
            System.out.println("Something went wrong with IO");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    public static void options() {
        System.out.println();
        System.out.println("To ");
        System.out.println();
    }
}
