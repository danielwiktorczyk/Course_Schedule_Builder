package com.CourseScheduleBuilder.Model;

import javax.persistence.*;
import java.util.Set;

/**
 * To give a user either basic privilege or admin
 */
@Entity
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO )
    private long id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}