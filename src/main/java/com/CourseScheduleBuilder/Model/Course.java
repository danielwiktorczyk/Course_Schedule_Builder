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
    private String neverTaken;
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
    private String day;
    private Integer enrolmentCap;
    private Integer numberCurrentlyEnrolled;
    private String associatedLecture;
    private String associatedTutorial;
    private String associatedLab;
    private String requireEngineer;



    //the term must be summer, fall or winter
    //
    //subject is the general subject category, SOEN, ENCS, ENGR, etc...
    //courseNumber is the number that identifies a course --> eg. 248 in COMP248
    //component of the course indicates LEC, LAB, TUT
    //
    //section identifier is used to identify associated course components, not unique,
    //must be used with other identifiers such as subject, course number, term
    //associated lab/tutorial/lecture indicate which labs and tutorials belong to which lecture
    //
    //the if a course must be taught by an engineer, the requireEngineer attribute contains the section(s)
    //suitable for SOEN students, if the course does not have to be taught by an engineer, the flag NOENG is used.







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

    @Override
    public String toString() {
        return "CourseDb{" +
                term + " " + " " + section + " " + subject + courseNumber + " " + component + "\n" +
                name + "\n" +
                "prequsite(s): " + preReq + "\n" +
                "corequsite(s): " + coReq + "\n" +
                "course location: " + location + '\'' +
                ", day: " + day + '\'' +
                ", start time='" + startTime + '\'' +
                ", end time='" + endTime + "\n" +
                "enrollment status: " + numberCurrentlyEnrolled + "/" + enrolmentCap + '\'' +
                '}';
    }
}
