package com.example.orgnzr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(username TEXT primary key, name TEXT, email_address TEXT, user_password TEXT)");
        DB.execSQL("create Table Appointmentdetails(appointmentID INTEGER primary key AUTOINCREMENT, " +
                " clientname TEXT NOT NULL, " +
                " appointment_date TEXT NOT NULL, " +
                " appointment_time TEXT NOT NULL, " +
                " appointment_hour TEXT NOT NULL, " +
                " username TEXT, " +
                " FOREIGN KEY (username) REFERENCES Userdetails(username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
        DB.execSQL("drop Table if exists Appointmentdetails");
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

    public Boolean authenticateUsernamePassword(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ? and user_password =?", new String[]{username,password});

        if(cursor.getCount()>0){
            cursor.close();
            return true;
        } else{
            cursor.close();
            return false;
        }

    }

    //Build appointment addition function here
    public Boolean createAppointment(String clientName, String appointmentDate, String appointmentTime, String appointmentHour, String username){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("clientname", clientName);
        contentValues.put("appointment_date", appointmentDate);
        contentValues.put("appointment_time", appointmentTime);
        contentValues.put("appointment_hour",appointmentHour);
        contentValues.put("username", username);


        long result=DB.insert("Appointmentdetails","null",contentValues);
        if(result==-1){
            return false;
        } else{
            return true;
        }
    }

    public Cursor fetchAppointmentByHour(String username, String appointmentDate, String appointmentHour) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Appointmentdetails where username = ? and appointment_date = ? and appointment_hour = ?",
                new String[]{username, appointmentDate, appointmentHour});

        return cursor;
    }
}
