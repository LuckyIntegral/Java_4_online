package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.CourseController;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.type.CourseType;
import ua.com.alevel.persistence.util.PrintUtils;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.service.impl.CourseServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

public class CourseControllerImpl implements CourseController {
    private final CourseService service = new CourseServiceImpl();
    private final PrintUtils toPrint = PrintUtils.getInstance();
    @Override
    public void create(BufferedReader reader) throws IOException {
        Course course = new Course();
        System.out.println("Enter the name");
        course.setName(reader.readLine());
        System.out.println("Enter the type, available types: JAVA, KOTLIN, SCALA, SHARP, PYTHON");
        course.setType(CourseType.valueOf(reader.readLine()));
        course.setUpdated(new Date());
        service.create(course);
    }

    @Override
    public void getNumberOfStudentByCourses() {
        System.out.println(toPrint.printStudentNumberByCoursesDto(service.getNumberOfStudentByCourses()));
    }

    @Override
    public void findAllStudentsByCourse(BufferedReader reader) {
        try {
            System.out.println("Please enter the student id");
            Long id = Long.parseLong(reader.readLine());
            Set<Student> students = service.findById(id).getStudentSet();
            System.out.println(toPrint.printStudents(students));
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
    }

    @Override
    public void update(BufferedReader reader) throws IOException {
        System.out.println("Enter the course id");
        try {
            Long id = Long.parseLong(reader.readLine());
            Course course = service.findById(id);
            if (course != null) {
                System.out.println("Enter the new name, current is '" + course.getName() + '\'');
                course.setName(reader.readLine());
                System.out.println("Enter the type, available types: JAVA, KOTLIN, SCALA, SHARP, PYTHON");
                System.out.println("Current type is '" + course.getType() + '\'');
                course.setType(CourseType.valueOf(reader.readLine()));
                course.setUpdated(new Date());
                service.update(course);
            }
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        } catch (Exception e) {
            System.out.println("Unknown exception, try again");
        }
    }

    @Override
    public void delete(BufferedReader reader) throws IOException {
        try {
            System.out.println("Enter the id");
            Long id = Long.parseLong(reader.readLine());
            service.delete(id);
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        }
    }

    @Override
    public void findById(BufferedReader reader) throws IOException {
        try {
            System.out.println("Enter the id");
            Long id = Long.parseLong(reader.readLine());
            System.out.println(toPrint.print(service.findById(id)));
        } catch (SecurityException e) {
            System.out.println("Invalid id");
        }
    }

    @Override
    public void findAll() {
        System.out.println(toPrint.printCourses(service.findAll()));
    }
}
