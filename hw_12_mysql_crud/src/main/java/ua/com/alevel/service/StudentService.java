package ua.com.alevel.service;

import ua.com.alevel.persistence.dto.StudentDto;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<Student> {
    void attachStudentToCourse(Long studId, Long courseId);
    void removeStudentFromCourse(Long studId, Long courseId);
    List<Student> findStudentsByCourseId(Long id);
    List<StudentDto> findTheNumberOfCoursesForEachStudent();
}
