package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.dao.impl.CourseDaoImpl;
import ua.com.alevel.persistence.dto.CourseDto;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.service.CourseService;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao = new CourseDaoImpl();
    @Override
    public void create(Course entity) {
        if (!entity.getName().matches("^[A-Za-z0-9_ -]{3,15}$")) {
            throw new SecurityException();
        } else if (!entity.getSubject().matches("^[A-Za-z0-9_-]{3,15}$")) {
            throw new SecurityException();
        } else {
            courseDao.create(entity);
        }
    }

    @Override
    public Course findById(Long id) {
        if (id < 0) {
            throw new SecurityException();
        }
        Optional<Course> optional = courseDao.findById(id);
        if (optional.isEmpty()) {
            throw new SecurityException();
        } else {
            return optional.get();
        }
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public void update(Long id, Course entity) {
        if (id < 0) {
            throw new SecurityException();
        }
        if (!entity.getName().matches("^[A-Za-z0-9_ -]{3,15}$")) {
            throw new SecurityException();
        } else if (!entity.getSubject().matches("^[A-Za-z0-9_-]{3,15}$")) {
            throw new SecurityException();
        } else {
            courseDao.update(id, entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (id < 0) {
            throw new SecurityException();
        }
        courseDao.delete(id);
    }

    @Override
    public List<Course> findCoursesByStudentId(Long id) {
        if (id < 0) {
            throw new SecurityException();
        }
        return courseDao.findCoursesByStudentId(id);
    }

    @Override
    public List<CourseDto> findTheNumberOfStudentsForEachCourse() {
        return courseDao.findTheNumberOfStudentsForEachCourse();
    }
}
