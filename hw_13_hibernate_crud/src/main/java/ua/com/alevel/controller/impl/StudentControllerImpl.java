package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.StudentController;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.util.PrintUtils;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.service.impl.CourseServiceImpl;
import ua.com.alevel.service.impl.StudentServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

public class StudentControllerImpl implements StudentController {
    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final PrintUtils toPrint = PrintUtils.getInstance();

    @Override
    public void create(BufferedReader reader) throws IOException {
        Student student = new Student();
        System.out.println("Enter the name");
        student.setFirstName(reader.readLine());
        System.out.println("Enter the last name");
        student.setLastName(reader.readLine());
        System.out.println("Enter the age");
        try {
            student.setAge(Integer.parseInt(reader.readLine()));
        } catch (Exception e) {
            System.out.println("Not a number");
            student.setAge(0);
        }
        student.setUpdated(new Date());
        studentService.create(student);
    }

    @Override
    public void update(BufferedReader reader) throws IOException {
        System.out.println("Enter the student id");
        try {
            Long id = Long.parseLong(reader.readLine());
            Student student = studentService.findById(id);
            if (student != null) {
                System.out.println("Enter the new name, current is '" + student.getFirstName() + '\'');
                student.setFirstName(reader.readLine());
                System.out.println("Enter the new last name, current is '" + student.getLastName() + '\'');
                student.setLastName(reader.readLine());
                System.out.println("Enter the new age, current is '" + student.getAge() + '\'');
                try {
                    student.setAge(Integer.parseInt(reader.readLine()));
                } catch (Exception e) {
                    System.out.println("Not a number");
                    student.setAge(0);
                }
                student.setUpdated(new Date());
                studentService.update(student);
            }
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        } catch (Exception e) {
            System.out.println("Unknown exception, try again");
        }
    }

    @Override
    public void attachStudentToCourse(BufferedReader reader) {
        try {
            System.out.println("Enter the student id");
            Long studId = Long.parseLong(reader.readLine());
            Student student = studentService.findById(studId);
            System.out.println("Enter the course id");
            Long courseId = Long.parseLong(reader.readLine());
            Set<Course> courseSet = student.getCourseSet();
            courseSet.add(courseService.findById(courseId));
            student.setCourseSet(courseSet);
            studentService.update(student);
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        } catch (Exception e) {
            System.out.println("Unknown exception, try again");
        }
    }

    @Override
    public void removeStudentFromCourse(BufferedReader reader) {
        try {
            System.out.println("Enter the student id");
            Long studId = Long.parseLong(reader.readLine());
            Student student = studentService.findById(studId);
            System.out.println("Enter the course id");
            Long courseId = Long.parseLong(reader.readLine());
            Set<Course> courseSet = student.getCourseSet();
            courseSet.remove(courseService.findById(courseId));
            student.setCourseSet(courseSet);
            studentService.update(student);
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        } catch (Exception e) {
            System.out.println("Unknown exception, try again");
        }
    }

    @Override
    public void findAllCoursesByStudent(BufferedReader reader) {
        try {
            System.out.println("Please enter the student id");
            Long id = Long.parseLong(reader.readLine());
            Set<Course> courses = studentService.findById(id).getCourseSet();
            System.out.println(toPrint.printCourses(courses));
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
    }

    @Override
    public void getNumberOfCoursesByStudents() {
        System.out.println(toPrint.printCourseNumberByStudentsDto(studentService.getNumberOfCoursesByStudents()));
    }

    @Override
    public void delete(BufferedReader reader) throws IOException {
        try {
            System.out.println("Enter the id");
            Long id = Long.parseLong(reader.readLine());
            studentService.delete(id);
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
    }

    @Override
    public void findById(BufferedReader reader) throws IOException {
        try {
            System.out.println("Enter the id");
            Long id = Long.parseLong(reader.readLine());
            System.out.println(toPrint.print(studentService.findById(id)));
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
    }

    @Override
    public void findAll() {
        System.out.println(toPrint.printStudents(studentService.findAll()));
    }
}
