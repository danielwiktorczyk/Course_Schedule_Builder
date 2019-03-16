package com.CourseScheduleBuilder.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class loggedInUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;
    private String name;
    private String user;
    private String email;

    public loggedInUser(String email){
        user = "user";
        this.email = email;
    }


}
