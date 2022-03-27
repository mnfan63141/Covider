
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class register extends AppCompatActivity implements View.OnClickListener {

    public TextView banner, registerUser;
    public EditText editTextFullName, editTextEmail, editTextPassword;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference("Buildings").child("BuildingName").
                setValue("THH");

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullName);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,login.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String fullName = editTextFullName.getText().toString();

        if(fullName.isEmpty()){
            editTextFullName.setError("Need full name");
            editTextFullName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Need full name");
            editTextEmail.requestFocus();
            return;
        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            editTextEmail.setError("Please give valid email");
//            editTextEmail.requestFocus();
//            return;
//        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User newUser = new User(fullName,password, email );
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                            setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(register.this, "Successful register", Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(register.this, "Failed register", Toast.LENGTH_LONG).show();
                            }
                        }
                    }); ///child(FirebaseAuth.getInstance().getCurrentUser().getUid()). we could add an oncompletelistener here from 19;30
                }else{
                    Toast.makeText(register.this, "Failed to register", Toast.LENGTH_LONG).show();
                }
            }
        });
    }






}