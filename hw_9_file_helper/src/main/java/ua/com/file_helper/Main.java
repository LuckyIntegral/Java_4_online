package ua.com.file_helper;

import ua.com.file_helper.service.Service;

public class Main {
    public static void main(String[] args) {
        Service service = Service.getInstance();
        service.start();
    }
}