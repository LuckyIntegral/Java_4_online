package ua.com.file_helper.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Service {
    private static Service service;
    private Service() {}
    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    public void start() {
        System.out.println("Please, enter the path of directory");
        String currentPath;
        String option;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            currentPath = reader.readLine();
            System.out.println(currentPath);
        } catch (IOException ioException) {
            System.out.println("Something went wrong with io, please try again");
        } catch (Exception exception) {
            System.out.println("Something went wrong, please try again");
        }
    }

    private void options() {
        System.out.println();
        System.out.println("Hello");
        System.out.println();
    }
}
