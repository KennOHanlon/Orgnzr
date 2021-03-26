package com.example.orgnzr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Appointment extends AppCompatActivity {
    private static final String TAG = "Appointment";
    private String format = "";
    String appointmentHour;
    TextView clientName, appointmentDate, appointmentStartTime;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    TimePickerDialog.OnTimeSetListener mTimeSetListener;
    Button makeAppointment, viewMonth;

    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        appointmentDate = findViewById(R.id.appointmentDate);

        DB = new DBHelper(this);
        Intent incomingIntent = getIntent();
        final String username = incomingIntent.getStringExtra("username");
        String date = incomingIntent.getStringExtra("date");
        if(date!=null){
            appointmentDate.setText(date);
        }

        clientName = findViewById(R.id.clientName);
        makeAppointment = findViewById(R.id.btnMakeAppointment);
        viewMonth = findViewById(R.id.btnViewMonth);




        appointmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Appointment.this,
                        android.R.style.Widget,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));



                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                appointmentDate.setText(date);

            }
        };


        appointmentStartTime = findViewById(R.id.appointmentStartTime);
        appointmentStartTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        Appointment.this,
                        android.R.style.Widget,
                        mTimeSetListener,
                        hour, minute, false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeSet: hh:mm: " + hourOfDay + ":" + minute);
                appointmentHour = String.valueOf(hourOfDay);
                showTime(hourOfDay,minute);
            }
        };

        makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clientNameTXT = clientName.getText().toString();
                String appointmentDateTXT = appointmentDate.getText().toString();
                String appointmentTimeTXT = appointmentStartTime.getText().toString();

                Boolean inputClientAppointment = DB.createAppointment(clientNameTXT, appointmentDateTXT, appointmentTimeTXT, appointmentHour, username);
                if (inputClientAppointment) {
                    Toast.makeText(Appointment.this, "Appointment booked for: " + clientNameTXT, Toast.LENGTH_SHORT).show();
                    Intent intentCalendar = new Intent(Appointment.this, CalendarActivity.class);
                    intentCalendar.putExtra("username", username);
                    startActivity(intentCalendar);
                } else {
                    Toast.makeText(Appointment.this, "Appointment not created", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //navigates back to month view
        viewMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCalendar = new Intent(Appointment.this, CalendarActivity.class);
                intentCalendar.putExtra("username", username);
                startActivity(intentCalendar);
            }
        });

    }
    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        if(min == 0){
            appointmentStartTime.setText(new StringBuilder().append(hour).append(":").append(min)
                    .append("0 ").append(format));
        } else if(min < 10 && min > 0){
            appointmentStartTime.setText(new StringBuilder().append(hour).append(":0").append(min)
                    .append(" ").append(format));
        }
        else {
            appointmentStartTime.setText(new StringBuilder().append(hour).append(":").append(min)
                    .append(" ").append(format));
        }
        }
}

