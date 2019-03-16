package com.CourseScheduleBuilder.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO )
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean EWT;
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


}
