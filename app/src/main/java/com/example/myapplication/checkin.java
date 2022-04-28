package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import android.database.Cursor;

public class checkin extends AppCompatActivity {



    Button submitt;
    RadioButton one, two, three, four, five, six, seven, eight, nine, ten,
    eleven, twelve, thirteen, fourteen, fifteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        DatabaseHelper db;
        setTitle("Check-in");
        Button button = (Button) findViewById(R.id.homebtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(checkin.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isProf", login.isProf);

                bundle.putString("profCourseList", login.profCourseList);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //setting up the database
        db = new DatabaseHelper(checkin.this);
        submitt = findViewById(R.id.submit);
        one = findViewById(R.id.radioButton1);
        two = findViewById(R.id.radioButton2);
        three = findViewById(R.id.radioButton3);
        four = findViewById(R.id.radioButton4);
        five = findViewById(R.id.radioButton5);
        six = findViewById(R.id.radioButton6);
        seven = findViewById(R.id.radioButton7);
        eight = findViewById(R.id.radioButton8);
        nine = findViewById(R.id.radioButton9);
        ten = findViewById(R.id.radioButton10);
        eleven = findViewById(R.id.radioButton11);
        twelve = findViewById(R.id.radioButton12);
        thirteen = findViewById(R.id.radioButton13);
        fourteen = findViewById(R.id.radioButton14);
        fifteen = findViewById(R.id.radioButton15);
        if(one.isChecked()){
            two.setChecked(false);
        }
        if(twelve.isChecked()){
            thirteen.setChecked(false);
        }
        if(fourteen.isChecked()){
            fifteen.setChecked(false);
        }




        submitt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(one.isChecked()){
                    two.setChecked(false);

                }
                if(twelve.isChecked()){
                    thirteen.setChecked(false);
                }
                if(fourteen.isChecked()){
                    fifteen.setChecked(false);
                }


                if((!one.isChecked()) && (!two.isChecked())) {
                    Toast.makeText(checkin.this, "Please fill all the data", Toast.LENGTH_LONG).show();
                }
                if ((!three.isChecked()) &&
                        (!four.isChecked()) &&
                        (!five.isChecked())&&
                        (!six.isChecked())&&
                        (!seven.isChecked())&&
                        (!eight.isChecked())&&
                        (!nine.isChecked())&&
                        (!ten.isChecked())&&
                        (!eleven.isChecked())){
                    Toast.makeText(checkin.this, "Please fill all the data", Toast.LENGTH_LONG).show();

                }
                if((!twelve.isChecked()) && (!thirteen.isChecked())) {
                    Toast.makeText(checkin.this, "Please fill all the data", Toast.LENGTH_LONG).show();

                }
                if((!fourteen.isChecked()) && (!fifteen.isChecked())) {
                    Toast.makeText(checkin.this, "Please fill all the data", Toast.LENGTH_LONG).show();

                }
                String build = "";
                if ((three.isChecked()))
                        build = "Campus Center";

                if ((four.isChecked()))
                    build = "Taper Hall";

                if ((five.isChecked()))
                    build = "Salvatori";

                if ((six.isChecked()))
                    build = "Ferttita";

                if ((seven.isChecked()))
                    build = "Engemann";

                if ((eight.isChecked()))
                    build = "Kaufman";

                if ((nine.isChecked()))
                    build = "Kaprielian";

                if ((ten.isChecked()))
                    build = "Leventhal";

                if ((eleven.isChecked()))
                    build = "Annenberg";




                SQLiteDatabase database = db.getWritableDatabase();
                Cursor cursorVisit = database.rawQuery("Select * from Buildings " , null);
                int visit = 0;
                if(cursorVisit.getCount()>0){
                    while(cursorVisit.moveToNext()){
                        if(cursorVisit.getString(0).equals(build)){
                            visit = cursorVisit.getInt(2);
                            visit++;
                            ContentValues cv = new ContentValues();
                            cv.put("building_visit", visit);
                            database.update("Buildings", cv,"building_name=?", new String[]{build});
                        }
                    }
                }

                SQLiteDatabase read = db.getReadableDatabase();
                Cursor cursor = read.rawQuery("Select * from Buildings " , null);
                int count = -1;
                String frequent = "";
                if(cursor.getCount()>0) {
                    while (cursor.moveToNext()) {
                        if (cursor.getInt(2) > count) {
                            count = cursor.getInt(2);
                            frequent = cursor.getString(0);
                        }
                    }
                }
                Intent intent = new Intent(checkin.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(checkin.this, frequent+ " is visited " + count+" times and is the most frequently visited building.", Toast.LENGTH_LONG).show();

            }

        });

    }
}