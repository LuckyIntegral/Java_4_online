package ua.com.alevel.persistence.util;

import ua.com.alevel.persistence.dto.CourseDto;
import ua.com.alevel.persistence.dto.StudentDto;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public class PrintUtil {
    private static PrintUtil printUtil;
    private PrintUtil() {}

    public static PrintUtil getInstance() {
        if (printUtil == null) {
            printUtil = new PrintUtil();
        }
        return printUtil;
    }

    private static final String studentFields = "ID  Name           Surname        Age Created Time         ";
    private static final String courseFields = "ID  Course Name    Subject        Created Time         ";
    private static final String studentDtoFields = "ID  Name           Surname        Age Created Time         Amount";
    private static final String courseDtoFields = "ID  Course Name    Subject        Created Time         Amount";



    public void printCourses(List<Course> courses) {
        System.out.println(courseFields);
        for (Course course : courses) {
            System.out.println(string(course));
        }
    }

    public void printStudents(List<Student> studentList) {
        System.out.println(studentFields);
        for (Student student : studentList) {
            System.out.println(string(student));
        }
    }

    public void printCoursesDto(List<CourseDto> courses) {
        System.out.println(courseDtoFields);
        for (CourseDto course : courses) {
            System.out.println(string(course));
        }
    }

    public void printStudentsDto(List<StudentDto> studentList) {
        System.out.println(studentDtoFields);
        for (StudentDto student : studentList) {
            System.out.println(string(student));
        }
    }

    public void print(Course course) {
        System.out.println(courseFields);
        System.out.println(string(course));
    }

    public void print(Student student) {
        System.out.println(studentFields);
        System.out.println(string(student));
    }

    private String string(CourseDto d) {
        return string(d.course()) + "   " + d.number();
    }

    private String string(Course c) {
        return c.getId() + " ".repeat(4 - String.valueOf(c.getId()).length()) +
                c.getName() + " ".repeat(15 - c.getName().length()) +
                c.getSubject() + " ".repeat(15 - c.getSubject().length()) +
                c.getCreatedTime();
    }

    private String string(StudentDto d) {
        return string(d.student()) + "   " + d.number();
    }

    private String string(Student s) {
        return s.getId() + " ".repeat(4 - String.valueOf(s.getId()).length()) +
                s.getName() + " ".repeat(15 - s.getName().length()) +
                s.getLastName() + " ".repeat(15 - s.getLastName().length()) +
                s.getAge() + " ".repeat(4 - String.valueOf(s.getAge()).length()) +
                s.getCreatedTime();
    }
}
