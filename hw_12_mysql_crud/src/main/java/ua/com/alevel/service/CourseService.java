package ua.com.alevel.service;

import ua.com.alevel.persistence.dto.CourseDto;
import ua.com.alevel.persistence.entity.Course;

import java.util.List;

public interface CourseService extends BaseService<Course> {
    List<Course> findCoursesByStudentId(Long id);
    List<CourseDto> findTheNumberOfStudentsForEachCourse();
}
