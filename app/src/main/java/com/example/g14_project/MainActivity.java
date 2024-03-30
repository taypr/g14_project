package com.example.g14_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EditText et_title, et_description;
    Button btn_add, btn_edit, btn_delete, btn_display;

    public Button button_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_title = findViewById(R.id.editText_title);
        et_description = findViewById(R.id.editText_des);
        btn_add = findViewById(R.id.button_add);
        btn_delete = findViewById(R.id.button_del);
        btn_display = findViewById(R.id.button_dis);
        button_home = (Button) findViewById(R.id.button_home);



        DBHelper dbHelper = new DBHelper(getApplicationContext(), "test_db",null, 1);

        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString();
                String description = et_description.getText().toString();
                long row = dbHelper.addValue(title, description);
                if(row < 0){
                    Toast.makeText(getApplicationContext(), "Uh Oh Couldn't Add Item!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Food Item Added!",Toast.LENGTH_LONG).show();

                }
                et_title.setText("");
                et_description.setText("");

            }
        });
        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "";
                Cursor cursor = dbHelper.display();
                while (cursor.moveToNext()){
                    data +="Title: "+cursor.getString(1)+"   Description: "+cursor.getString(2)+"\n";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Here's whats in your fridge!");
                builder.setMessage(data);
                builder.show();
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString();
                dbHelper.deleteData(title);
            }
        });
    }




}