package com.example.springbootwithreactjs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class loginUser implements Serializable {
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public loginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public loginUser(){
        this.username= "hello";
        this.password= "world";
    }
}
