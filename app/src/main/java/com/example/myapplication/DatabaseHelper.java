
package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager8.db";

    // User table name
    private static final String TABLE_USER = "Users";
    // Building table name
    private static final String TABLE_BUILDING = "Buildings";

    // User Table Columns names
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    //Building table columns
    private static final String COLUMN_BUILDING_NAME = "building_name";
    private static final String COLUMN_BUILDING_RISKLEVEL = "risk_level";






    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table sql query
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_NAME + " TEXT PRIMARY KEY ,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);


        // create the buildings table
        String CREATE_BUILDING_TABLE = "CREATE TABLE " + TABLE_BUILDING + "("
                + COLUMN_BUILDING_NAME + " TEXT PRIMARY KEY ,"
                + COLUMN_BUILDING_RISKLEVEL + " INTEGER "
                + ")";
        db.execSQL(CREATE_BUILDING_TABLE);
        // adding buildings and risk levels
        ContentValues values = new ContentValues();
        values.put(COLUMN_BUILDING_NAME, "Campus Center");
        values.put(COLUMN_BUILDING_RISKLEVEL, "1");
        db.insert(TABLE_BUILDING, null, values);

        values.put(COLUMN_BUILDING_NAME, "Taper Hall");
        values.put(COLUMN_BUILDING_RISKLEVEL, "3");
        db.insert(TABLE_BUILDING, null, values);

        values.put(COLUMN_BUILDING_NAME, "Salvatori");
        values.put(COLUMN_BUILDING_RISKLEVEL, "2");
        db.insert(TABLE_BUILDING, null, values);

        values.put(COLUMN_BUILDING_NAME, "Fertitta");
        values.put(COLUMN_BUILDING_RISKLEVEL, "1");
        db.insert(TABLE_BUILDING, null, values);

        values.put(COLUMN_BUILDING_NAME, "Engemann");
        values.put(COLUMN_BUILDING_RISKLEVEL, "4");
        db.insert(TABLE_BUILDING, null, values);

        values.put(COLUMN_BUILDING_NAME, "Kaufman");
        values.put(COLUMN_BUILDING_RISKLEVEL, "3");
        db.insert(TABLE_BUILDING, null, values);

        values.put(COLUMN_BUILDING_NAME, "Kaprielian");
        values.put(COLUMN_BUILDING_RISKLEVEL, "2");
        db.insert(TABLE_BUILDING, null, values);

        values.put(COLUMN_BUILDING_NAME, "Leventhal");
        values.put(COLUMN_BUILDING_RISKLEVEL, "5");
        db.insert(TABLE_BUILDING, null, values);

        values.put(COLUMN_BUILDING_NAME, "Annenberg");
        values.put(COLUMN_BUILDING_RISKLEVEL, "1");
        db.insert(TABLE_BUILDING, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
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



}