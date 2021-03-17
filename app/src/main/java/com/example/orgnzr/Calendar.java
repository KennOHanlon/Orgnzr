package com.example.orgnzr;

import android.content.Intent;
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Calendar extends AppCompatActivity {

    private TextView usernameCalendar;
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent incomingIntent = getIntent();
        String username = incomingIntent.getStringExtra("username");

        usernameCalendar = findViewById(R.id.usernameCalendar);
        usernameCalendar.setText(username);

        mCalendarView = findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                month = month+1;
                String date = month + "/" + dayOfMonth +"/" + year;
                Intent intent = new Intent(Calendar.this, DayView.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

    }
}