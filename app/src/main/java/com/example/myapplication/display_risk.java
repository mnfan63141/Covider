package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class display_risk extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_risk);
        Intent secondIntent = getIntent();
        String buildingName = secondIntent.getStringExtra("BUILDING_SELECTED");
        db = new DatabaseHelper(display_risk.this);
        SQLiteDatabase dbInstant = db.getReadableDatabase();
        Cursor cursor = dbInstant.rawQuery("Select * from Buildings", null);
        String risk = "0";
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                if(cursor.getString(0).equals(buildingName)){
                    risk = cursor.getString(1);
                }
            }
        }
        String message = "Risk level at " + buildingName + " is " + risk + " out of 1-5 where 5 is highest risk";
        TextView myText = (TextView) findViewById(R.id.display);
        myText.setText(message);
    }
}