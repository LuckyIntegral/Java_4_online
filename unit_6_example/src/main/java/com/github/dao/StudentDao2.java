package com.github.dao;

import com.github.db.DbStudent2;
import com.github.entity.Student;

import java.util.List;

public class StudentDao2 implements StudentDao {

    DbStudent2 dbStudents = DbStudent2.getInstance();

    public StudentDao2() {
        System.out.println("dbStudents2");
    }

    @Override
    public void create(Student student) {
        dbStudents.create(student);
    }

    @Override
    public void update(Student student) {
        dbStudents.update(student);
    }

    @Override
    public void delete(String id) {
        dbStudents.delete(id);
    }

    @Override
    public List<Student> findAll() {
        return dbStudents.findAll();
    }

    @Override
    public Student findById(String id) {
        return dbStudents.findById(id).get();
    }
}
