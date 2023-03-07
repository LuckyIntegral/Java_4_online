package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.CourseController;
import ua.com.alevel.controller.MainController;
import ua.com.alevel.controller.StudentController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainControllerImpl implements MainController {

    private final CourseController courseController = new CourseControllerImpl();
    private final StudentController studentController = new StudentControllerImpl();

    @Override
    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            options();
            String option;
            while ((option = reader.readLine()) != null) {
                switch (option) {
                    case "1" -> studentController.create(reader);
                    case "2" -> studentController.findById(reader);
                    case "3" -> studentController.findAll();
                    case "4" -> studentController.update(reader);
                    case "5" -> studentController.delete(reader);
                    case "6" -> courseController.create(reader);
                    case "7" -> courseController.findById(reader);
                    case "8" -> courseController.findAll();
                    case "9" -> courseController.update(reader);
                    case "10" -> courseController.delete(reader);
                    case "11" -> studentController.attachStudentToCourse(reader);
                    case "12" -> studentController.removeStudentFromCourse(reader);
                    case "13" -> courseController.getNumberOfStudentByCourses();
                    case "14" -> studentController.getNumberOfCoursesByStudents();
                    case "15" -> studentController.findAllCoursesByStudent(reader);
                    case "16" -> courseController.findAllStudentsByCourse(reader);
                    case "17" -> exit();
                }
                options();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exit(){
        System.exit(0);
    }

    private void options() {
        System.out.println("----------------------MAIN--MENU----------------------");
        System.out.println("To create a student, enter ------------------------- 1");
        System.out.println("To find the student, enter ------------------------- 2");
        System.out.println("To find all students, enter ------------------------ 3");
        System.out.println("To update the student info, enter ------------------ 4");
        System.out.println("To delete the student, enter ----------------------- 5");
        System.out.println("To create a course, enter -------------------------- 6");
        System.out.println("To find the course, enter -------------------------- 7");
        System.out.println("To find all courses, enter ------------------------- 8");
        System.out.println("To update the course info, enter ------------------- 9");
        System.out.println("To delete the course, enter ----------------------- 10");
        System.out.println("To attach the student to course, enter ------------ 11");
        System.out.println("To remove the student from course, enter ---------- 12");
        System.out.println("To get number of students by each course, enter --- 13");
        System.out.println("To get number of courses by each student, enter --- 14");
        System.out.println("To get list of courses by student, enter ---------- 15");
        System.out.println("To get list of students by course, enter ---------- 16");
        System.out.println("To exit the system, enter ------------------------- 17");
        System.out.println("------------------------------------------------------");
    }
}
