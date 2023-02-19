package ua.com.alevel;

import ua.com.alevel.controller.FileWorker;

public class Main {
    public static void main(String[] args) {
        FileWorker worker = new FileWorker();
        worker.start();
    }
}