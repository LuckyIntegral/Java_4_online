package ua.com.file_helper.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Service {
    private static Service service;
    private File currentDirectory;
    private Service() {}
    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    public void start() {
        System.out.println("Please, enter the path of directory");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String temp = reader.readLine();
            currentDirectory = new File(temp);
            if (!currentDirectory.isDirectory()) {
                System.out.println("Your path is invalid, try again");
                throw new NoSuchElementException();
            }
            String option;
            showOptions();
            while ((option = reader.readLine()) != null) {
                switch (option) {
                    case "1": readAllFiles();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                    case "6":
                        break;
                    case "7":
                        break;
                    case "8":
                        break;
                    case "9":
                        break;
                    case "10":
                        break;
                    case "11": exit();
                        break;
                }
                showOptions();
            }
        } catch (IOException ioException) {
            System.out.println("Something went wrong with io, please try again");
        } catch (Exception exception) {
            System.out.println("Something went wrong, please try again");
        }
    }

    private void showOptions() {
        System.out.println();
        System.out.println("To read all files and directories inside, please enter 1");
        System.out.println("To create a new file, please enter 2");
        System.out.println("To create a new directory inside, please enter 3");
        System.out.println("To delete file within directory, please enter 4");
        System.out.println("To delete directory within directory, please enter 5");
        System.out.println("To move an internal file to another directory, please enter 6");
        System.out.println("To move an internal directory to another directory, please enter 7");
        System.out.println("To find a file within, please enter 8");
        System.out.println("To find a directory within, please enter 9");
        System.out.println("To find a file, that contains some text, please enter 10");
        System.out.println("To exit the program, please enter 11");
        System.out.println();
    }

    private void readAllFiles() {
        ArrayList<File> files = getAllFilesInside(currentDirectory);
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    private ArrayList<File> getAllFilesInside(File dir) {
        ArrayList<File> files = new ArrayList<>();
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                files.addAll(getAllFilesInside(file));
            } else {
                files.add(file);
            }
        }
        return files;
    }

    private void exit() {
        System.exit(0);
    }
}
