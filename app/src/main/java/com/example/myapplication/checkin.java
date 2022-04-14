package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class checkin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        setTitle("Check-in");
        Button button = (Button) findViewById(R.id.homebtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(checkin.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}