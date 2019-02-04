package com.coursebuilder;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleBuilder {
    private List<Schedule> schedules; //Storage method to be decided.





    public void addCourse(){

    }

    public void deleteCourse(){

    }

    public Course SearchCourse(){

        return new Course(); // returns a course with all possible sections.
    }

    public void changeAvailabilities(Student student){
        // this.getPreferences() = ???

    }

    public Preferences checkPreferences(Student student){
        return student.getPreferences();
    }

    public void generateSchedules(Preferences preferences){

    }



}
