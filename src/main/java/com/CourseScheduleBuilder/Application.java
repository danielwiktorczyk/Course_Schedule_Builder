package com.CourseScheduleBuilder;

import com.CourseScheduleBuilder.Model.Course;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private CourseRepo courseRepo;
    private UserRepo userRepo;

    private Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    public void setCourseRepo(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Course course1 = new Course();
        course1.setName("SOen 341");
        course1.setCredit(3);
        course1.setPreReq("comp346");
        course1.setCoReq("ENGR 301");

        courseRepo.save(course1);

        Course course2 = new Course();
        course2.setName("ENGR 233");
        course2.setCredit(3);
        course2.setPreReq("MATH 205");
        course2.setCoReq("ENGR 213");

        courseRepo.save(course2);

        List<Course> courseData = courseRepo.findAll();

        for (Course Course : courseData) {
            LOG.info("Course found :" + Course.toString());

        }

        Course resultCourse = courseRepo.findByName("ENGR 233");
        LOG.info("course found by name : " + resultCourse.toString());

        List<Course> result = courseRepo.findByCreditAndPreReq(4, "MATH 205");

        for (Course Course : result) {
            LOG.info("Matching results are " + Course.toString());
        }


        User user1 = new User();
        user1.setFirstName("Moataz");
        user1.setLastName("Fawzy");
        user1.setPassword("SOEN341");
        user1.setEmail("moataz_fawzy@live.com");


        userRepo.save(user1);

        User user2 = new User();
        user2.setFirstName("Terrill");
        user2.setLastName("Fancott");
        user2.setEmail("SuperMan@live.com");
        user2.setPassword("20BONUSPOINTS");


        userRepo.save(user2);

        List<User> userData = userRepo.findAll();

        for (User User : userData) {
            LOG.info("Course found :" + User.toString());

        }

        User resultUser = userRepo.findByName("Moataz");
        LOG.info("User found by name : " + resultUser.toString());

        List<User> results = userRepo.findByEmailAndPassword("SuperMan@live.com", "20BONUSPOINTS");

        for (User User : results) {
            LOG.info("Matching results are : " + User.toString());
        }
    }
}
