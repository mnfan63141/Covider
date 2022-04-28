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
import android.widget.Toast;

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
        int numVisits = 0;
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                if(cursor.getString(0).equals(buildingName)){
                    risk = cursor.getString(1);
                    numVisits = cursor.getInt(2);
                }
            }
        }
        String note = "Risk level is out of 5 where 5 is the highest";
        TextView noteText = (TextView) findViewById(R.id.note);
        noteText.setText(note);
        if(numVisits > 10){
            risk = String.valueOf(Integer.parseInt(risk) + 1);
            String message = "Risk level at " + buildingName + " is " + risk +
                    " and is one risk degree higher due to reduced building safety and high foot traffic.";
            TextView myText = (TextView) findViewById(R.id.display);
            myText.setText(message);
        }
        else{
            String message = "Risk level at " + buildingName + " is " + risk;
            TextView myText = (TextView) findViewById(R.id.display);
            myText.setText(message);
        }

        TextView frequentText = (TextView) findViewById(R.id.frequent);
        if((risk.equals("2") || risk.equals("1") || risk.equals("0")) && numVisits <= 10){
            frequentText.setText("This is a safer location for you!");
        }
        else{
            frequentText.setText("Consider zooming in to this location!");
        }
        if(numVisits >= 4){
            Toast.makeText(display_risk_map.this,  "This is a frequent location for you!", Toast.LENGTH_LONG).show();
        }
    }
}