package com.CourseScheduleBuilder;

import com.CourseScheduleBuilder.Model.Schedule;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Services.UserDetailsServiceImpl;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private CourseRepo courseRepo;
    private UserRepo userRepo;
    private UserDetailsServiceImpl userDetailsService;

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
        user1.setPassword("aaa");
        user1.setEmail("moataz_fawzy@live.com");
        user1.setUsername("aaa");
        user1.setEWT(false);
        user1.addToPrereqs("COMP348");
        user1.addToPrereqs("COMP352");
        user1.addToPrereqs("SOEN391");
        user1.addToPrereqs("COMP248");


        userRepo.save(user1);

        User user2 = new User();
        user2.setFirstName("Terrill");
        user2.setLastName("Fancott");
        user2.setEmail("SuperMan@live.com");
        user2.setUsername("bbb");
        user2.setPassword("bbb");
        user2.setEWT(false);
        user2.addToPrereqs("ENGR233");

        Schedule s = new Schedule();


        userRepo.save(user2);

        List<User> userData = userRepo.findAll();

        for (User User : userData) {
            LOG.info("Course found :" + User.toString());

        }

       List<User> resultUser = userRepo.findByFirstName("Moataz");
        LOG.info("User found by name : " + resultUser.get(0).toString());

        List<User> results = userRepo.findByUsernameAndPassword("SuperMan@live.com", "20BONUSPOINTS");

        for (User User : results) {
            LOG.info("Matching results are : " + User.toString());
        }


        /**
         * This will create the h2 databse server, then start it
         * then it will execute a few SQL commands and load the csv file
         * YOU HAVE TO ADD THE MODULE OF H2 //TODO TODO  but only once
         * 3 - File -> Project structure -> libraries
         * 4 - click on  the '+' above all the maven libraries and click on Java
         * 5 - It is in the Modules directory ( [..]/Course_Schedule_Builder/Modules/ )
         */
        try {
            Server server = Server.createTcpServer(args).start();
            server = Server.createTcpServer("-tcpAllowOthers").start();
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/DB", "sa", "");


            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE COURSE");
            statement.execute("CREATE TABLE COURSE AS SELECT * FROM CSVREAD('./Course_Database_Table.csv')");
            statement.execute("ALTER TABLE USER ALTER COLUMN FALL_SCHEDULE LONGTEXT");
            statement.execute("ALTER TABLE USER ALTER COLUMN WINTER_SCHEDULE LONGTEXT");
            statement.execute("ALTER TABLE USER ALTER COLUMN SUMMER_SCHEDULE LONGTEXT");
            statement.close();

            conn.close();
            server.stop();
        } catch (Exception e) {
            System.out.println("Something wrong with starting h2");
            e.printStackTrace();
        }


    }

}
