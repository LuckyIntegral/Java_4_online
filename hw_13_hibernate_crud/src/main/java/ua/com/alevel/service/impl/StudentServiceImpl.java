package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.dao.impl.CourseDaoImpl;
import ua.com.alevel.persistence.dao.impl.StudentDaoImpl;
import ua.com.alevel.persistence.dto.CourseNumberByStudent;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao = new StudentDaoImpl();
    private final CourseDao courseDao = new CourseDaoImpl();
    @Override
    public void create(Student entity) {
        studentDao.create(validation(entity));
    }

    @Override
    public void update(Student entity) {
        studentDao.update(validation(entity));
    }

    @Override
    public List<CourseNumberByStudent> getNumberOfCoursesByStudents() {
        return studentDao.getNumberOfCoursesByStudents();
    }

    @Override
    public void delete(Long id) {
        Optional<Student> student = studentDao.findById(id);
        if (student.isPresent()) {
            Set<Course> courses = student.get().getCourseSet();
            for (Course course : courses) {
                Set<Student> students = course.getStudentSet();
                students.remove(student.get());
                courseDao.update(course);
            }
            student.get().setCourseSet(new HashSet<>());
            studentDao.update(student.get());
            studentDao.delete(student.get());
        } else {
            System.out.println("Incorrect id");
        }
    }

    @Override
    public Student findById(Long id) {
        Optional<Student> student = studentDao.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new SecurityException();
        }
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    private Student validation(Student entity) {
        if (!entity.getFirstName().matches("^[A-Za-z0-9_-]{3,15}$")) {
            System.out.println("Invalid name");
            entity.setFirstName("Unknown");
        } else if (!entity.getLastName().matches("^[A-Za-z0-9_-]{3,15}$")) {
            System.out.println("Invalid last name");
            entity.setLastName("Unknown");
        } else if (entity.getAge() > 100 || entity.getAge() < 0) {
            System.out.println("Invalid age");
            entity.setAge(0);
        }
        return entity;
    }
}
