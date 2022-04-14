
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class Adder extends AppCompatActivity  {

    public static List<User> data;
    DatabaseHelper db;
    private EditText fullName, email, password;
    public Button button;
    Button addUser, addFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adder);
        email = findViewById(R.id.email2);

        // creating an object of databaseHelper
        db = new DatabaseHelper(Adder.this);
        addUser = findViewById(R.id.addUser1);
        // on click listener for add user
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = email.getText().toString();
                // checking if all the fields have been filled
                if (emailString.isEmpty()) {
                    Toast.makeText(Adder.this, "Please fill all the data", Toast.LENGTH_SHORT).show();
                    return;
                }
                String courseName =getIntent().getExtras().getString("courseName");
                db.addUserToCourse(courseName, emailString);
                Toast.makeText(Adder.this, "User Added", Toast.LENGTH_SHORT).show();
                email.setText("");
                Intent intent = new Intent(Adder.this,faculty.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

//  database ends
