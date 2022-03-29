package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityLoginBinding;

import java.util.List;

public class login extends AppCompatActivity implements View.OnClickListener{
    EditText email, password;
    Button enter;
   static DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean flag1, flag2 = false;
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        enter = findViewById(R.id.signIn);
        db = new DatabaseHelper(login.this);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = false;
                String pass = password.getText().toString();
                String em = email.getText().toString();
                if(em.isEmpty() || pass.isEmpty()){
                    Toast.makeText(login.this,  "Please enter email and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase database = db.getReadableDatabase();
                    String db_name = db.getDatabaseName();
                    Cursor cursor = database.rawQuery("Select * from Users", null);
                    if(cursor.getCount() > 0) {
                        while(cursor.moveToNext()) {
                            if (cursor.getString(1).equals(em) &&
                                    cursor.getString(2).equals(pass)) {
                                success = true;
                                break;
                            }
                        }
                        if(success){
                            Toast.makeText(login.this,  "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(login.this, MainActivity.class);
                             startActivity(myIntent);
                        }
                        else {
                            Toast.makeText(login.this,  "Incorrect login credentials, Try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(login.this,  "Incorrect login credentials", Toast.LENGTH_SHORT).show();
                    }
                }






            }
        });
    // if the user is registering for the first time
        TextView register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.register:
                startActivity(new Intent(this,register.class));
        }
    }
}