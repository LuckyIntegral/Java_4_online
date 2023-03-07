package ua.com.alevel.service;

import ua.com.alevel.persistence.dto.CourseNumberByStudent;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<Student> {
    List<CourseNumberByStudent> getNumberOfCoursesByStudents();
}
