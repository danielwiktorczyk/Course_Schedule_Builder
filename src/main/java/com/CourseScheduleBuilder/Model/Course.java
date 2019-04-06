package com.CourseScheduleBuilder.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public final class Course implements Cloneable, Serializable {


    private String term;
    private String name;
    private String section;
    private String component;
    private String title;
    private String roomCode;
    private String startTime;
    private String endTime;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String online;
    private String preReq;
    private String coReq;
    private String equivalent;
    private String association;
    private String labRequired;
    private String requireEngineer;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO )
    private Integer id;


    public int getStartTime() {
        return Integer.valueOf(startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return Integer.valueOf(endTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getEquivalent() {
        return equivalent;
    }

    public void setEquivalent(String equivalent) {
        this.equivalent = equivalent;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getLabRequired() {
        return labRequired;
    }

    public void setLabRequired(String labRequired) {
        this.labRequired = labRequired;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public boolean[] getClassDays(){
        boolean[] classDays = new boolean[5];
        classDays[0] = this.monday.equals("TRUE");
        classDays[1] = this.tuesday.equals("TRUE");
        classDays[2] = this.wednesday.equals("TRUE");
        classDays[3] = this.thursday.equals("TRUE");
        classDays[4] = this.friday.equals("TRUE");

        return classDays;
    }

    @Override
    public String toString() {
        return "CourseDb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preReq='" + preReq + '\'' +
                ", coReq='" + coReq + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return term.equals(course.term) &&
                Objects.equals(name, course.name) &&
                Objects.equals(section, course.section) &&
                Objects.equals(component, course.component) &&
                Objects.equals(startTime, course.startTime) &&
                Objects.equals(endTime, course.endTime) &&
                Objects.equals(monday, course.monday) &&
                Objects.equals(tuesday, course.tuesday) &&
                Objects.equals(wednesday, course.wednesday) &&
                Objects.equals(thursday, course.thursday) &&
                Objects.equals(friday, course.friday) &&
                Objects.equals(online, course.online) &&
                Objects.equals(association, course.association);
    }
}


