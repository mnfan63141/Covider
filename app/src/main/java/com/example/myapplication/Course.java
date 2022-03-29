package com.example.course;

public class Course {
    private String id;
    private List<User> students;

    public Course(String id) {
        this.id = id;
        students = new ArrayList<User>();
    }

    public void addStudent(User student) {
        students.add(student);
    }

    public void removeStudent(User student) {
        students.remove(student);
    }
    public int calculateRisk()
    {
        int risk = 0;
        for (User student : students) {
            risk += student.calculateRisk();
        }
        return risk;
    }

    public List<User> getStudents() {
        return students;
    }

    public String getId() {
        return id;
    }
}