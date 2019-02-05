package com.coursebuilder;

import lombok.Data;
import java.util.List;
import javax.persistence.Entity;

@Data
public class Course {

    private String courseId;
    private List<Section> sections; //To be decided how to store




}
