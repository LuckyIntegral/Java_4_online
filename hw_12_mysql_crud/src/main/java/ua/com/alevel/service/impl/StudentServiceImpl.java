package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.dao.impl.StudentDaoImpl;
import ua.com.alevel.persistence.dto.StudentDto;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao = new StudentDaoImpl();
    @Override
    public void create(Student entity) {
        if (!entity.getName().matches("^[A-Za-z0-9_-]{3,15}$")) {
            throw new SecurityException();
        } else if (!entity.getLastName().matches("^[A-Za-z0-9_-]{3,15}$")) {
            throw new SecurityException();
        } else if (entity.getAge() < 0 || entity.getAge() > 100) {
            throw new SecurityException();
        } else {
            studentDao.create(entity);
        }
    }

    @Override
    public Student findById(Long id) {
        if (id < 0 ) {
            throw new SecurityException();
        }
        Optional<Student> optional =  studentDao.findById(id);
        if (optional.isEmpty()) {
            throw new SecurityException();
        } else {
            return optional.get();
        }
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void update(Long id, Student entity) {
        if (id < 0 ) {
            throw new SecurityException();
        }
        if (!entity.getName().matches("^[A-Za-z0-9_-]{3,15}$")) {
            throw new SecurityException();
        } else if (!entity.getLastName().matches("^[A-Za-z0-9_-]{3,15}$")) {
            throw new SecurityException();
        } else if (entity.getAge() < 0 || entity.getAge() > 100) {
            throw new SecurityException();
        } else {
            studentDao.update(id, entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (id < 0 ) {
            throw new SecurityException();
        }
        studentDao.delete(id);
    }

    @Override
    public void attachStudentToCourse(Long studId, Long courseId) {
        if (studId < 0 || courseId < 0) {
            throw new SecurityException();
        }
        studentDao.attachStudentToCourse(studId, courseId);
    }

    @Override
    public void removeStudentFromCourse(Long studId, Long courseId) {
        if (studId < 0 || courseId < 0) {
            throw new SecurityException();
        }
        studentDao.removeStudentFromCourse(studId, courseId);
    }

    @Override
    public List<Student> findStudentsByCourseId(Long id) {
        if (id < 0 ) {
            throw new SecurityException();
        }
        return studentDao.findStudentsByCourseId(id);
    }

    @Override
    public List<StudentDto> findTheNumberOfCoursesForEachStudent() {
        return studentDao.findTheNumberOfCoursesForEachStudent();
    }
}
