package com.coursebuilder;

import java.util.List;

public class Student extends User{

    private List<Course> Prereqs; //Subject to change once we decide how to store these.
    private int credits;
    private Preferences preferences;

    public Student(String firstName, String lastName, String username, String password, String address, String dateOfBirth, List<Course> prereqs, int credits, Preferences preferences) {
        super(firstName, lastName, username, password, address, dateOfBirth);
        Prereqs = prereqs;
        this.credits = credits;
        this.preferences = preferences;
    }

    public Student() {
        super();
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}


