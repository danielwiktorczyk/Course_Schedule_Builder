package com.CourseScheduleBuilder;

import com.CourseScheduleBuilder.Model.Schedule;
import com.CourseScheduleBuilder.Model.User;
import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
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
            statement.execute("ALTER TABLE USER ALTER COLUMN PREREQS LONGTEXT");
            statement.close();

            conn.close();
            server.stop();
        } catch (Exception e) {
            System.out.println("Something wrong with starting h2");
            e.printStackTrace();
        }


        User user1 = new User();
        user1.setFirstName("Moataz");
        user1.setLastName("Fawzy");
        user1.setPassword("abcdef");
        user1.setEmail("moataz_fawzy@live.com");
        user1.setUsername("MoatazF");
        user1.setEWT(false);
        user1.addToPrereqs("COMP248");
        user1.addToPrereqs("COMP232");
        user1.addToPrereqs("ENGR213");
        user1.addToPrereqs("ENGR201");
        user1.addToPrereqs("ECON201");
        user1.addToPrereqs("SOEN228");
        user1.addToPrereqs("COMP249");
        user1.addToPrereqs("SOEN287");
        user1.addToPrereqs("ENGR233");
        user1.addToPrereqs("BIOL206");
        user1.addToPrereqs("COMP352");
        user1.addToPrereqs("COMP348");
        user1.addToPrereqs("ENGR202");
        user1.addToPrereqs("ENCS272");
        user1.addToPrereqs("ENCS282");
        user1.addToPrereqs("ELEC275");
        user1.addToPrereqs("COMP346");
        user1.addToPrereqs("MECH221");
        user1.addToPrereqs("ENGR371");
        user1.addToPrereqs("SOEN341");
        user1.addToPrereqs("SOEN331");
        user1.addToPrereqs("COMP335");
        user1.addToPrereqs("SOEN384");
        user1.addToPrereqs("ENGR391");
        user1.addToPrereqs("SOEN342");
        user1.addToPrereqs("SOEN343");

        userRepo.save(user1);

        User user2 = new User();
        user2.setFirstName("Harry");
        user2.setLastName("Potter");
        user2.setEmail("SuperMan@live.com");
        user2.setUsername("HarryP");
        user2.setPassword("PotterAcc");
        user2.setEWT(false);
        user2.addToPrereqs("COMP248");
        user2.addToPrereqs("COMP232");
        user2.addToPrereqs("ENGR213");
        user2.addToPrereqs("ENGR201");
        user2.addToPrereqs("ECON201");

        Schedule s = new Schedule();


        userRepo.save(user2);

        User user3 = new User();
        user3.setFirstName("Bob");
        user3.setLastName("Baggins");
        user3.setEmail("Bilbro@live.com");
        user3.setUsername("BobB");
        user3.setPassword("1234567");
        user3.setEWT(false);
        user3.addToPrereqs("COMP248");
        user3.addToPrereqs("COMP232");
        user3.addToPrereqs("ENGR213");
        user3.addToPrereqs("ENGR201");
        user3.addToPrereqs("ECON201");
        user3.addToPrereqs("SOEN228");
        user3.addToPrereqs("COMP249");
        user3.addToPrereqs("SOEN287");
        user3.addToPrereqs("ENGR233");
        user3.addToPrereqs("BIOL206");

        userRepo.save(user3);

        User user4 = new User();
        user4.setFirstName("Omar");
        user4.setLastName("Baggins");
        user4.setEmail("OmarB@live.com");
        user4.setUsername("OmarB");
        user4.setPassword("OmarPassword");
        user4.setEWT(false);
        user4.addToPrereqs("COMP248");
        user4.addToPrereqs("COMP232");
        user4.addToPrereqs("ENGR213");
        user4.addToPrereqs("ENGR201");
        user4.addToPrereqs("ECON201");
        user4.addToPrereqs("SOEN228");
        user4.addToPrereqs("COMP249");
        user4.addToPrereqs("SOEN287");
        user4.addToPrereqs("ENGR233");
        user4.addToPrereqs("BIOL206");
        user4.addToPrereqs("COMP352");
        user4.addToPrereqs("COMP348");
        user4.addToPrereqs("ENGR202");
        user4.addToPrereqs("ENCS272");
        user4.addToPrereqs("ENCS282");
        user4.addToPrereqs("ELEC275");



        userRepo.save(user4);

        List<User> userData = userRepo.findAll();
        for (User User : userData) {
            LOG.info("Course found :" + User.toString());

        }


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



    }

}
