package com.github.db;

import com.github.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DbStudent1 {

    private final List<Student> students = new ArrayList<>();
    private static DbStudent1 instance;

    private DbStudent1() { }

    public static DbStudent1 getInstance() {
        if (instance == null) {
            instance = new DbStudent1();
        }
        return instance;
    }

    public void create(Student student) {
        student.setId(generateId());
        students.add(student);
    }

    public void update(Student student) {
        Optional<Student> optionalStudent = findById(student.getId());
        if (optionalStudent.isPresent()) {
            Student current = optionalStudent.get();
            current = student;
        }
    }

    public void delete(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public List<Student> findAll() {
        return students;
    }

    public Optional<Student> findById(String id) {
        return students
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    private String generateId() {
        String id = UUID.randomUUID().toString().substring(0, 8);
        if (students.stream().anyMatch(s -> s.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
