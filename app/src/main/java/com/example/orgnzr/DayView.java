package com.example.orgnzr;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DayView extends AppCompatActivity {

    private TextView todaysDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        todaysDate = findViewById(R.id.todaysDate);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        todaysDate.setText(date);

    }
}