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
    private ArrayList<UserPreferences> userPrefs = new ArrayList();

    public void modifyUserPrefs(UserPreferences newPreference) {
        if(newPreference.isAdd()){
            addPref(newPreference);
        }
        else{
            removePref(newPreference);
        }
    }
    /*
    Method that modifies userPrefs when they are updated by the user
    uses helper methods addPref() and removePref() defined below
     */

    public void addPref(UserPreferences newPreference) {
        if (userPrefs.size() == 0) {
            userPrefs.add(newPreference);
        } else {
            for (int i = 0; i < userPrefs.size(); i++) {
                if (userPrefs.get(i).compareDays(newPreference) && userPrefs.get(i).timeOverlap(newPreference)) {
                    if (userPrefs.get(i).getStartTime() < newPreference.getStartTime()) {
                        newPreference.setStartTime(userPrefs.get(i).getStartTime());
                    }
                    if (userPrefs.get(i).getEndTime() > newPreference.getEndTime()) {
                        newPreference.setEndTime(userPrefs.get(i).getEndTime());
                    }
                    userPrefs.add(newPreference);
                }
            }
        }
    }

    public void removePref(UserPreferences newPreference) {
        for (int i = 0; i < userPrefs.size(); i++) {
            if (userPrefs.get(i).compareDays(newPreference) && userPrefs.get(i).timeOverlap(newPreference)) {
                if (userPrefs.get(i).getStartTime() < newPreference.getEndTime()) {
                    userPrefs.get(i).setStartTime(newPreference.getEndTime());
                }
                if (userPrefs.get(i).getEndTime() < newPreference.getStartTime()) {
                    userPrefs.get(i).setEndTime(userPrefs.get(i).getStartTime());
                }
                if(userPrefs.get(i).getStartTime() > newPreference.getStartTime() && userPrefs.get(i).getEndTime() < newPreference.getEndTime()){
                    userPrefs.remove(i);
                }

            }
        }
    }



    public Integer getId(){
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
