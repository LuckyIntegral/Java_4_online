package ua.com.alevel.controller;

import java.io.BufferedReader;

public interface CourseController extends BaseController {
    void getNumberOfStudentByCourses();
    void findAllStudentsByCourse(BufferedReader reader);
}
