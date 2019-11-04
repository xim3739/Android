package com.example.example6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private RadioButton radioBtDatePicker, radioBtTimePicker;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private TextView textViewYear, textViewMonth, textViewDay, textViewHour, textViewMinute;
    private String strYear, strMonth, strDay, strHour, strMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        radioBtDatePicker = findViewById(R.id.radioBtDatePicker);
        radioBtTimePicker = findViewById(R.id.radioBtTimePicker);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);

        textViewYear = findViewById(R.id.textViewYear);
        textViewMonth = findViewById(R.id.textViewMonth);
        textViewDay = findViewById(R.id.textViewDay);
        textViewHour = findViewById(R.id.textViewHour);
        textViewMinute = findViewById(R.id.textViewMinute);

        pickerVisiable(View.INVISIBLE, View.INVISIBLE);
        radioButtonVisiable(View.INVISIBLE, View.INVISIBLE);

        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
                radioButtonVisiable(View.VISIBLE, View.VISIBLE);
            }
        });

        radioBtDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerVisiable(View.VISIBLE, View.INVISIBLE);
            }
        });

        radioBtTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerVisiable(View.INVISIBLE, View.VISIBLE);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                strHour = String.valueOf(hourOfDay);
                strMinute = String.valueOf(minute);
            }
        });

        strYear = String.valueOf(datePicker.getYear());
        strMonth = String.valueOf(datePicker.getMonth());
        strDay = String.valueOf(datePicker.getDayOfMonth());

        textViewYear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                textViewYear.setText(strYear + "년");
                textViewMonth.setText(strMonth + "월");
                textViewDay.setText(strDay + "일");
                textViewHour.setText(strHour + "시");
                textViewMinute.setText(strMinute + "분");

                pickerVisiable(View.INVISIBLE, View.INVISIBLE);
                radioButtonVisiable(View.INVISIBLE, View.INVISIBLE);
                radioBtDatePicker.setChecked(false);
                radioBtTimePicker.setChecked(false);
                chronometer.stop();

                return false;
            }
        });

    }

    public void pickerVisiable(int date, int time) {
        datePicker.setVisibility(date);
        timePicker.setVisibility(time);
    }

    public void radioButtonVisiable(int date, int time) {
        radioBtDatePicker.setVisibility(date);
        radioBtTimePicker.setVisibility(time);
    }
}
