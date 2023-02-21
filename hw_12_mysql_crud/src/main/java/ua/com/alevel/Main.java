package ua.com.alevel;

import ua.com.alevel.controller.Controller;
import ua.com.alevel.controller.impl.ControllerImpl;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        controller.start();
    }
}