package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.dao.impl.CourseDaoImpl;
import ua.com.alevel.persistence.dao.impl.StudentDaoImpl;
import ua.com.alevel.persistence.dto.StudentNumberByCourseDto;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.CourseService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao = new CourseDaoImpl();
    private final StudentDao studentDao = new StudentDaoImpl();
    @Override
    public void create(Course entity) {
        courseDao.create(validation(entity));
    }

    @Override
    public List<StudentNumberByCourseDto> getNumberOfStudentByCourses() {
        return courseDao.getNumberOfStudentByCourses();
    }

    @Override
    public void update(Course entity) {
        courseDao.update(validation(entity));
    }

    @Override
    public void delete(Long id) {
        Optional<Course> course = courseDao.findById(id);
        if (course.isPresent()) {
            Set<Student> studentSet = course.get().getStudentSet();
            for (Student student : studentSet) {
                Set<Course> courses = student.getCourseSet();
                courses.remove(course.get());
                studentDao.update(student);
            }
            course.get().setStudentSet(new HashSet<>());
            courseDao.update(course.get());
            courseDao.delete(course.get());
        } else {
            System.out.println("Incorrect id");
        }
    }

    @Override
    public Course findById(Long id) throws SecurityException {
        Optional<Course> course = courseDao.findById(id);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new SecurityException();
        }
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    private Course validation(Course entity) {
        if (!entity.getName().matches("^[A-Za-z0-9_-]{3,15}$")) {
            entity.setName("Unknown");
            System.out.println("Invalid name");
        }
        return entity;
    }
}
