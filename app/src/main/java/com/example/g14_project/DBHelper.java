package com.example.g14_project;


import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE course(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addValue(String titleInput, String descriptionInput){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", titleInput);
        contentValues.put("description", descriptionInput);

        return sqLiteDatabase.insert("course",null,contentValues);
    }

    public Cursor display(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM course", null);
    }

    public long editDatabase(String inputTitle, String descriptionInput){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("description",descriptionInput);
        return sqLiteDatabase.update("course",contentValues,"title=?", new String[]{inputTitle});
    }

    public int deleteData(String inputTitle){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("course", "title = ?",new String[]{inputTitle});
    }

    public int clearDatabase(String inputTitle) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("course", "title = ?", new String[]{inputTitle});
    }

}
