package com.github;

import com.github.config.StudentFactory;
import com.github.controller.StudentController;

public class Main {
    public static void main(String[] args) throws Exception {
//        Student student = new Student();
//        Class<?> studentClass = student.getClass();
//        for (Field declaredField : studentClass.getDeclaredFields()) {
//            System.out.println(declaredField);
//        }

        new StudentFactory().configure();
        new StudentController().start();
    }
}