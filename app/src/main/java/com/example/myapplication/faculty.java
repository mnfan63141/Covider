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
                bundle.putBoolean("isProf", login.isProf);

                bundle.putString("profCourseList", login.profCourseList);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button button1 = (Button) findViewById(R.id.addClass);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(faculty.this,AddClass.class);
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





        String courseString = login.profCourseList.replaceAll(" +", " ").trim();
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
        TextView e = new TextView(this);
        e.setText("Send Home");
        e.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        row.addView(e);
        TextView f = new TextView(this);
        f.setText("Add Student");
        f.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        row.addView(f);
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
            d1.setText(prof.getCourses().get(i).calculateRisk(faculty.this)+"");
            d1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
            tableRow.addView(d1);
            Button e1 = new Button(this);
            final Professor p = prof;
            final int j = i;
            e1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(faculty.this,Status.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isProf", login.isProf);

                    bundle.putString("profCourseList", login.profCourseList);
                    bundle.putString("courseName", p.getCourses().get(j).getId());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            e1.setText("Class Status");
            tableRow.addView(e1);
            Button f1 = new Button(this);

            f1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(faculty.this,Adder.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isProf", login.isProf);

                    bundle.putString("profCourseList", login.profCourseList);
                    bundle.putString("courseName", p.getCourses().get(j).getId());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            f1.setText("Register");
            tableRow.addView(f1);
            tableLayout.addView(tableRow);
            DatabaseHelper db = new DatabaseHelper(this);
            String [] emails = db.emailList(p.getCourses().get(j).getId());
            if(emails != null) {
                {
                    TableLayout.LayoutParams tableRowParams1 = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                    tableRowParams1.setMargins(0, 0, 0, 0);
                    TableRow tableRow1 = new TableRow(this);
                    tableRow1.setLayoutParams(tableRowParams1);
                    tableRow1.setPadding(0, 0, 0, 0);
                    TextView b2 = new TextView(this);
                    b2.setText("Roster");
                    b2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
                    tableRow1.addView(b2);
                    tableLayout.addView(tableRow1);
                }
                for (int s = 0; s < emails.length; s++) {
                    TableLayout.LayoutParams tableRowParams1 = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                    tableRowParams1.setMargins(0, 0, 0, 0);
                    TableRow tableRow1 = new TableRow(this);
                    tableRow1.setLayoutParams(tableRowParams1);
                    tableRow1.setPadding(0, 0, 0, 0);
                    //   tableRow.setBackgroundColor(getResources().getColor(R.color));
                    tableRow1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    TextView b2 = new TextView(this);
                    b2.setText(emails[s]);
                    b2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
                    tableRow1.addView(b2);
                    tableLayout.addView(tableRow1);

                }
            }
        }
        // add tablelayout to activity_faculty.xml
        this.addContentView(tableLayout, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
    }

}