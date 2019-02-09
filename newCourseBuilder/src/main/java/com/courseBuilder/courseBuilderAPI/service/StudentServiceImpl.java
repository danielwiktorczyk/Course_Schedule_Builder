package com.courseBuilder.courseBuilderAPI.service;

import com.courseBuilder.courseBuilderAPI.domain.Student;
import com.courseBuilder.courseBuilderAPI.repositories.StudentRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    StudentRepositories studentRepositories;

    @Override
    public List<Student> findAll() {
        return studentRepositories.findAll();
    }

    @Override
    public List<Student> findById(long studentId) {
        return studentRepositories.findById(studentId);
    }

    @Override
    public List<Student> findByFullName(String fullName) {
        return studentRepositories.findByFullName(fullName);
    }

    @Override
    public List<Student> findByUserName(String userName) {
        return studentRepositories.findByUserName(userName);
    }

    @Override
    public void saveOrUpdateStudent(Student student) {
        studentRepositories.save(student);

    }





}
