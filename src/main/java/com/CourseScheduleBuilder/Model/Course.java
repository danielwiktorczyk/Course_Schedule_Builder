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
    private String preReq;
    private String coReq;
    private String equivalent;
    private Integer credit;
    private String term;
    private String section;
    private String subject;
    private Integer courseNumber;
    private String component;
    private String location;
    //check of best format for start and end times
    private String startTime;
    private String endTime;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean online;
    private Integer enrolmentCap;
    private Integer numberCurrentlyEnrolled;
    private Integer association;
    private String requireEngineer;

    public Course(){

    }

    //getter and setter methods for all attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getEquivalent() {
        return equivalent;
    }

    public void setEquivalent(String equivalent) {
        this.equivalent = equivalent;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public Integer getEnrolmentCap() {
        return enrolmentCap;
    }

    public void setEnrolmentCap(Integer enrolmentCap) {
        this.enrolmentCap = enrolmentCap;
    }

    public Integer getNumberCurrentlyEnrolled() {
        return numberCurrentlyEnrolled;
    }

    public void setNumberCurrentlyEnrolled(Integer numberCurrentlyEnrolled) {
        this.numberCurrentlyEnrolled = numberCurrentlyEnrolled;
    }

    public Integer getAssociation() {
        return association;
    }

    public void setAssociation(Integer association) {
        this.association = association;
    }

    public String getRequireEngineer() {
        return requireEngineer;
    }

    public void setRequireEngineer(String requireEngineer) {
        this.requireEngineer = requireEngineer;
    }

    //method to verify that a particular lab goes with a particular course,
    //labs are not associated with specific sections so it is sufficient that the lab
    // be in the same term and have the same courseNumber, returns true if the lab is appropriate and false if not
    public boolean affiliatedLab(Course labTobeChecked){
        return labTobeChecked.component.equalsIgnoreCase("lab") && term.equalsIgnoreCase(labTobeChecked.term) && courseNumber.equals(labTobeChecked.courseNumber);
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
                term + " " + " " + section + " " + subject + courseNumber + " " + component + "\n" +
                name + "\n" +
                "prequsite(s): " + preReq + "\n" +
                "corequsite(s): " + coReq + "\n" +
                "course location: " + location + '\'' +
                ", start time='" + startTime + '\'' +
                ", end time='" + endTime + "\n" +
                ", " + getDays() + "\n" +
                "enrollment status: " + numberCurrentlyEnrolled + "/" + enrolmentCap + '\'' +
                '}';
    }
}
