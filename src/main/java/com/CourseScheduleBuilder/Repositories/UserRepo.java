package com.CourseScheduleBuilder.Repositories;

import com.CourseScheduleBuilder.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String> {


    User findByName(String email);

    List<User> findByEmailAndPassword(String email, String password);

}

