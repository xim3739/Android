package com.example.mycustomcalendartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridView calendar;
    private TextView monthText;
    private Button btNext, btBack;
    private int currentYear;
    private int currentMonth;

    private CalendarAdapter calendarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendar);
        monthText = findViewById(R.id.monthText);
        btNext = findViewById(R.id.btNext);
        btBack = findViewById(R.id.btBack);

        calendarAdapter = new CalendarAdapter(this);
        calendar.setAdapter(calendarAdapter);

        calendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MonthItem currentItem = calendarAdapter.items[position];

                int day = currentItem.getDayValue();

                calendarAdapter.setSelectedPosition(day);
                calendarAdapter.notifyDataSetChanged();
            }
        });

        setMonthText();

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarAdapter.setPreviousMonth();
                calendarAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarAdapter.setNextMonth();
                calendarAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    private void setMonthText() {
        currentYear = calendarAdapter.getCurrentYear();
        currentMonth = calendarAdapter.getCurrentMonth();

        monthText.setText(currentYear + "년" + (currentMonth+1) + "월");
    }
}
