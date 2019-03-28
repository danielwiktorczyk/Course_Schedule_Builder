package com.CourseScheduleBuilder.Repositories;

import com.CourseScheduleBuilder.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository <Course, String> {



   List<Course> findByName(String name);
   List<Course> findByNameAndComponent(String name, String component);
   List<Course> findByNameAndComponentAndTerm(String name, String component,String term);


}

