package com.google;

import com.google.controller.ServiceController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ServiceController serviceController = new ServiceController();
        serviceController.start();
    }
}