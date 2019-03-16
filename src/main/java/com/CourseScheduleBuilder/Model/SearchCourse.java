package com.CourseScheduleBuilder.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
public class searchCourse {
    @JsonProperty
    private String searchedCourse;
    @JsonProperty
    private Integer searchedCourseNum;

    public String getSearchedCourse() {
        return searchedCourse;
    }

    public void setSearchedCourse(String searchedCourse) {
        this.searchedCourse = searchedCourse;
    }

    public Integer getSearchedCourseNum() {
        return searchedCourseNum;
    }

    public void setSearchedCourseNum(Integer searchedCourseNum) {
        this.searchedCourseNum = searchedCourseNum;
    }

    public searchCourse(String searchedCourse){
        this.searchedCourse = searchedCourse;
    }

    public searchCourse(String searchedCourse, Integer searchedCourseNum){
        this.searchedCourse = searchedCourse;
        this.searchedCourseNum = searchedCourseNum;
    }

    //two constructors support search via string or string and integer so course number and subject can be used or just course name
}
