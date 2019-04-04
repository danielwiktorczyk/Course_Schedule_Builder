package com.CourseScheduleBuilder.Services;

import com.CourseScheduleBuilder.Repositories.CourseRepo;
import com.CourseScheduleBuilder.Repositories.UserRepo;
import com.CourseScheduleBuilder.Repositories.loggedInUserRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Java6Assertions.assertThat;
@Ignore
@RunWith(SpringRunner.class)
public class ScheduleBuilderServiceImplTest {

    RestTemplate restTemplate = new RestTemplate();

    @MockBean
    private static CourseRepo courseRepo;
    @MockBean
    private static UserRepo userRepo;
    @MockBean
    private static loggedInUserRepo login;

    @TestConfiguration
        static class ScheduleBuilderServiceImplTestConfiguration {
        @Bean
        public ScheduleBuilderServiceImpl scheduleBuilderService() {
            return new ScheduleBuilderServiceImpl( courseRepo,  userRepo);
        }
    }


    ScheduleBuilderServiceImpl scheduleBuilderService;



    String courseA = "COMP232";
    String courseB = "SOEN287";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void validatePrerequisites() {
        assertThat(true);
    }

    @Test
    public void scheduleGenerator() {

    }

    @Test
    public void generateSchedule() {
        scheduleBuilderService.generateSchedules(courseA,"Fall");
    }

    @Test
    public void nextSchedule() {
    }

    @Test
    public void previousSchedule() {
    }
}