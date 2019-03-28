package com.CourseScheduleBuilder.controller;

import com.CourseScheduleBuilder.Services.ScheduleBuilderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ScheduleBuilderController.class)
public class ScheduleBuilderControllerTest {

    @MockBean
    ScheduleBuilderService scheduleBuilderService;

    @Autowired
    private MockMvc mvc;

    String addCourseURL = "http://localhost:8080/addCourseToWishList";
    String generateURL = "http://localhost:8080/generate";
    String nextURL = "http://localhost:8080/next";

    String courseA = "COMP232";
    String courseB = "SOEN287";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void requestSchedule() {
    }

    @Test
    public void addCourseToWishList() {
//        Mockito.doNothing().when(scheduleBuilderService).
    }

    @Test
    public void generateSchedule() {
    }

    @Test
    public void nextSchedule() {
    }

    @Test
    public void previousSchedule() {
    }
}