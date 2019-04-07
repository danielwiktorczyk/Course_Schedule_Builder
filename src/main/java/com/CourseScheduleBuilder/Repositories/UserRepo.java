package com.CourseScheduleBuilder.Repositories;

import com.CourseScheduleBuilder.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String> {



    List<User> findByActive(int i);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByUsernameAndPassword(String username, String password);

}

