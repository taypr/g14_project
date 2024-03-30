package com.example.g14_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    public Button btn_fridgecontent, meals_available, btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        DBHelper dbHelper = new DBHelper(getApplicationContext(), "test_db",null, 1);

        btn_home = (Button) findViewById(R.id.button_home);
        btn_fridgecontent = (Button) findViewById(R.id.button_content);
        meals_available = (Button) findViewById(R.id.button_meals);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        meals_available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Crafting a meal!",Toast.LENGTH_SHORT).show();
            }
        });

        btn_fridgecontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "";
                Cursor cursor = dbHelper.display();
                while (cursor.moveToNext()){
                    data +="Title: "+cursor.getString(1)+"   Description: "+cursor.getString(2)+"\n";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setTitle("Inside the Fridge!");
                builder.setMessage(data);
                builder.show();
            }
        });



    }
}