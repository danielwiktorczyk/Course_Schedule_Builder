package com.courseBuilder.courseBuilderAPI.service;
import java.util.*;
import com.courseBuilder.courseBuilderAPI.domain.Courses;


public interface CourseService {


    List<Courses> findAll();

    List<Courses> findBycourseId(String courseId);
    List<Courses> findBycourseName(String CourseName);



    void saveOrUpdateCourse(Courses course);

    void deleteCourse(Courses course);

    //TODO add more if needed

}
