package ua.com.alevel.persistence.dto;

import ua.com.alevel.persistence.entity.Student;

public record CourseNumberByStudent(Student student, long number) {
}
