package com.example.springbootwithreactjs.controller;

import com.example.springbootwithreactjs.Course;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
public class PongController {

  @RequestMapping(value = "/pong", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:3000") //TODO change me!
  public Course pong() {
    Course x = new Course("COMP249", "COMP248");
    return x;
  }
}
