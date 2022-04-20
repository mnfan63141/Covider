package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class login extends AppCompatActivity implements View.OnClickListener{
    EditText email, password;
    Button enter;
    public static String loginUser;
    public static boolean isProf;
    public static String profCourseList;

   static DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean flag1, flag2 = false;
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email2);
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
                    String query = "SELECT * FROM Users" + " WHERE user_email = '" + em + "' AND user_password = '" + pass + "'";
                    Cursor cursor = database.rawQuery(query, null);
                    Cursor cursorProf = null;
                    try {
                        query = "SELECT * FROM Professor" + " WHERE user_email = '" + em + "' AND user_password = '" + pass + "'";
                        cursorProf =database.rawQuery(query, null);
                        Log.v("cursorProf", cursorProf.toString());
                    } catch (Exception e) {
                        Log.d("Error", e.toString());
                    }
                    login.isProf = false;
                    if(cursorProf != null && cursorProf.getCount() > 0){
                        cursor = cursorProf;
                        login.isProf = true;
                    }
                    Log.v("Cursor", cursor.getCount() + "");
                    Log.v("isProf", isProf + "");

                    if(cursor.getCount() > 0) {
                        while(cursor.moveToNext()) {
                            Log.e("Email", cursor.getString(1));
                            Log.e("Password", cursor.getString(2));
                            if (cursor.getString(1).equals(em) &&
                                    cursor.getString(2).equals(pass)) {
                                success = true;
                                loginUser = cursor.getString(1);
                                break;
                            }
                        }
                        if(success) {
                            Toast.makeText(login.this,  "Login Successful", Toast.LENGTH_SHORT).show();

                            Intent myIntent = new Intent(login.this, MainActivity.class);
                            Bundle b = new Bundle();

                            b.putBoolean("isProf", isProf);
                            Log.v("isProf1", isProf + "");
                            if(isProf){
                              //  Log.v("III:", cursor.());
                                b.putString("profCourseList", cursor.getString(0));
                                login.profCourseList = cursor.getString(0);
                            }
                            myIntent.putExtras(b);
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