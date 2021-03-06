package com.CourseScheduleBuilder.Model;

import java.io.Serializable;

public class CourseTrio implements Cloneable, Serializable {
    private Course lecture;
    private Course tutorial;
    private Course lab;
    private boolean hasLab;

    public CourseTrio(){

    }

    public CourseTrio(Course lecture, Course tutorial, Course lab) {
        this.lecture = lecture;
        this.tutorial = tutorial;
        this.lab = lab;
        this.hasLab = true;
    }

    public CourseTrio(Course lecture, Course tutorial) {
        this.lecture = lecture;
        this.tutorial = tutorial;
        this.hasLab = false;
    }

    public Course getLecture() {
        return lecture;
    }

    public void setLecture(Course lecture) {
        this.lecture = lecture;
    }

    public Course getTutorial() {
        return tutorial;
    }

    public void setTutorial(Course tutorial) {
        this.tutorial = tutorial;
    }

    public Course getLab() {
        return lab;
    }

    public void setLab(Course lab) {
        this.lab = lab;
    }

    public boolean isHasLab() {
        return hasLab;
    }

    public void setHasLab(boolean hasLab) {
        this.hasLab = hasLab;
    }


    void printInfo(){
        System.out.println("Lecture: " + this.lecture.getName() + "   " + this.lecture.getId() + "   " + this.lecture.getStartTime() + "   " + this.lecture.getEndTime() + "   " +this.lecture.getComponent() + "  " + this.lecture.getClassDays()[0] + "  " + this.lecture.getClassDays()[1]+ "  " + this.lecture.getClassDays()[2]+ "  " + this.lecture.getClassDays()[3]+ "  " + this.lecture.getClassDays()[4] );
        System.out.println("Tutorial: " + this.tutorial.getName() + "   " + this.tutorial.getId() + "   " + this.tutorial.getStartTime() + "   " + this.tutorial.getEndTime() + "   " + this.tutorial.getComponent()+ "  " + this.tutorial.getClassDays()[0] + "  " + this.tutorial.getClassDays()[1]+ "  " + this.tutorial.getClassDays()[2]+ "  " + this.tutorial.getClassDays()[3]+ "  " + this.tutorial.getClassDays()[4] );
        if (this.hasLab)
        System.out.println("Lab: " + this.lab.getName() + "   " + this.lab.getId() + "   " + this.lab.getStartTime() + "   " + this.lab.getEndTime() + "   " + this.lab.getComponent() + "  " + this.lab.getClassDays()[0] + "  " + this.lab.getClassDays()[1]+ "  " + this.lab.getClassDays()[2]+ "  " + this.lab.getClassDays()[3]+ "  " + this.lab.getClassDays()[4] );
    }

    boolean compareTo(CourseTrio courseTrio){
        if(this.tutorial.getId().equals(courseTrio.tutorial.getId()))
            return true;
        if(this.lecture.getId().equals(courseTrio.lecture.getId()))
            return true;
        if(this.hasLab){
            return this.lab.getId().equals(this.lab.getId());
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CourseTrio newCourseTrio = (CourseTrio) super.clone();
        newCourseTrio.lecture = (Course)this.lecture.clone();
        newCourseTrio.tutorial = (Course) this.tutorial.clone();
        if(hasLab)
            newCourseTrio.lab = (Course) this.lab.clone();
        return newCourseTrio;
    }

}
