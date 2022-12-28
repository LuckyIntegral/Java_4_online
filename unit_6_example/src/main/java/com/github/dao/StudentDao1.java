package com.github.dao;

import com.github.config.Release;
import com.github.db.DbStudent1;
import com.github.entity.Student;

import java.util.List;

@Release
public class StudentDao1 implements StudentDao {

    DbStudent1 dbStudents = DbStudent1.getInstance();

    public StudentDao1() {
        System.out.println("dbStudents1");
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
