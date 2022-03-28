package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Setting up the database
//        fullName = findViewById(R.id.fullName);
//        email = findViewById(R.id.email);
//        password = findViewById(R.id.password);
//        addUser =  findViewById(R.id.registerUser);
//
//        // creating an object of databaseHelper
//        db = new DatabaseHelper(MainActivity.this);
//
//        // on click listener for add user
//        addUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = fullName.getText().toString();
//                String emailString = email.getText().toString();
//                String passwordString = password.getText().toString();
//                // checking if all the fields have been filled
//                if(name.isEmpty() || emailString.isEmpty() || passwordString.isEmpty()){
//                    Toast.makeText(MainActivity.this, "Please fill all the data", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                User user = new User(name, passwordString, emailString);
//                db.addUser(user);
//                Toast.makeText(MainActivity.this, "User Added", Toast.LENGTH_SHORT).show();
//                fullName.setText("");
//                email.setText("");
//                password.setText("");
//            }
//        });
//        //  database ends

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
    }
}