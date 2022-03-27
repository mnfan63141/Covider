package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class CampusMap extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Intent myIntent;
    TextView taperView;
    TextView salvatoriView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Campus Map");
        setContentView(R.layout.activity_campus_map);
        imageView = findViewById(R.id.imageView);
        imageView.setRotation(90);
        myIntent = new Intent(this, display_risk_map.class);

        //buildings
        taperView = (TextView) findViewById(R.id.taper);
        taperView.setRotation(90);
        salvatoriView = (TextView) findViewById(R.id.salvatori);
        salvatoriView.setRotation(90);
        taperView.setOnClickListener(this);
        salvatoriView.setOnClickListener(this);

    }//393x851 XxY

    @Override
    public void onClick(View v){
        String buildingName = "";
        if(v.getId() == R.id.taper){
            buildingName = (String) taperView.getText();
                myIntent.putExtra("BUILDING_SELECTED", buildingName);
                startActivity(myIntent);
        }
        else if(v.getId() == R.id.salvatori){
            buildingName = (String) salvatoriView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
    }
//    @Override
//    public boolean OnTouchListener(MotionEvent e){
//        double x = 0;
//        double y = 0;
//        if(e.getAction() == MotionEvent.ACTION_DOWN){
//            x = e.getX();
//            y = e.getY();
//        }
//        if(0 < x){
//            myIntent.putExtra("BUILDING_SELECTED", "THH");
//            startActivity(myIntent);
//        }
//        return super.onTouchEvent(e);
//    }

}