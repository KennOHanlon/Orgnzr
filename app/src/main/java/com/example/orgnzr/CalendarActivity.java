package com.example.orgnzr;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CalendarActivity extends AppCompatActivity {

    private TextView usernameCalendar;
    private CalendarView mCalendarView;
    Button btnMakeAppointment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent incomingIntent = getIntent();
        final String username = incomingIntent.getStringExtra("username");

        btnMakeAppointment = findViewById(R.id.makeAppointmentCalendar);
        btnMakeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAppointment = new Intent(CalendarActivity.this, Appointment.class);
                intentAppointment.putExtra("username",username);
                startActivity(intentAppointment);
            }
        });

        usernameCalendar = findViewById(R.id.usernameCalendar);
        usernameCalendar.setText(username);

        mCalendarView = findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                month = month+1;
                String date = month + "/" + dayOfMonth +"/" + year;
                Intent intent = new Intent(CalendarActivity.this, DayView.class);
                intent.putExtra("date", date);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }
}