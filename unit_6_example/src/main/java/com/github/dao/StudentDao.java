package com.github.dao;

import com.github.entity.Student;

import java.util.List;

public interface StudentDao {
    void create(Student student);

    void update(Student student);

    void delete(String id);

    List<Student> findAll();

    Student findById(String id);
}
