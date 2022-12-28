package com.github.controller;


import com.github.entity.Student;
import com.github.service.StudentService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class StudentController { // interface взаємодії з юзером

    private final StudentService service = new StudentService();

    public void start() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose your option");
        String line;
        menu();
        while ((line = reader.readLine()) != null) {
            crud(reader, line);
        }
    }

    private void crud(BufferedReader reader, String line) throws Exception {
        switch (line) {
            case "1" -> createStudent(reader);
            case "2" -> findByEmail(reader);
            case "3" -> findAll();
            case "4" -> updateStudent(reader);
            case "5" -> deleteById(reader);
            case "6" -> stop();
        }
        menu();
    }

    private void updateStudent(BufferedReader reader) throws Exception {
        System.out.println("Please enter the Student's id");
        String id = reader.readLine();
        Student student = service.findByID(id);
        if (student == null) {
            System.out.println("Number is invalid");
            return;
        }

        System.out.println("If you want to update the name, enter 1");
        System.out.println("If you want to update the email, enter 2");
        System.out.println("If you want to update the github acc, enter 3");
        System.out.println("If you want to update the telegram acc, enter 4");

        String line = reader.readLine();
        switch (line) {
            case "1" -> {
                System.out.println("Please enter the name");
                student.setName(reader.readLine());
            }
            case "2" -> {
                System.out.println("Please enter email");
                student.setEmail(reader.readLine());
            }
            case "3" -> {
                System.out.println("Please enter github acc");
                student.setGitHubAcc(reader.readLine());
            }
            case "4" -> {
                System.out.println("Please enter new telegram acc");
                student.setTelegramAcc(reader.readLine());
            }
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want to create an student, please enter 1");
        System.out.println("If you want to find the student, please enter 2");
        System.out.println("If you want to find all students, please enter 3");
        System.out.println("If you want to update the student, please enter 4");
        System.out.println("If you want to remove the student, please enter 5");
        System.out.println("If you want to close the application, please enter 6");
        System.out.println();
    }

    private void deleteById(BufferedReader reader) throws Exception {
        System.out.println("Please enter the id");
        service.delete(reader.readLine());
    }

    private void findByEmail(BufferedReader reader) throws Exception {
        System.out.println("Please enter the id");
        String id = reader.readLine();
        Student student = service.findByID(id);
        System.out.println("student = " + student);  
    }

    private void findAll() {
        System.out.println("All students:");
        List<Student> array = service.findAll();
        for (Student student : array) {
            System.out.println(student);
        }
    }

    private void createStudent(BufferedReader reader) throws Exception {
        Student student = new Student();

        System.out.println("Please enter the name");
        String name = reader.readLine();
        student.setName(name);

        System.out.println("Please enter the email");
        String surname = reader.readLine();
        student.setEmail(surname);

        System.out.println("Please enter the github acc");
        String number = reader.readLine();
        student.setGitHubAcc(number);

        System.out.println("Please enter the telegram acc");
        student.setTelegramAcc(reader.readLine());

        service.create(student);
    }

    private void stop() {
        System.exit(0);
    }

}
