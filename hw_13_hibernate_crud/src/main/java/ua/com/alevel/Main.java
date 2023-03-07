package ua.com.alevel;

import ua.com.alevel.controller.impl.MainControllerImpl;

public class Main {
    public static void main(String[] args) {
        MainControllerImpl controller = new MainControllerImpl();
        controller.start();
    }
}