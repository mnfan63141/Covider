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
    TextView campusCenterView;
    TextView taperView;
    TextView salvatoriView;
    TextView fertittaView;
    TextView engemannView;
    TextView kaufmanView;
    TextView kaprielianView;
    TextView leventhalView;
    TextView annenbergView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Campus Map");
        setContentView(R.layout.activity_campus_map);
        imageView = findViewById(R.id.imageView);
        imageView.setRotation(90);
        myIntent = new Intent(this, display_risk_map.class);

        //buildings change id, view name and click lister
        //add text view and reposition with new id
        taperView = (TextView) findViewById(R.id.taper);
        taperView.setRotation(90);

        salvatoriView = (TextView) findViewById(R.id.salvatori);
        salvatoriView.setRotation(90);

        campusCenterView = (TextView) findViewById(R.id.campusCenter);
        campusCenterView.setRotation(90);

        fertittaView = (TextView) findViewById(R.id.fertitta);
        fertittaView.setRotation(90);

        engemannView = (TextView) findViewById(R.id.engemann);
        engemannView.setRotation(90);

        kaufmanView = (TextView) findViewById(R.id.kaufman);
        kaufmanView.setRotation(90);

        kaprielianView = (TextView) findViewById(R.id.kaprielian);
        kaprielianView.setRotation(90);

        leventhalView = (TextView) findViewById(R.id.leventhal);
        leventhalView.setRotation(90);

        annenbergView = (TextView) findViewById(R.id.annenberg);
        annenbergView.setRotation(90);

        taperView.setOnClickListener(this);
        salvatoriView.setOnClickListener(this);
        campusCenterView.setOnClickListener(this);
        fertittaView.setOnClickListener(this);
        engemannView.setOnClickListener(this);
        kaufmanView.setOnClickListener(this);

        kaprielianView.setOnClickListener(this);
        leventhalView.setOnClickListener(this);
        annenbergView.setOnClickListener(this);
    }//393x851 XxY

    @Override
    public void onClick(View v){
        String buildingName = "";
        Bundle bundle = new Bundle();
        bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

        bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

        myIntent.putExtras(bundle);
        if(v.getId() == R.id.taper){

                myIntent.putExtra("BUILDING_SELECTED", "Taper Hall");
                startActivity(myIntent);
        }
        else if(v.getId() == R.id.salvatori){
            buildingName = (String) salvatoriView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.campusCenter){
            buildingName = (String) campusCenterView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.fertitta){
            buildingName = (String) fertittaView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.engemann){
            buildingName = (String) engemannView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.kaufman){
            buildingName = (String) kaufmanView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.kaprielian){
            buildingName = (String) kaprielianView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.leventhal){
            buildingName = (String) leventhalView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.annenberg){
            buildingName = (String) annenbergView.getText();
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
    }

}