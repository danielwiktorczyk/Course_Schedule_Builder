package com.courseBuilder.courseBuilderAPI.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;

@Document
public class Student {
        @Id
        String userName;
        String fullName;
        List<Courses> completed;
        long studentId; //TODO Should be generated automatically
        //TODO add more


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Courses> getCompleted() {
        return completed;
    }

    public void setCompleted(List<Courses> completed) {
        this.completed = completed;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
