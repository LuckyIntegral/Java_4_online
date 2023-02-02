package ua.com.file_helper.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.*;

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
        System.out.println("Welcome to file helper");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String option;
            showOptions();
            while ((option = reader.readLine()) != null) {
                switch (option) {
                    case "1": readAllFiles(reader, false);
                        break;
                    case "2": readAllFiles(reader, true);
                        break;
                    case "3": createNewFile(reader, false);
                        break;
                    case "4": createNewFile(reader, true);
                        break;
                    case "5": deleteFileWithin(reader, false);
                        break;
                    case "6": deleteFileWithin(reader, true);
                        break;
                    case "7": moveFile(reader);
                        break;
                    case "8": moveDirectory(reader);
                        break;
                    case "9":
                        break;
                    case "10":
                        break;
                    case "11":
                    case "12": exit();
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
        System.out.println("To read files and directories inside your directory, please enter 1");
        System.out.println("To read all internal files in your directory, please enter 2");
        System.out.println("To create a new internal file in your directory, please enter 3");
        System.out.println("To create a new directory in your directory, please enter 4");
        System.out.println("To delete internal file in your directory, please enter 5");
        System.out.println("To delete directory in your directory, please enter 6");
        System.out.println("To move an internal file from your directory to another directory, please enter 7");
        System.out.println("To move an internal directory from your directory to another directory, please enter 8");
        System.out.println("To find a file in your directory, please enter 9");
        System.out.println("To find a directory in your directory, please enter 10");
        System.out.println("To find a file that contains some text in your directory, please enter 11");
        System.out.println("To exit the program, please enter 12");
        System.out.println();
    }

    private void moveDirectory(BufferedReader reader) {
        try {
            System.out.println("Please enter the source directory");
            Path path = Paths.get(reader.readLine());
            if (!Files.exists(path) || !Files.isDirectory(path)) {
                throw new SecurityException();
            }
            System.out.println("Please enter the target directory");
            Path target = Paths.get(reader.readLine());
            if (!Files.exists(target) || !Files.isDirectory(target)) {
                throw new SecurityException();
            }
            if (Files.exists(Paths.get(target + "/" + path.getFileName()))) {
                System.out.println("Same directory is already exist in target directory");
                return;
            }
            Path finalDir = Files.createDirectory(Paths.get(target + "/" + path.getFileName()));
            List<Path> sources = getAllFilesInside(path);
            List<Path> targets = sources.stream()
                    .map(ref -> ref.toString().substring(path.toString().length()))
                    .map(ref -> Paths.get(finalDir + ref))
                    .toList();
            for (int i = 0; i < sources.size(); i++) {
                Files.createFile(targets.get(i));
                Files.move(sources.get(i), targets.get(i), StandardCopyOption.REPLACE_EXISTING);
            }
            Files.delete(path);
        } catch (SecurityException e) {
            System.out.println("Invalid path");
        } catch (IOException e) {
            System.out.println("Something went wrong with io");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void moveFile(BufferedReader reader) {
        try {
            System.out.println("Please enter the path of new directory");
            Path dir = Paths.get(reader.readLine());
            if (!Files.exists(dir) || !Files.isDirectory(dir)) {
                throw new SecurityException();
            }
            System.out.println("Please enter the file path");
            Path sourceFile = Paths.get(reader.readLine());
            if (!Files.exists(sourceFile) || Files.isDirectory(sourceFile)) {
                throw new SecurityException();
            }
            if (Files.exists(Path.of(dir + "/" + sourceFile.getFileName()))) {
                System.out.println("Same file name is already in use in this directory");
                throw new Exception();
            }
            Path target = Files.createFile(Path.of(dir + "/" + sourceFile.getFileName()));
            System.out.println(target);
            Files.move(sourceFile, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (SecurityException e) {
            System.out.println("Invalid path");
        } catch (IOException e) {
            System.out.println("Something went wrong with io");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void deleteFileWithin(BufferedReader reader, boolean isDir) {
        try {
            System.out.println("Please enter the path of the directory");
            Path path = Paths.get(reader.readLine());
            if (!Files.exists(path)) {
                throw new SecurityException();
            }
            System.out.println("Enter the name (just the name, not the full path)");
            String name = reader.readLine();
            if (!name.startsWith("/")) {
                name = "/" + name;
            }
            Path file = Paths.get(path + name);
            if (Files.exists(file)) {
                if (Files.isDirectory(file) && isDir) {
                    if (Objects.requireNonNull(file.toFile().listFiles()).length == 0) {
                        Files.delete(file);
                    } else {
                        System.out.println("This directory can't be removed. Clean it first");
                    }
                } else if (!Files.isDirectory(file) && !isDir) {
                    Files.delete(file);
                } else {
                    System.out.println("Invalid condition");
                }
            } else {
                System.out.printf("File doesn't exist, or name %s is incorrect\n", file);
            }
        } catch (SecurityException e) {
            System.out.println("Invalid path");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void createNewFile(BufferedReader reader, boolean isDir) {
        try {
            System.out.println("Please enter the path of the directory");
            Path path = Paths.get(reader.readLine());
            if (!Files.exists(path)) {
                throw new SecurityException();
            }
            if (isDir) {
                System.out.println("Enter a name for the new internal directory");
                Path newDir = Paths.get(path + "/" + reader.readLine());
                if (Files.exists(newDir)) {
                    System.out.println("This name is already in use");
                } else {
                    Files.createDirectory(newDir);
                }
            } else {
                System.out.println("Enter a name for the new internal file");
                Path newFile = Paths.get(path + "/" + reader.readLine());
                if (Files.exists(newFile)) {
                    System.out.println("This name is already in use");
                } else {
                    Files.createFile(newFile);
                }
            }
        } catch (SecurityException e) {
            System.out.println("Invalid path");
        } catch (IOException e) {
            System.out.println("Something went wrong with io");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void readAllFiles(BufferedReader reader, boolean withInternalFiles) {
        try {
            System.out.println("Please enter the path of directory");
            Path path = Paths.get(reader.readLine());
            if (!Files.exists(path)) {
                throw new SecurityException();
            } else {
                List<Path> files;
                if (withInternalFiles) {
                    files = new ArrayList<>(getAllFilesInside(path));
                } else {
                    files = new ArrayList<>(Arrays
                            .stream(Objects.requireNonNull(path.toFile().listFiles()))
                            .map(File::toPath)
                            .toList());
                }
                if (files.size() == 0) {
                    System.out.println("Empty");
                } else {
                    files.forEach(file -> System.out.println(file.toString().substring(path.toString().length())));
                }
            }
        } catch (SecurityException e) {
            System.out.println("Invalid path");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    private ArrayList<Path> getAllFilesInside(Path path) {
        ArrayList<Path> paths = new ArrayList<>();
        for (File file : Objects.requireNonNull(path.toFile().listFiles())) {
            if (Files.isDirectory(file.toPath())) {
                paths.addAll(getAllFilesInside(file.toPath()));
            } else {
                paths.add(file.toPath());
            }
        }
        return paths;
    }

    private void exit() {
        System.exit(0);
    }
}
