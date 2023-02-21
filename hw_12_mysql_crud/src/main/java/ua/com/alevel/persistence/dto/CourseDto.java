package ua.com.alevel.persistence.dto;

import ua.com.alevel.persistence.entity.Course;

public record CourseDto(Course course, int number) {
    @Override
    public String toString() {
        return "CourseDto{" +
                "course=" + course +
                ", number=" + number +
                '}';
    }
}
