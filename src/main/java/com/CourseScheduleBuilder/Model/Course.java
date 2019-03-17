package com.CourseScheduleBuilder.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {


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
    private String association;
    private String labRequired;
    private String requireEngineer;
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO )
    private Integer id;








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


    @Override
    public String toString() {
        return "CourseDb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preReq='" + preReq + '\'' +
                ", coReq='" + coReq + '\'' +
                '}';
    }
}
