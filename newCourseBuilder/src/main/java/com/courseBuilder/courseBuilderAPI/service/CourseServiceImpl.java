package com.courseBuilder.courseBuilderAPI.service;

import com.courseBuilder.courseBuilderAPI.domain.Courses;
import com.courseBuilder.courseBuilderAPI.repositories.CoursesRepositories;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowire;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{




    public List<Courses> findAll() {
        return null;
    }


    public List<Courses> findBycourseId(String courseId) {
        return null;
    }


    public List<Courses> findBycourseName(String CourseName) {
        return null;
    }


    public void saveOrUpdateCourse(Courses course) {

    }


    public void deleteCourse(Courses course) {

    }
}
