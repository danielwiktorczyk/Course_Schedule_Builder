package com.CourseScheduleBuilder;

import com.CourseScheduleBuilder.Model.Schedule;
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

        User user1 = new User();
        user1.setFirstName("Moataz");
        user1.setLastName("Fawzy");
        user1.setPassword("SOEN341");
        user1.setUsername("moataz_fawzy@live.com");
        user1.setEWT(false);
        user1.addToPrereqs("COMP348");
        user1.addToPrereqs("COMP352");
        user1.addToPrereqs("SOEN391");
        user1.addToPrereqs("COMP248");


        userRepo.save(user1);

        User user2 = new User();
        user2.setFirstName("Terrill");
        user2.setLastName("Fancott");
        user2.setUsername("SuperMan@live.com");
        user2.setPassword("20BONUSPOINTS");
        user2.setEWT(false);
        user2.addToPrereqs("ENGR233");

        Schedule s = new Schedule();


        userRepo.save(user2);

        List<User> userData = userRepo.findAll();

        for (User User : userData) {
            LOG.info("Course found :" + User.toString());

        }

        User resultUser = userRepo.findByFirstName("Moataz");
        LOG.info("User found by name : " + resultUser.toString());

        List<User> results = userRepo.findByUsernameAndPassword("SuperMan@live.com", "20BONUSPOINTS");

        for (User User : results) {
            LOG.info("Matching results are : " + User.toString());
        }

    }
}
