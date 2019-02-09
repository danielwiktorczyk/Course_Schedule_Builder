package com.courseBuilder.courseBuilderAPI.service;
import java.util.*;
import com.courseBuilder.courseBuilderAPI.domain.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService{


     List<Student> findAll();


     List<Student> findById(long studentId);

     List<Student> findByFullName(String fullName);


     List<Student> findByUserName(String userName);


     void saveOrUpdateStudent(Student student);


    //TODO add more if needed


}
