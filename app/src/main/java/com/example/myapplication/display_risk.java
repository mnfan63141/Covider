package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class display_risk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_risk);
        Intent secondIntent = getIntent();
        String message = "Risk level is " + secondIntent.getStringExtra("BUILDING_SELECTED");
        TextView myText = (TextView) findViewById(R.id.display);
        myText.setText(message);
    }
}