package com.courseBuilder.courseBuilderAPI.repositories;

import java.util.*;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.courseBuilder.courseBuilderAPI.domain.Student;

public interface StudentRepositories extends MongoRepository<Student, String>{



        List<Student> findByUserName(String userName);

        List<Student> findById(long studentId);

        List<Student> findByFullName(String fullName);


        //TODO add more if needed


}
