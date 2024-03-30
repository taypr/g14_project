package com.example.g14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {


    public Button btn_fridge, btn_meals, btn_clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_fridge = (Button) findViewById(R.id.button_add);
        btn_clear = (Button) findViewById(R.id.button_clear);
        btn_meals = (Button) findViewById(R.id.button_meals);


        DBHelper dbHelper = new DBHelper(getApplicationContext(), "test_db",null, 1);

        btn_fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_meals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "meat";
                dbHelper.clearDatabase(title);
                title = "produce";
                dbHelper.clearDatabase(title);
                title = "dairy";
                dbHelper.clearDatabase(title);
                title = "pantry";
                dbHelper.clearDatabase(title);
                Toast.makeText(getApplicationContext(),"Fridge is empty!",Toast.LENGTH_SHORT).show();


            }
        });


    }

}