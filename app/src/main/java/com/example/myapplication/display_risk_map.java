package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class display_risk_map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_risk_map);
        Intent secondIntent = getIntent();
        //we should insert db logic here by using buildingname from getStringExtra to find risk
        String message = "Risk level is " + secondIntent.getStringExtra("BUILDING_SELECTED");
        TextView myText = (TextView) findViewById(R.id.display);
        myText.setText(message);
    }
}