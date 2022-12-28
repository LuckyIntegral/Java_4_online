package com.github.service;

import com.github.config.StudentFactory;
import com.github.dao.StudentDao;
import com.github.dao.StudentDao1;
import com.github.entity.Student;

import java.util.List;
public class StudentService {

//    private final StudentDao studentDao = (StudentDao) StudentFactory.getImplementationByClass(StudentDao.class);
    private final StudentDao studentDao = new StudentDao1();

    public void create(Student student) {
        studentDao.create(student);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void delete(String id) {
        studentDao.delete(id);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public Student findByID(String id) {
        return studentDao.findById(id);
    }
}
