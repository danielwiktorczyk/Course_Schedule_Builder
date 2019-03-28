package com.CourseScheduleBuilder.Repositories;

import com.CourseScheduleBuilder.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String> {


    User findByFirstName(String firstName);
    User findByUserName(String username);
    User findByEmail(String email);
    List<User> findByUserNameAndPassword(String username, String password);

}

