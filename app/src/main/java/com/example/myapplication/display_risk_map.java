//package com.example.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//
//public class display_risk_map extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_risk_map);
//        Intent secondIntent = getIntent();
//        //we should insert db logic here by using buildingname from getStringExtra to find risk
//        String message = "Risk level is " + secondIntent.getStringExtra("BUILDING_SELECTED");
//        TextView myText = (TextView) findViewById(R.id.display);
//        myText.setText(message);
//    }
//}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class display_risk_map extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_risk_map);
        Intent secondIntent = getIntent();

        String buildingName = secondIntent.getStringExtra("BUILDING_SELECTED");
        db = new DatabaseHelper(display_risk_map.this);
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