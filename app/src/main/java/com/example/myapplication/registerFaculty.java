
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class registerFaculty extends AppCompatActivity  {

    public static List<User> data;
    DatabaseHelper db;
    private EditText fullName, email, password;
    private EditText courses;
    public Button button;
    Button addUser, addFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("registerFaculty", "onCreate: ");
        setContentView(R.layout.activity_register_faculty);

        // Setting up the database
        fullName = findViewById(R.id.fullName1);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);
        courses = findViewById(R.id.courses);


        addUser = findViewById(R.id.registerUser1);


        // creating an object of databaseHelper
        db = new DatabaseHelper(registerFaculty.this);


        // on click listener for add user
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullName.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                String coursesString = courses.getText().toString();
                // checking if all the fields have been filled
                if (name.isEmpty() || emailString.isEmpty() || passwordString.isEmpty() || coursesString.isEmpty()) {
                    Toast.makeText(registerFaculty.this, "Please fill all the data", Toast.LENGTH_SHORT).show();
                    return;
                }
                // check formatting

                String[] lines = coursesString.split("\n");
                String totalLine = "";
                for (String line : lines) {
                    if(!line.matches("[a-zA-Z0-9]*-[a-zA-Z0-9]*")) {
                        Toast.makeText(registerFaculty.this, "Please follow valid formatting", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    totalLine += line + " ";
                }
                Professor user = null;
                try {
                    user = Professor.parseCourseIdList(totalLine, name, passwordString, emailString);
                } catch (Exception e) {
                    Toast.makeText(registerFaculty.this, "Please enter valid courses", Toast.LENGTH_SHORT).show();
                }
                if (user == null) {
                    Toast.makeText(registerFaculty.this, "Please enter valid courses", Toast.LENGTH_SHORT).show();
                }
                try {
                    db.addProfessor(user);
                }catch (Exception e){
                    Toast.makeText(registerFaculty.this, "Email already exists", Toast.LENGTH_SHORT).show();
                }
                db.addUser(user);
                for(Course course : user.getCourses()){
                    db.addCourse(course);
                }
                Toast.makeText(registerFaculty.this, "User Added", Toast.LENGTH_SHORT).show();
                fullName.setText("");
                email.setText("");
                password.setText("");
                courses.setText("");
                startActivity(new Intent(registerFaculty.this, login.class));
            }
        });
    }
}

        //  database ends
