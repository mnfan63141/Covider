package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    ActivityTestRule<login> activityRule = new ActivityTestRule<>(login.class);
    @Test
    public void facultySigninTestExistance() {

        onView(withId(R.id.email2)).perform(typeText("g"));
        onView(withId(R.id.password)).perform(typeText("g"));
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }
    @Test
    public void facultyRegisterTestExistance() {
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.registerFaculty)).perform(click());
        onView(withId(R.id.fullName1)).perform(typeText("g"));
        onView(withId(R.id.email1)).perform(typeText("g"));
        onView(withId(R.id.password1)).perform(typeText("g"));
        onView(withId(R.id.courses)).perform(typeText("0-1"));
        // sleep for a
        onView(withId(R.id.email1)).check(matches(isDisplayed()));

    }
    @Test
    public void facultyCreationTest() {
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.registerFaculty)).perform(click());
        onView(withId(R.id.fullName1)).perform(typeText("g1"));
        onView(withId(R.id.email1)).perform(typeText("g1"));
        onView(withId(R.id.password1)).perform(typeText("g1"));
        onView(withId(R.id.courses)).perform(typeText("0-1"));
        onView(withId(R.id.email2)).perform(typeText("g"));
        onView(withId(R.id.password)).perform(typeText("g"));
        onView(withId(R.id.map)).check(matches(isDisplayed()));

    }
    @Test
    public void facultyFullUseTest() {
        onView(withId(R.id.email2)).perform(typeText("g"));
        onView(withId(R.id.password)).perform(typeText("g"));
        onView(withId(R.id.faculty)).perform(click());
        onView(withId(R.id.homebtn)).check(matches(isDisplayed()));
    }

    @Test
    public void facultyFalseFlag()
    {
        onView(withId(R.id.email2)).perform(typeText("g222"));
        onView(withId(R.id.password)).perform(typeText("g222"));
        onView(withId(R.id.faculty)).perform(click());
        onView(withId(R.id.email2)).check(matches(isDisplayed()));
    }






}