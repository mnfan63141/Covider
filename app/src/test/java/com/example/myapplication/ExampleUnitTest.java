package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void profStringGetSet()
    {
        Professor p = new Professor("name",  "password", "email");
        assertEquals("name", p.getFullName());
        assertEquals("email", p.getEmail());
        assertEquals("password", p.getPassword());

        p.setFullName("newName");
        p.setEmail("newEmail");
        p.setPassword("newPassword");
        assertEquals("newName", p.getFullName());
        assertEquals("newEmail", p.getEmail());
        assertEquals("newPassword", p.getPassword());

    }
    @Test
    public void profCoursesGetSet()
    {
        Professor p = new Professor("name", "email", "password");
        Course c1 = new Course("0", "a");
        Course c2 = new Course("1", "b");
        Course c3 = new Course("2", "c");
        Course c4 = new Course("3", "c");
        p.addCourse(c1);
        p.addCourse(c2);
        p.addCourse(c3);
        p.addCourse(c4);
        assertEquals(4, p.getCourses().size());
        assertEquals("0-a", p.getCourses().get(0).toString());
        assertEquals("1-b", p.getCourses().get(1).toString());
        assertEquals("2-c", p.getCourses().get(2).toString());
        assertEquals("3-c", p.getCourses().get(3).toString());
    }

    @Test
    public void profParseTest()
    {
        String classList = "0-a 1-b 2-c 3-c";
        Professor p2 = Professor.parseCourseIdList(classList, "name", "password", "email");
        assertEquals("name", p2.getFullName());
        assertEquals("email", p2.getEmail());
        assertEquals("password", p2.getPassword());
        assertEquals("0-a", p2.getCourses().get(0).toString());
        assertEquals("1-b", p2.getCourses().get(1).toString());
        assertEquals("2-c", p2.getCourses().get(2).toString());
        assertEquals("3-c", p2.getCourses().get(3).toString());

    }



    @Test
    public void courseGetSet() {
        Course course = new Course("0", "a");
        assertEquals("0", course.getId());
        assertEquals("a", course.getLocation());

        course.setId("1");
        course.setLocation("b");
        assertEquals("1", course.getId());
        assertEquals("b", course.getLocation());

        assertEquals("1-b", course.toString());

        Course course2 = new Course("1", "b");

    }
    @Test
    public void profParseTest2()
    {
        String classList = "0-a 1-b 2-c 3-c";
        Professor p2 = Professor.parseCourseIdList(classList, "name", "password", "email");
        assertEquals("name", p2.getFullName());
        assertEquals("email", p2.getEmail());
        assertEquals("password", p2.getPassword());
        assertEquals("0-a", p2.getCourses().get(0).toString());
        assertEquals("1-b", p2.getCourses().get(1).toString());
        assertEquals("2-c", p2.getCourses().get(2).toString());
        assertEquals("3-c", p2.getCourses().get(3).toString());

    }



}