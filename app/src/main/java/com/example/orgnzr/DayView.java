package com.example.orgnzr;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DayView extends AppCompatActivity {

    private String eightAM = "8", nineAM = "9", tenAM = "10", elevenAM = "11", noon = "12", onePM = "13", twoPM = "14", threePM = "15", fourPM = "16", fivePM = "17", sixPM = "18", sevenPM = "19", eightPM = "20", ninePM = "21";
    private TextView todaysDate, eightAMDetails, nineAMDetails, tenAMDetails, elevenAMDetails, noonDetails,
                     onePMDetails, twoPMDetails, threePMDetails, fourPMDetails, fivePMDetails, sixPMDetails,
                     sevenPMDetails, eightPMDetails, ninePMDetails;
    Button makeAppointment;
    String eight;
    DBHelper DB;

    public String getAppointment(String username, String appointmentDate, String appointmentHour){
        Cursor result = DB.fetchAppointmentByHour(username, appointmentDate, appointmentHour);
        if(result.getCount()==0){
            return "No Appointments";
        }
        else{
            StringBuffer buffer = new StringBuffer();
            while(result.moveToNext()){
                buffer.append(("Client Name: " + result.getString(1)+"\n"));
                buffer.append(("Start time: " + result.getString(3)+"\n\n"));

            }
            return buffer.toString();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        DB = new DBHelper(this);

        makeAppointment = findViewById(R.id.btnMakeAppointmentDaily);
        todaysDate = findViewById(R.id.todaysDate);
        eightAMDetails = findViewById(R.id.eightAMCalendarView);
        nineAMDetails = findViewById(R.id.nineAMCalendarView);
        tenAMDetails = findViewById(R.id.tenAMCalendarView);
        elevenAMDetails = findViewById(R.id.elevenAMCalendarView);
        noonDetails = findViewById(R.id.twelvePMCalendarView);
        onePMDetails = findViewById(R.id.onePMCalendarView);
        twoPMDetails = findViewById(R.id.twoPMCalendarView);
        threePMDetails = findViewById(R.id.threePMCalendarView);
        fourPMDetails = findViewById(R.id.fourPMCalendarView);
        fivePMDetails = findViewById(R.id.fivePMCalendarView);
        sixPMDetails = findViewById(R.id.sixPMCalendarView);
        sevenPMDetails = findViewById(R.id.sevenPMCalendarView);
        eightPMDetails = findViewById(R.id.eightPMCalendarView);
        ninePMDetails = findViewById(R.id.ninePMCalendarView);


        Intent incomingIntent = getIntent();
        final String date = incomingIntent.getStringExtra("date");
        final String username = incomingIntent.getStringExtra("username");
        todaysDate.setText(date);
        eightAMDetails.setText(getAppointment(username,date,eightAM));
        nineAMDetails.setText(getAppointment(username,date,nineAM));
        tenAMDetails.setText(getAppointment(username,date,tenAM));
        elevenAMDetails.setText(getAppointment(username,date,elevenAM));
        noonDetails.setText(getAppointment(username,date,noon));
        onePMDetails.setText(getAppointment(username, date, onePM));
        twoPMDetails.setText(getAppointment(username, date, twoPM));
        threePMDetails.setText(getAppointment(username, date, threePM));
        fourPMDetails.setText(getAppointment(username, date, fourPM));
        fivePMDetails.setText(getAppointment(username, date, fivePM));
        sixPMDetails.setText(getAppointment(username, date, sixPM));
        sevenPMDetails.setText(getAppointment(username, date, sevenPM));
        eightPMDetails.setText(getAppointment(username, date, eightPM));
        ninePMDetails.setText(getAppointment(username, date, ninePM));

        makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DayView.this, Appointment.class);
                intent.putExtra("date", date);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }



}