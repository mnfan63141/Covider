package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.media.MediaPlayer;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static List<User> data;
    DatabaseHelper db;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View normalC = this.findViewById(R.id.C);
//        //normalC.setEnabled(true);
//        final MediaPlayer mpC = MediaPlayer.create(this, R.raw.normalc);
//        normalC.setOnClickListener(new OnClickListener(){
//
//            public void onClick(View v) {
//                mpC.start();
//            }
//        });
        setTitle("Covider");
        button = (Button) findViewById(R.id.map);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,CampusMap.class);
                startActivity(intent);
            }
        });

        Button listBtn = (Button) findViewById(R.id.list);
        listBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,campus_list.class);
                startActivity(intent);
            }
        });

        Button checkinBtn = (Button) findViewById(R.id.checkin);
        checkinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,checkin.class);
                startActivity(intent);
            }
        });

        Button facultyBtn = (Button) findViewById(R.id.faculty);
        facultyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,faculty.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(this);
        data = new ArrayList<>();
        User user = new User("test", "test", "test");
//        db.addUser(user);
//        data = db.getAllUser();
//        System.out.println(data);

    }
}