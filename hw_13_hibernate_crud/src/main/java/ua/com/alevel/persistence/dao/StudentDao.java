package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.dto.CourseNumberByStudent;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student> {
    List<CourseNumberByStudent> getNumberOfCoursesByStudents();
}
