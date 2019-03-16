package com.CourseScheduleBuilder.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO )
    private Integer id;
    private String name;
    private String title;
    private String preReq;
    private String coReq;
    private String term;
    private String section;
    private String component;
    private Integer startTime;
    private Integer endTime;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean online;
    private Integer association;
    private boolean labRequired;
    private String requireEngineer;

    public Course(){

    }

    //getter and setter methods for all attributes

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreReq() {
        return preReq;
    }

    public void setPreReq(String preReq) {
        this.preReq = preReq;
    }

    public String getCoReq() {
        return coReq;
    }

    public void setCoReq(String coReq) {
        this.coReq = coReq;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Integer getAssociation() {
        return association;
    }

    public void setAssociation(Integer association) {
        this.association = association;
    }

    public boolean isLabRequired() {
        return labRequired;
    }

    public void setLabRequired(boolean labRequired) {
        this.labRequired = labRequired;
    }

    public String getRequireEngineer() {
        return requireEngineer;
    }

    public void setRequireEngineer(String requireEngineer) {
        this.requireEngineer = requireEngineer;
    }

    //method to verify if a course course requires a lab component
    //labs are not associated with specific sections so if required the lab, must just be in the same term as the course
    //and match it's name

    public boolean doesCourseHaveLab(){
        if (labRequired == true){
            return true;
        }
        else{
            return false;
        }
    }
    
    //tutorials are affiliated with only specific lecture sections,
    // associated components share an identical association number
    //this method identifies associated lectures and tutorials
    public boolean affiliatedTutorial(Course tutorialToBeChecked){
        return tutorialToBeChecked.component.equalsIgnoreCase("tut") && association.equals(tutorialToBeChecked.association);
    }

    //method that returns a string of the days a course is offered
    private String getDays(){
        String daysOffered = "";
        if  (monday) daysOffered = "Monday ";
        else if (tuesday) daysOffered = daysOffered + "Tuesday ";
        else if (wednesday){
            daysOffered = daysOffered + "Wednesday ";
        }
        else if (thursday){
            daysOffered = daysOffered + "Thursday ";
        }
        else if (friday){
            daysOffered = daysOffered + "Friday";
        }
        else {
            daysOffered = "online";
        }
        return daysOffered;
    }

    @Override
    public String toString() {
        return "CourseDb{" +
                term + " " + " " + section + " " + name + " " + component + "\n" +
                title + "\n" +
                "prequsite(s): " + preReq + "\n" +
                "corequsite(s): " + coReq + "\n" +
                ", start time='" + startTime + '\'' +
                ", end time='" + endTime + "\n" +
                ", " + getDays() + "\n" +
                '}';
    }
}
