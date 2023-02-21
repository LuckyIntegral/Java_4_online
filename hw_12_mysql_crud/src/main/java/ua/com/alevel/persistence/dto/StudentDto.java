package ua.com.alevel.persistence.dto;

import ua.com.alevel.persistence.entity.Student;

public record StudentDto(Student student, int number) {
    @Override
    public String toString() {
        return "StudentDto{" +
                "student=" + student +
                ", number=" + number +
                '}';
    }
}
