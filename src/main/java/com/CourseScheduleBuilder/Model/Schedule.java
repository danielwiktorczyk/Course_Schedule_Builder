package com.CourseScheduleBuilder.Model;

import java.io.Serializable;
import java.util.Arrays;

public class Schedule implements Cloneable, Serializable {
    private CourseTrio[] courses;
    private int size;

    public Schedule(){
        courses = new CourseTrio[6];
        this.size = 0;
    }

    public CourseTrio[] getCourseTrio() {
        return courses;
    }

    public void setCourses(CourseTrio[] courses) {
        this.courses = courses;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void insertCourse(CourseTrio course){
        if (this.size == this.courses.length){
                courses = Arrays.copyOf(courses, courses.length+1);// increment by +1
        }
        this.courses[this.size] = course;
        this.size++;

    }

    public int labCount(){
        int labCount = 0;
        for (int i=0;i<size;i++){
            if(courses[i].isHasLab())
                labCount++;
        }
        return labCount;
    }

    public void printSchedule(){
        System.out.println("--------------------------");
        for (int i=0; i<size; i++){
            this.courses[i].printInfo();
        }
        System.out.println("--------------------------");
    }

    public boolean compareTo(Schedule schedule){
        for(int i=0;i<size;i++){
            if(this.getCourseTrio()[i].compareTo(schedule.getCourseTrio()[i]))
                return true;
        }
        return false;
    }

    public void adjustLength(){
        this.courses = Arrays.copyOf(this.courses,size);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Schedule returnSchedule = (Schedule) super.clone();
        returnSchedule.courses = this.courses.clone();
        return returnSchedule;
    }


}
