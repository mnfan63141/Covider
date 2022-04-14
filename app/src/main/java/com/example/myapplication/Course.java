package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String location;
    public Course(String id, String location) {
        this.id = id;
        this.location = location;
    }
    public int calculateRisk(Context context)
    {
        DatabaseHelper db = new DatabaseHelper(context);
        SQLiteDatabase dbInstant = db.getReadableDatabase();
        Cursor cursor = dbInstant.rawQuery("Select * from Buildings", null);
        String risk = "0";
        Log.v("db", cursor.getCount() +"");
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                Log.v("db", cursor.getString(0) +"");
                if(cursor.getString(0).equals(location)){
                    risk = cursor.getString(1);
                }
            }
        }
        return Integer.parseInt(risk);
    }
    public int calculateRisk()
    {
        DatabaseHelper db = login.db;
        SQLiteDatabase dbInstant = db.getReadableDatabase();
        Cursor cursor = dbInstant.rawQuery("Select * from Buildings", null);
        String risk = "0";
        Log.v("db", cursor.getCount() +"");
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                Log.v("db", cursor.getString(0) +"");
                if(cursor.getString(0).equals(location)){
                    risk = cursor.getString(1);
                }
            }
        }
        return Integer.parseInt(risk);
    }
    public String toString()
    {
        return id + "-" + location;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}