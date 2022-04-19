
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Status extends AppCompatActivity  {

    public static List<User> data;
    DatabaseHelper db;
    private EditText fullName, email, password;
    public Button button;
    Button addUser, addFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        // creating an object of databaseHelper
        db = new DatabaseHelper(Status.this);

        TextView status = (TextView) findViewById(R.id.currStatus);
        String courseName = getIntent().getExtras().getString("courseName");
        status.setText("Current Status: " + db.getCourseStatus(courseName));
        Button online = (Button) findViewById(R.id.online);
        Button inperson = (Button) findViewById(R.id.inperson);
        Button hybrid = (Button) findViewById(R.id.hybrid);

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.updateCourseStatus(courseName, "Online");
                Toast.makeText(Status.this, "Course Updated", Toast.LENGTH_SHORT).show();
                status.setText("Online");
                Intent intent = new Intent(Status.this,faculty.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.updateCourseStatus(courseName, "Hybrid");
                Toast.makeText(Status.this, "Course Updated", Toast.LENGTH_SHORT).show();
                status.setText("Hybrid");
                Intent intent = new Intent(Status.this,faculty.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        inperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.updateCourseStatus(courseName, "In-Person");
                Toast.makeText(Status.this, "Course Updated", Toast.LENGTH_SHORT).show();
                status.setText("In-Person");
                Intent intent = new Intent(Status.this,faculty.class);
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
