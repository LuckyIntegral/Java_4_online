package ua.com.alevel.persistence.util;

import ua.com.alevel.persistence.dto.CourseNumberByStudent;
import ua.com.alevel.persistence.dto.StudentNumberByCourseDto;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Student;

import java.util.Collection;
import java.util.List;

public class PrintUtils {
    private static PrintUtils printUtils;
    private static final String studentFields = "ID  Name           Surname        Age Created Time            Updated Time         ";
    private static final String courseFields = "ID  Course Name    Type           Created Time            Updated Time         ";
    private static final String studentDtoFields = "ID  Name           Surname        Age Created Time            Updated Time            Amount";
    private static final String courseDtoFields = "ID  Course Name    Type           Created Time            Updated Time            Amount";
    private PrintUtils() {}
    public static PrintUtils getInstance() {
        if (printUtils == null) {
            printUtils = new PrintUtils();
        }
        return printUtils;
    }

    public String printCourses(Collection<Course> courses) {
        if (courses == null || courses.size() == 0) {
            return "<empty>";
        }
        StringBuilder result = new StringBuilder(courseFields + '\n');
        for (Course course : courses) {
            result.append(stringOf(course)).append("\n");
        }
        return result.toString();
    }

    public String printStudents(Collection<Student> students) {
        if (students == null || students.size() == 0) {
            return "<empty>";
        }
        StringBuilder result = new StringBuilder(studentFields + '\n');
        for (Student student : students) {
            result.append(stringOf(student)).append("\n");
        }
        return result.toString();
    }

    public String printStudentNumberByCoursesDto(List<StudentNumberByCourseDto> courses) {
        if (courses == null || courses.size() == 0) {
            return "<empty>";
        }
        StringBuilder result = new StringBuilder(courseDtoFields + '\n');
        for (StudentNumberByCourseDto course : courses) {
            result.append(stringOf(course)).append("\n");
        }
        return result.toString();
    }

    public String printCourseNumberByStudentsDto(List<CourseNumberByStudent> students) {
        if (students == null || students.size() == 0) {
            return "<empty>";
        }
        StringBuilder result = new StringBuilder(studentDtoFields + '\n');
        for (CourseNumberByStudent student : students) {
            result.append(stringOf(student)).append("\n");
        }
        return result.toString();
    }

    public String print(Course course) {
        return courseFields + '\n' + stringOf(course);
    }

    public String print(Student student) {
        return studentFields + '\n' + stringOf(student);
    }

    private String stringOf(StudentNumberByCourseDto d) {
        return stringOf(d.course()) + "   " + d.number();
    }

    private String stringOf(Course c) {
        return c.getId() + " ".repeat(4 - String.valueOf(c.getId()).length()) +
                c.getName() + " ".repeat(15 - c.getName().length()) +
                c.getType() + " ".repeat(15 - c.getType().toString().length()) +
                c.getCreated() + " ".repeat(24 - c.getCreated().toString().length()) +
                c.getUpdated();
    }

    private String stringOf(CourseNumberByStudent d) {
        return stringOf(d.student()) + "   " + d.number();
    }

    private String stringOf(Student s) {
        return s.getId() + " ".repeat(4 - String.valueOf(s.getId()).length()) +
                s.getFirstName() + " ".repeat(15 - s.getFirstName().length()) +
                s.getLastName() + " ".repeat(15 - s.getLastName().length()) +
                s.getAge() + " ".repeat(4 - String.valueOf(s.getAge()).length()) +
                s.getCreated() + " ".repeat(24 - s.getCreated().toString().length()) +
                s.getUpdated();
    }
}
