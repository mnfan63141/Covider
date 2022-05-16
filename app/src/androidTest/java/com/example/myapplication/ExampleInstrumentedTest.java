package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<login> activityRule = new ActivityTestRule<>(login.class, true, true);
    @Test
    public void facultySigninTestExistance() {

        onView(withId(R.id.email2)).perform(typeText("7"));
        onView(withId(R.id.password)).perform(typeText("7"));
        onView(withId(R.id.signIn)).perform(click());
        onView(withId(R.id.faculty)).perform(click());
        onView(withId(faculty.butId)).perform(click());
        onView(withId(R.id.online)).perform(click());
        onView(withId(faculty.butId)).perform(click());
        onView(withId(R.id.currStatus)).check(matches(withText("Current Status: Online")));
    }
    @Test
    public void facultyRegisterTestExistance() {
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.registerFaculty)).perform(click());
        onView(withId(R.id.courses)).perform(typeText("0-1"));
        onView(withId(R.id.fullName1)).perform(typeText("g"));
        onView(withId(R.id.email1)).perform(typeText("g"));

        onView(withId(R.id.password1)).perform(typeText("g"));
        onView(withId(R.id.email1)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.registerUser1)).perform(click());
        // sleep for a
        onView(withId(R.id.email2)).check(matches(isDisplayed()));

    }
    @Test
    public void facultyCreationTest() {

        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.registerFaculty)).perform(click());
        onView(withId(R.id.courses)).perform(typeText("4-1"));
        onView(withId(R.id.fullName1)).perform(typeText("g1"));
        onView(withId(R.id.email1)).perform(typeText("g1"));

        onView(withId(R.id.password1)).perform(typeText("g1"));
        onView(withId(R.id.email1)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.registerUser1)).perform(click());

        DatabaseHelper db = new DatabaseHelper(activityRule.getActivity());
        SQLiteDatabase dbInstant = db.getReadableDatabase();
        Cursor cursor = dbInstant.rawQuery("Select * from Courses", null);
        String pw = "0";
        if(cursor.getCount()>0){
            pw = "1";
            while(cursor.moveToNext()){
                Log.d("cursor",cursor.getString(1));
                if(cursor.getString(0).equals("4")){
                    pw = cursor.getString(0);
                }
            }
        }
        Assert.assertEquals("4", pw);
    }
    @Test
    public void facultyAddClass() {
        onView(withId(R.id.email2)).perform(typeText("g44"));
        onView(withId(R.id.password)).perform(typeText("g44"));
        onView(withId(R.id.signIn)).perform(click());
        onView(withId(R.id.faculty)).perform(click());
        onView(withId(R.id.addClass)).perform(click());

        onView(withId(R.id.courseId)).perform(typeText("2"));
        onView(withId(R.id.courseLoc)).perform(typeText("Annenburg"));
        onView(withId(R.id.addClass)).perform(click());
        onView(withId(R.id.homebtn)).check(matches(isDisplayed()));
        DatabaseHelper db = new DatabaseHelper(activityRule.getActivity());
        SQLiteDatabase dbInstant = db.getReadableDatabase();
        Cursor cursor = dbInstant.rawQuery("Select * from Professor", null);
        String pw = "0";
        if(cursor.getCount()>0){
            pw = "1";
            while(cursor.moveToNext()){
                Log.d("cursor",cursor.getString(0));
                if(cursor.getString(0).contains("2-Annenburg")){

                    pw = "4";
                }
            }
        }
        Assert.assertEquals("4", pw);

    }

    @Test
    public void facultyFalseFlag()
    {
        // g222 is a regular student
        onView(withId(R.id.email2)).perform(typeText("g222"));
        onView(withId(R.id.password)).perform(typeText("g222"));
        onView(withId(R.id.signIn)).perform(click());
        onView(withId(R.id.faculty)).perform(click());
        onView(withId(R.id.faculty)).check(matches(isDisplayed()));
    }






}