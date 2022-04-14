package com.example.myapplication;

import com.example.myapplication.*;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {
    List<Course> courses;
    public Professor(String name, String email, String password) {
        super(name, email, password);
        courses = new ArrayList<Course>();
    }

    public List<Integer> calculateCourseRisk()
    {
        List<Integer> risk = new ArrayList<Integer>();
        for(Course course : courses)
        {
            risk.add(course.calculateRisk());
        }
        return risk;
    }
    public String getCourseIdList()
    {
        String courseIdList = "";
        for(Course course : courses)
        {
            courseIdList += course.getId() + "-" + course.getLocation() + " ";
        }
        return courseIdList;
    }
    public void addCourse(Course course)
    {
        courses.add(course);
    }
    public List<Course> getCourses() {
        return courses;
    }
    public static Professor parseCourseIdList(String courseIdList, String name, String email, String password)
    {
        try {
            Professor professor = new Professor(name, email, password);
            String[] courseIds = courseIdList.split(" ");
            for (String courseId : courseIds) {
                String[] courseIdParts = courseId.split("-");
                professor.courses.add(new Course(courseIdParts[0], courseIdParts[1]));
            }
            return professor;
        }catch (Exception e)
        {
            return null;
        }
    }


    
}