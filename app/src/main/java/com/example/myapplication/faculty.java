package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class faculty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Faculty", "onCreate: ");
        super.onCreate(savedInstanceState);
        setTitle("Faculty");
        setContentView(R.layout.activity_faculty);
        Log.d("Faculty", "onCreate: ");
        Button button = (Button) findViewById(R.id.homebtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(faculty.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", getIntent().getExtras().getBoolean("isProf"));

                bundle.putString("profCourseList", getIntent().getExtras().getString("profCourseList"));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        // add new table to activity_faculty.xml
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);





        String courseString = getIntent().getExtras().getString("profCourseList");
        Log.v("Faculty", "courseString: " + courseString);
        Professor prof = Professor.parseCourseIdList(courseString, "", "","");
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

        tableLayout.addView(row);

        for(int i = 0; i < prof.getCourses().size(); i++) {
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
            b1.setText(prof.getCourses().get(i).getId());
            b1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
            tableRow.addView(b1);
            TextView c1 = new TextView(this);
            c1.setText(prof.getCourses().get(i).getLocation());
            c1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
            tableRow.addView(c1);
            TextView d1 = new TextView(this);
            d1.setText(prof.getCourses().get(i).calculateRisk()+"");
            d1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
            tableRow.addView(d1);
            tableLayout.addView(tableRow);
        }
        // add tablelayout to activity_faculty.xml
        this.addContentView(tableLayout, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
    }

}