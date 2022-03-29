package com.example.professor;

public class Professor extends User {
    List<Course> courses;
    public Professor(String name, String email, String password) {
        super(name, email, password);
        courses = new ArrayList<Course>();
    }
    public void addCourse(Course course) {
        courses.add(course);
    }
    public List<Integer> calculateCourseRisk()
    {
        List<Integer> risk = new ArrayList<Integer>();
        for(Course course : courses)
        {
            risk.add(course.calculateRisk());
        }
    }
    
}