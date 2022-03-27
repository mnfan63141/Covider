package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class campus_list extends AppCompatActivity {

    ListView list;
    Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_list);
        setTitle("Campus List");
        Button button = (Button) findViewById(R.id.homebtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(campus_list.this,MainActivity.class);
                startActivity(intent);
            }
        });

        myIntent = new Intent(this, display_risk.class);
        list = (ListView) findViewById(R.id.campusList);
        String[] names = {"First"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(listClick);
    }

    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView parent, View v, int pos, long id) {
            String buildingName = (String) list.getItemAtPosition(pos);
            myIntent.putExtra("BUILDING_SELECTED", buildingName);
            startActivity(myIntent);
        }
    };
}