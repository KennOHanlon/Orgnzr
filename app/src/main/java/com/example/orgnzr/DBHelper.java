package com.example.orgnzr;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(username TEXT primary key, name TEXT, email_address TEXT, user_password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean createUserAccount(String username,String name, String emailAddress, String userPassword){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("email_address", emailAddress);
        contentValues.put("user_password", userPassword);

        long result=DB.insert("Userdetails","null",contentValues);
        if(result==-1){
            return false;
        } else{
            return true;
        }
    }
}
