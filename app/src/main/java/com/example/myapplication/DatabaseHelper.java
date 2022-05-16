
package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager43333.db";

    // User table name
    private static final String TABLE_USER = "Users";
    // Building table name
    private static final String TABLE_BUILDING = "Buildings";

    // Professor Table name
    private static final String TABLE_PROFESSOR = "Professor";

    // Course Table Name
    private static final String TABLE_COURSE = "Courses";

    // User Table Columns names
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    //Building table columns
    private static final String COLUMN_BUILDING_NAME = "building_name";
    private static final String COLUMN_BUILDING_RISKLEVEL = "risk_level";
    private static final String COLUMN_BUILDING_VISIT = "building_visit";

    //Professor table columns
    private static final String COLUMN_COURSEID_LIST = "course_id_list";

    // Course table columns
    private static final String COLUMN_COURSE_ID = "course_id";
    private static final String COLUMN_COURSE_LOCATION = "course_location";
    private static final String COLUMN_COURSE_EMAILS = "course_emails";
    private static final String COLUMN_COURSE_STATUS = "course_status";

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create professor table

        String CREATE_PROFESSOR_TABLE = "CREATE TABLE " + TABLE_PROFESSOR + "("
                + COLUMN_COURSEID_LIST + " TEXT,"
                + COLUMN_USER_NAME + " TEXT PRIMARY KEY ,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")";
        try {
            db.execSQL(CREATE_PROFESSOR_TABLE);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        Log.v("DatabaseHelper", CREATE_PROFESSOR_TABLE);


        // create course table
        String CREATE_COURSE_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + COLUMN_COURSE_ID + " TEXT PRIMARY KEY ,"
                + COLUMN_COURSE_EMAILS + " TEXT,"
                + COLUMN_COURSE_LOCATION + " TEXT,"
                + COLUMN_COURSE_STATUS + " TEXT " + ")";

        try {
            db.execSQL(CREATE_COURSE_TABLE);
        } catch (Exception e) {}

        // create table sql query
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_NAME + " TEXT PRIMARY KEY ,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")";
        try {
            db.execSQL(CREATE_USER_TABLE);
        }catch(Exception e){}

        // create the buildings table
        String CREATE_BUILDING_TABLE = "CREATE TABLE " + TABLE_BUILDING + "("
                + COLUMN_BUILDING_NAME + " TEXT PRIMARY KEY ,"
                + COLUMN_BUILDING_RISKLEVEL + " INTEGER ,"
                + COLUMN_BUILDING_VISIT + " INTEGER "
                + ")";
        try {
            db.execSQL(CREATE_BUILDING_TABLE);
        }catch(Exception e){}
        // adding buildings and risk levels
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_BUILDING_NAME, "Campus Center");
            values.put(COLUMN_BUILDING_RISKLEVEL, "1");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            values.put(COLUMN_BUILDING_NAME, "Taper Hall");
            values.put(COLUMN_BUILDING_RISKLEVEL, "3");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            values.put(COLUMN_BUILDING_NAME, "Salvatori");
            values.put(COLUMN_BUILDING_RISKLEVEL, "2");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            values.put(COLUMN_BUILDING_NAME, "Fertitta");
            values.put(COLUMN_BUILDING_RISKLEVEL, "1");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            values.put(COLUMN_BUILDING_NAME, "Engemann");
            values.put(COLUMN_BUILDING_RISKLEVEL, "4");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            values.put(COLUMN_BUILDING_NAME, "Kaufman");
            values.put(COLUMN_BUILDING_RISKLEVEL, "3");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            values.put(COLUMN_BUILDING_NAME, "Kaprielian");
            values.put(COLUMN_BUILDING_RISKLEVEL, "2");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            values.put(COLUMN_BUILDING_NAME, "Leventhal");
            values.put(COLUMN_BUILDING_RISKLEVEL, "5");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            values.put(COLUMN_BUILDING_NAME, "Annenberg");
            values.put(COLUMN_BUILDING_RISKLEVEL, "1");
            values.put(COLUMN_BUILDING_VISIT, "0");
            db.insert(TABLE_BUILDING, null, values);

            ContentValues values1 = new ContentValues();
            values1.put(COLUMN_COURSE_ID, 1);
            values1.put(COLUMN_COURSE_LOCATION, "Annenberg");
            values1.put(COLUMN_COURSE_EMAILS, "1 2 3 4 5 6 7");
            values1.put(COLUMN_COURSE_STATUS, "In-Person");
            db.insert(TABLE_COURSE, null, values1);
        }catch(Exception e){}
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);

    }


    // adds new user
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getFullName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addProfessor(Professor professor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, professor.getFullName());
        values.put(COLUMN_USER_EMAIL, professor.getEmail());
        values.put(COLUMN_USER_PASSWORD, professor.getPassword());
        values.put(COLUMN_COURSEID_LIST, professor.getCourseIdList());

        // Inserting Row
        db.insert(TABLE_PROFESSOR, null, values);
        db.close();
    }
    public void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_ID, course.getId());
        values.put(COLUMN_COURSE_LOCATION, course.getLocation());
        values.put(COLUMN_COURSE_EMAILS, "");
        values.put(COLUMN_COURSE_STATUS, "In-Person");
        // Inserting Row
        db.insert(TABLE_COURSE, null, values);
        db.close();
    }
    public String[] emailList(String courseId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("DDD",courseId);
        Cursor cursor = db.query(TABLE_COURSE, new String[] { COLUMN_COURSE_ID, COLUMN_COURSE_LOCATION, COLUMN_COURSE_EMAILS}, COLUMN_COURSE_ID + "=?",
                new String[] { courseId }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     //   Log.e("AAA",cursor.getString(2));
        if(cursor.getCount() > 0)
            return cursor.getString(2).split(" ");
        return null;
    }
    public Professor getProfessor(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROFESSOR, new String[] { COLUMN_USER_NAME, COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD, COLUMN_COURSEID_LIST }, COLUMN_USER_NAME + "=?",
                new String[] { name }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Professor professor = Professor.parseCourseIdList(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return user
        return professor;
    }

    public void addUserToCourse(String courseName, String emailString) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Log.v("DDD",courseName);
        Cursor cursor = db.query(TABLE_COURSE, new String[] { COLUMN_COURSE_ID, COLUMN_COURSE_LOCATION, COLUMN_COURSE_EMAILS}, COLUMN_COURSE_ID + "=?",
                new String[] { courseName }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        values.put(COLUMN_COURSE_ID, courseName);
        values.put(COLUMN_COURSE_LOCATION, cursor.getString(1));
        values.put(COLUMN_COURSE_EMAILS, cursor.getString(2) + " " + emailString);
        db.update(TABLE_COURSE, values, COLUMN_COURSE_ID + "=?", new String[] { courseName });

    }

    public String addCourseToProfessor(Course course, String profCourseList) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        String courseName = course.getId();

        Log.v("DDD",courseName);
        Cursor cursor = db.query(TABLE_PROFESSOR, new String[] { COLUMN_USER_NAME, COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD, COLUMN_COURSEID_LIST }, COLUMN_USER_NAME + "=?",
                new String[] { profCourseList }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        //values.put(COLUMN_USER_NAME, cursor.getString(0));
        //values.put(COLUMN_USER_EMAIL, cursor.getString(1));
        //values.put(COLUMN_USER_PASSWORD, cursor.getString(2));
        String putty = cursor.getString(3) + " "+ course.getId() +"-"+course.getLocation();
        putty = putty.trim().replace(" +", " ");
        values.put(COLUMN_COURSEID_LIST, putty);
        db.update(TABLE_PROFESSOR, values, COLUMN_USER_NAME + "=?", new String[] { profCourseList });
        return putty;
    }

    public String getCourseStatus(String courseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COURSE, new String[] { COLUMN_COURSE_ID, COLUMN_COURSE_LOCATION, COLUMN_COURSE_EMAILS, COLUMN_COURSE_STATUS}, COLUMN_COURSE_ID + "=?",
                new String[] { courseName }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor.getString(3);
    }

    public void updateCourseStatus(String courseName, String online) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_STATUS, online);
        db.update(TABLE_COURSE, values, COLUMN_COURSE_ID + "=?", new String[] { courseName });
    }

    public Course[] getCourses(String loginUser) {
        // find all courses where loginUser is in COLUMN_COURSE_EMAILS
        Log.v("DDD",loginUser);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COURSE, new String[] { COLUMN_COURSE_ID, COLUMN_COURSE_LOCATION, COLUMN_COURSE_EMAILS, COLUMN_COURSE_STATUS}, COLUMN_COURSE_EMAILS + " LIKE ?",
                new String[] { "%" + loginUser + "%" }, null, null, null, null);
        Log.e("DDD", "cursor: " + cursor.getCount());
        if (cursor != null)
            cursor.moveToFirst();
        int count = cursor.getCount();
        Course[] courses = new Course[count];
        for (int i = 0; i < count; i++) {
            courses[i] = new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            cursor.moveToNext();
        }
        return courses;
    }
}