
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class register extends AppCompatActivity implements View.OnClickListener {

    public static List<User> data;
    DatabaseHelper db;
    private EditText fullName, email, password;
    public Button button;
    Button addUser, addFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Setting up the database
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email2);
        password = findViewById(R.id.password);
        addUser = findViewById(R.id.addUser1);

        // creating an object of databaseHelper
        db = new DatabaseHelper(register.this);
        addFaculty = findViewById(R.id.registerFaculty);

        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // log here
                Log.d("register", "onClick: Attempting to add new user");
                startActivity(new Intent(register.this, registerFaculty.class));
            }
        });

        // on click listener for add user
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullName.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                // checking if all the fields have been filled
                if (name.isEmpty() || emailString.isEmpty() || passwordString.isEmpty()) {
                    Toast.makeText(register.this, "Please fill all the data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.v("register", "onClick: attempting to add user");
                User user = new User(name, passwordString, emailString);
                db.addUser(user);
                Toast.makeText(register.this, "User Added", Toast.LENGTH_SHORT).show();
                fullName.setText("");
                email.setText("");
                password.setText("");
                startActivity(new Intent(register.this, login.class));
            }
        });
        TextView covider = (TextView) findViewById(R.id.banner);
        covider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,login.class));
        }
    }
}

        //  database ends
