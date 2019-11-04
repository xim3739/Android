package com.example.reservationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private Button btStart, btOk;
    private RadioButton radioBtCalendar, radioBtTime;
    private CalendarView calendar;
    private TimePicker timePicker;

    private TextView textViewYear, textViewMonth, textViewDay, textViewHour, textViewMinute;

    private String strYear, strMonth, strDay, strHour, strMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약 앱");

        chronometer = findViewById(R.id.chronometer);
        calendar = findViewById(R.id.calendar);
        timePicker = findViewById(R.id.timePicker);

        btStart = findViewById(R.id.btStart);
        btOk = findViewById(R.id.btOk);

        radioBtCalendar = findViewById(R.id.ratioBtCalendar);
        radioBtTime = findViewById(R.id.ratioBtTime);

        textViewYear = findViewById(R.id.textViewYear);
        textViewMonth = findViewById(R.id.textViewMonth);
        textViewDay = findViewById(R.id.textViewDay);
        textViewHour = findViewById(R.id.textViewHour);
        textViewMinute = findViewById(R.id.textViewMinute);

        calendar.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);

        radioBtCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });
        radioBtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                strYear = String.valueOf(year);
                strMonth = String.valueOf(month+1);
                strDay = String.valueOf(dayOfMonth);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                strHour = String.valueOf(hourOfDay);
                strMinute = String.valueOf(minute);
            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                textViewYear.setText(strYear + "년");
                textViewMonth.setText(strMonth + "월");
                textViewDay.setText(strDay + "일");
                textViewHour.setText(strHour + "시");
                textViewMinute.setText(strMinute + "분");
            }
        });

    }
}
