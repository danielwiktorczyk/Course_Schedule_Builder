package com.CourseScheduleBuilder.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class User implements Cloneable{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO )
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean EWT;
    private Schedule fallSchedule;
    private Schedule winterSchedule;
    private Schedule summerSchedule;
    private ArrayList<String> prereqs = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEWT() {
        return EWT;
    }

    public void setEWT(boolean EWT) {
        this.EWT = EWT;
    }

    public void addToPrereqs(String prereq){
        this.prereqs.add(prereq);
    }

    public ArrayList<String> getPrereqs() {
        return prereqs;
    }

    @Override
    public String toString() {
        return "CourseDb{" +
                "id=" + id +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Email Address='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Schedule getFallSchedule() {
        return fallSchedule;
    }

    public void setFallSchedule(Schedule fallSchedule) {
        this.fallSchedule = fallSchedule;
    }

    public Schedule getWinterSchedule() {
        return winterSchedule;
    }

    public void setWinterSchedule(Schedule winterSchedule) {
        this.winterSchedule = winterSchedule;
    }

    public Schedule getSummerSchedule() {
        return summerSchedule;
    }

    public void setSummerSchedule(Schedule summerSchedule) {
        this.summerSchedule = summerSchedule;
    }

    public void setPrereqs(ArrayList<String> prereqs) {
        this.prereqs = prereqs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        User returnUser = (User) super.clone();
        returnUser.setPrereqs((ArrayList) returnUser.getPrereqs().clone());
        return returnUser;
    }
}
