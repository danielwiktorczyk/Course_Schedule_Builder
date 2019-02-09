package com.courseBuilder.courseBuilderAPI.repositories;

import java.util.*;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.courseBuilder.courseBuilderAPI.domain.Courses;

public interface CoursesRepositories extends MongoRepository<Courses, String>{

    List<Courses> findByCourseID(String courseId);

    List<Courses> findByPreRequisiteID(String preRequisiteId);

    List<Courses> findByCoRequisiteID(String CoRequisiteId);

    List<Courses> findByCourseName(String courseName);

    //TODO add more if needed

}
