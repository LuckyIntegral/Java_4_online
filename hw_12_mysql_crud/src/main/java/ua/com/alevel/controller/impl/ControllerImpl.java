package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.Controller;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.util.PrintUtil;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.service.impl.CourseServiceImpl;
import ua.com.alevel.service.impl.StudentServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;

public class ControllerImpl implements Controller {
    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final PrintUtil console = PrintUtil.getInstance();
    @Override
    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Please choose your option");
            String option;
            menu();
            while ((option = reader.readLine()) != null) {
                crud(option, reader);
                menu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crud(String option, BufferedReader reader) {
        switch (option) {
            case "1" -> createStudent(reader);
            case "2" -> readStudentById(reader);
            case "3" -> readAllStudents();
            case "4" -> updateStudent(reader);
            case "5" -> deleteStudent(reader);
            case "6" -> createCourse(reader);
            case "7" -> readCourseById(reader);
            case "8" -> readAllCourses();
            case "9" -> updateCourse(reader);
            case "10" -> deleteCourse(reader);
            case "11" -> attachStudentToCourse(reader);
            case "12" -> removeStudentFromCourse(reader);
            case "13" -> findAllStudentsInCourseById(reader);
            case "14" -> findAllCoursesOfStudentById(reader);
            case "15" -> findTheNumberOfStudentsForEachCourse();
            case "16" -> findTheNumberOfCoursesForEachStudent();
            case "17" -> exit();
        }
    }

    private void findTheNumberOfCoursesForEachStudent() {
        try {
            console.printStudentsDto(studentService.findTheNumberOfCoursesForEachStudent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findTheNumberOfStudentsForEachCourse() {
        try {
            console.printCoursesDto(courseService.findTheNumberOfStudentsForEachCourse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findAllCoursesOfStudentById(BufferedReader reader) {
        try {
            System.out.println("Enter the student id below");
            Long id = Long.parseLong(reader.readLine());
            console.printCourses(courseService.findCoursesByStudentId(id));
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findAllStudentsInCourseById(BufferedReader reader) {
        try {
            System.out.println("Enter the course id below");
            Long id = Long.parseLong(reader.readLine());
            console.printStudents(studentService.findStudentsByCourseId(id));
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeStudentFromCourse(BufferedReader reader) {
        try {
            System.out.println("Enter student id");
            Long studId = Long.parseLong(reader.readLine());
            System.out.println("Enter course id");
            Long courseId = Long.parseLong(reader.readLine());
            studentService.removeStudentFromCourse(studId, courseId);
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void attachStudentToCourse(BufferedReader reader) {
        try {
            System.out.println("Enter student id");
            Long studId = Long.parseLong(reader.readLine());
            System.out.println("Enter course id");
            Long courseId = Long.parseLong(reader.readLine());
            studentService.attachStudentToCourse(studId, courseId);
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCourse(BufferedReader reader) {
        try {
            System.out.println("Enter course id");
            long id = Long.parseLong(reader.readLine());
            courseService.delete(id);
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCourse(BufferedReader reader) {
        try {
            System.out.println("Enter course id");
            long id = Long.parseLong(reader.readLine());
            Course course = courseService.findById(id);
            if (course == null) {
                System.out.println("Incorrect id");
            } else {
                System.out.println("Enter new name, current - '" + course.getName() + '\'');
                course.setName(reader.readLine());
                System.out.println("Enter new subject, current - '" + course.getSubject() + '\'');
                course.setSubject(reader.readLine());
                courseService.update(id, course);
            }
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readAllCourses() {
        try {
            console.printCourses(courseService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readCourseById(BufferedReader reader) {
        try {
            System.out.println("Enter course id");
            long id = Long.parseLong(reader.readLine());
            Course course = courseService.findById(id);
            if (course == null) {
                System.out.println("Incorrect id");
            } else {
                console.print(course);
            }
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCourse(BufferedReader reader) {
        try {
            System.out.println("Enter course name");
            String name = reader.readLine();
            System.out.println("Enter course subject");
            String subject = reader.readLine();
            Course course = new Course();
            course.setName(name);
            course.setSubject(subject);
            course.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            courseService.create(course);
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent(BufferedReader reader) {
        try {
            System.out.println("Enter student id");
            long id = Long.parseLong(reader.readLine());
            studentService.delete(id);
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStudent(BufferedReader reader) {
        try {
            System.out.println("Enter student id");
            long id = Long.parseLong(reader.readLine());
            Student student = studentService.findById(id);
            if (student == null) {
                System.out.println("Incorrect id");
            } else {
                System.out.println("Enter new name, current - '" + student.getName() + '\'');
                student.setName(reader.readLine());
                System.out.println("Enter new last name, current - '" + student.getLastName() + '\'');
                student.setLastName(reader.readLine());
                System.out.println("Enter new age, current - '" + student.getAge() + '\'');
                student.setAge(Integer.parseInt(reader.readLine()));
                studentService.update(id, student);
            }
        } catch (SecurityException | NumberFormatException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readAllStudents() {
        try {
            console.printStudents(studentService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readStudentById(BufferedReader reader) {
        try {
            System.out.println("Enter student id");
            long id = Long.parseLong(reader.readLine());
            Student student = studentService.findById(id);
            if (student == null) {
                System.out.println("Incorrect id");
            } else {
                console.print(student);
            }
        } catch (SecurityException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createStudent(BufferedReader reader) {
        try {
            System.out.println("Enter student name");
            String name = reader.readLine();
            System.out.println("Enter student last name");
            String lastName = reader.readLine();
            System.out.println("Enter student age");
            int age = Integer.parseInt(reader.readLine());
            Student student = new Student();
            student.setName(name);
            student.setLastName(lastName);
            student.setAge(age);
            student.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            studentService.create(student);
        } catch (SecurityException | NumberFormatException e) {
            System.out.println("Incorrect format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        System.exit(0);
    }

    private void menu() {
        System.out.println();
        System.out.println("--------------------------MAIN--MENU--------------------------");
        System.out.println("If you want to create a student, enter --------------------- 1");
        System.out.println("If you want to find the student by id, enter --------------- 2");
        System.out.println("If you want to read all students, enter -------------------- 3");
        System.out.println("If you want to update the student, enter ------------------- 4");
        System.out.println("If you want to delete the student, enter ------------------- 5");
        System.out.println("--------------------------------------------------------------");
        System.out.println("If you want to create a course, enter ---------------------- 6");
        System.out.println("If you want to find the course by id, enter ---------------- 7");
        System.out.println("If you want to read all courses, enter --------------------- 8");
        System.out.println("If you want to update the course, enter -------------------- 9");
        System.out.println("If you want to delete the course, enter ------------------- 10");
        System.out.println("--------------------------------------------------------------");
        System.out.println("If you want to attach the student to course, enter -------- 11");
        System.out.println("If you want to remove the student from course, enter ------ 12");
        System.out.println("If you want to find all students in the course, enter ----- 13");
        System.out.println("If you want to find all courses of a student, enter ------- 14");
        System.out.println("If you want to find the number of students in each course - 15");
        System.out.println("If you want to find the number of courses for each student  16");
        System.out.println("If you want to exit the application, enter ---------------- 17");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
    }
}
