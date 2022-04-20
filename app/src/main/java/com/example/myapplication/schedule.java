package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Schedule", "onCreate: ");
        super.onCreate(savedInstanceState);
        setTitle("Schedule");
        setContentView(R.layout.activity_schedule);
        Log.d("Schedule", "onCreate: ");
        Button button = (Button) findViewById(R.id.homebtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(schedule.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", login.isProf);

                bundle.putString("profCourseList", login.profCourseList);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        // add new table to activity_faculty.xml
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

        TableRow row = new TableRow(this);
        TextView b = new TextView(this);
        b.setText("Id");
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        row.addView(b);
        TextView c = new TextView(this);
        c.setText("Location");
        c.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        row.addView(c);
        TextView d = new TextView(this);
        d.setText("Risk");
        d.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        row.addView(d);
        TextView e = new TextView(this);
        e.setText("Course Status");
        e.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        row.addView(e);
        tableLayout.addView(row);

        Course[] courses = null;
        if (login.isProf) {
            courses = Professor.parseCourseIdList(login.profCourseList, "", "", "").getCourses().toArray(new Course[0]);
        } else {
            courses = login.db.getCourses(login.loginUser);
        }
        if (courses != null) {
            for (int i = 0; i < courses.length; i++) {
                // add row to table
                TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tableRowParams.setMargins(0, 0, 0, 0);
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(tableRowParams);
                tableRow.setPadding(0, 0, 0, 0);
                //   tableRow.setBackgroundColor(getResources().getColor(R.color));
                tableRow.setGravity(View.TEXT_ALIGNMENT_CENTER);
                // add course location
                TextView b1 = new TextView(this);
                b1.setText(courses[i].getId());
                b1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
                tableRow.addView(b1);
                TextView c1 = new TextView(this);
                c1.setText(courses[i].getLocation());
                c1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
                tableRow.addView(c1);
                TextView d1 = new TextView(this);
                d1.setText(courses[i].calculateRisk(schedule.this) + "");
                d1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
                tableRow.addView(d1);
                TextView e1 = new TextView(this);
                e1.setText(courses[i].getStatus() + "");
                e1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
                tableRow.addView(e1);
                tableLayout.addView(tableRow);
            }
            // add tablelayout to activity_faculty.xml
            this.addContentView(tableLayout, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        }
    }

}