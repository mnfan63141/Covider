package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        one = findViewById(R.id.sympYes);
        // Addie Created
        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked) {
                    two.setChecked(false);
                }
            }
        });
        two = findViewById(R.id.sympNo);
        // Addie Implement
        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked) {
                    one.setChecked(false);
                }
            }
        });
        three = findViewById(R.id.campusCenterRadio);
        four = findViewById(R.id.taperHallRadio);
        five = findViewById(R.id.salRadio);
        six = findViewById(R.id.fertitaRadio);
        seven = findViewById(R.id.engelRadio);
        eight = findViewById(R.id.kaufRadio);
        nine = findViewById(R.id.kapRadio);
        ten = findViewById(R.id.levenRadio);
        eleven = findViewById(R.id.annenRadio);
        twelve = findViewById(R.id.maskYes);
        // Addie created
        twelve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked) {
                    thirteen.setChecked(false);
                }
            }
        });
        thirteen = findViewById(R.id.maskNo);
        // Addie created
        thirteen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked) {
                    twelve.setChecked(false);
                }
            }
        });
        fourteen = findViewById(R.id.sanitizerYes);
        // Addie created
        fourteen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked) {
                    fifteen.setChecked(false);
                }
            }
        });
        fifteen = findViewById(R.id.sanitizerNo);
        // Addie created
        fifteen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked) {
                    fourteen.setChecked(false);
                }
            }
        });
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