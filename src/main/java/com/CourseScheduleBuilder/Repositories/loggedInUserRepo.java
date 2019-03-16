package com.CourseScheduleBuilder.Repositories;

import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Model.loggedInUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface loggedInUserRepo extends JpaRepository <loggedInUser, String> {



    loggedInUser findByName(String name);
    loggedInUser findByEmail(String email);

    loggedInUser findByUser(String user);
}