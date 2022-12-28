package com.github.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.github.entity.Student;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    private static final StudentService studentService = new StudentService();

    private static final int SIZE = 10;
    private static final String NAME = "testName";
    private static final String EMAIL = "testEmail";
    private static final String TELEGRAM = "testTelegram";
    private static final String GITHUB = "testGitHub";

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < SIZE; i++) {
            Student student = generateStudent(i);
            studentService.create(student);
        }
    }

    @Test
    @Order(1)
    public void checkSizeStudent() {
        Assertions.assertEquals(studentService.findAll().size(), SIZE);
    }

    @Test
    @Order(2)
    public void createStudent() {
        Student student = generateStudent(SIZE + 1);
        studentService.create(student);
        Assertions.assertEquals(studentService.findAll().size(), SIZE + 1);
    }

    @Test
    @Order(3)
    public void removeStudent() {
        Student student = studentService.findAll().get(0);
        studentService.delete(student.getId());
        Assertions.assertEquals(studentService.findAll().size(), SIZE);
    }

    @Test
    @Order(4)
    public void shouldBeCreateStudentWhenEmailIsDuplicate() {
        Student student = studentService.findAll().get(0);
        Student newStudent = new Student();
        newStudent.setEmail(student.getEmail());
        studentService.create(newStudent);
        Assertions.assertEquals(studentService.findAll().size(), SIZE);
    }

    private static Student generateStudent(int i) {
        Student student = new Student();
        student.setName(NAME + i);
        student.setEmail(EMAIL + i);
        student.setTelegramAcc(TELEGRAM + i);
        student.setGitHubAcc(GITHUB + i);
        return student;
    }
}
