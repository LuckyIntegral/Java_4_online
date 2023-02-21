package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.dto.StudentDto;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student> {
    void attachStudentToCourse(Long studId, Long courseId);
    void removeStudentFromCourse(Long studId, Long courseId);
    List<Student> findStudentsByCourseId(Long id);
    List<StudentDto> findTheNumberOfCoursesForEachStudent();
}
