package ua.com.alevel.persistence.dto;

import ua.com.alevel.persistence.entity.Course;

public record StudentNumberByCourseDto(Course course, long number) {
}
