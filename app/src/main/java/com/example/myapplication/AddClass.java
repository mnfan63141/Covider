
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddClass extends AppCompatActivity  {

    DatabaseHelper db;
    private EditText id, loc;
    public Button button;
    Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        id = findViewById(R.id.courseId);
        loc = findViewById(R.id.courseLoc);
        // creating an object of databaseHelper
        db = new DatabaseHelper(AddClass.this);
        addUser = findViewById(R.id.addClass);
        // on click listener for add user
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idString = id.getText().toString();
                String locString = loc.getText().toString();
                // checking if all the fields have been filled
                if (idString.isEmpty() || locString.isEmpty()) {
                    Toast.makeText(AddClass.this, "Please fill all the data", Toast.LENGTH_SHORT).show();
                    return;
                }
                String cString = login.profCourseList;
                try {
                    db.addCourse(new Course(idString, locString));
                    cString = db.addCourseToProfessor(new Course(idString, locString), login.profCourseList);
                }catch(Exception e)
                {
                    Toast.makeText(AddClass.this, "Course Already Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(AddClass.this, "Class Added", Toast.LENGTH_SHORT).show();
                id.setText("");
                loc.setText("");
                Intent intent = new Intent(AddClass.this,faculty.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", login.isProf);

                bundle.putString("profCourseList", cString);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

//  database ends
