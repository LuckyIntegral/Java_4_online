package ua.com.alevel.service;

import ua.com.alevel.persistence.dto.StudentNumberByCourseDto;
import ua.com.alevel.persistence.entity.Course;

import java.util.List;

public interface CourseService extends BaseService<Course> {
    List<StudentNumberByCourseDto> getNumberOfStudentByCourses();
}
