package ua.com.alevel.controller;

import java.io.BufferedReader;

public interface StudentController extends BaseController {
    void attachStudentToCourse(BufferedReader reader);
    void removeStudentFromCourse(BufferedReader reader);
    void getNumberOfCoursesByStudents();
    void findAllCoursesByStudent(BufferedReader reader);
}
