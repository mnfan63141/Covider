
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class register extends AppCompatActivity  {

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
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        addUser = findViewById(R.id.registerUser);

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
                User user = new User(name, passwordString, emailString);
                db.addUser(user);
                Toast.makeText(register.this, "User Added", Toast.LENGTH_SHORT).show();
                fullName.setText("");
                email.setText("");
                password.setText("");
                startActivity(new Intent(register.this, login.class));
            }
        });
    }
}

        //  database ends
