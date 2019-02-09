package com.courseBuilder.courseBuilderAPI.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courseBuilder.courseBuilderAPI.domain.Student;
import com.courseBuilder.courseBuilderAPI.repositories.StudentRepositories;
import com.courseBuilder.courseBuilderAPI.service.StudentService;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping
    public ResponseEntity<?> getAll()
    {
        List<Student> result = studentService.findAll();
            return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{studentId}/{fullName}")
    public ResponseEntity<?> getByNameOrId(@PathVariable("studentId") long studentId, @PathVariable("fullName") String fullName){
        List<Student> result = new ArrayList<>();
            if("All".equals(fullName))
                result = studentService.findByFullName(fullName);
            else
                result = studentService.findAll();


            return new ResponseEntity(result, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addOrUpdateStudent(@RequestBody Student student)
    {
        studentService.saveOrUpdateStudent(student);
            return new ResponseEntity("Student database updated succesffully", HttpStatus.OK);

    }

    @DeleteMapping
    public void deleteStudent(@RequestParam("id") String id)
    {

    }

}
