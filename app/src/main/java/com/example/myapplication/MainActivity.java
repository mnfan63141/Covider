package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.media.MediaPlayer;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static List<User> data;
    DatabaseHelper db;
    private EditText fullName, email, password;
    public Button button;
    Button addUser;
    boolean isProf = false;
    String courseString = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            try {
                isProf = getIntent().getExtras().getBoolean("isProf");
            } catch (Exception e) {
                Log.d("MainActivity", "onCreate: " + e.getMessage());
                isProf = false;
            }
        }

        Log.v("isProf2", isProf + "");
        setTitle("Covider");
        button = (Button) findViewById(R.id.map);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,CampusMap.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button listBtn = (Button) findViewById(R.id.list);
        listBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,campus_list.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button checkinBtn = (Button) findViewById(R.id.checkin);
        checkinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,checkin.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button logoutBtn = (Button) findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
        Button facultyBtn = (Button) findViewById(R.id.faculty);
        facultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isProf) {
                    courseString = getIntent().getExtras().getString("profCourseList");
                    Intent intent = new Intent(MainActivity.this, faculty.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                    bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "You are not a professor", Toast.LENGTH_SHORT).show();
                }
            }


        });
        Button schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, schedule.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("isProf", isProf);
        savedInstanceState.putString("profCourseList", courseString);

    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isProf = savedInstanceState.getBoolean("isProf");
        courseString = savedInstanceState.getString("profCourseList");
    }
}