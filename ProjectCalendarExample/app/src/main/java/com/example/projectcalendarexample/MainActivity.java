package com.example.projectcalendarexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btPrevious, btNext;
    private TextView textViewMainDate;
    private GridView gridViewCalendar;

    private int currentYear;
    private int currentMonth;

    private CalendarAdapter calendarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMainDate = findViewById(R.id.textViewMainDate);
        btPrevious = findViewById(R.id.btPrevious);
        btNext = findViewById(R.id.btNext);
        gridViewCalendar = findViewById(R.id.gridViewCalendar);

        calendarAdapter = new CalendarAdapter(this);

        gridViewCalendar.setAdapter(calendarAdapter);

        setMonthText();

        btNext.setOnClickListener(this);
        btPrevious.setOnClickListener(this);

        gridViewCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
                TextView textViewDialog = dialogView.findViewById(R.id.textViewDialog);
                final EditText editTextDialog = dialogView.findViewById(R.id.editTextDialog);

                textViewDialog.setText(String.valueOf(calendarAdapter.getItemData()[position].getDayValue()));

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("INSERT_TEXT");
                dialogBuilder.setView(dialogView);
                dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), "Add Success!!" , Toast.LENGTH_SHORT).show();

                        String msg = editTextDialog.getText().toString().trim();
                        calendarAdapter.getItemData()[position].setText(msg);
                        calendarAdapter.notifyDataSetChanged();
                        setMonthText();
                    }
                });
                dialogBuilder.setNegativeButton("Close", null);
                dialogBuilder.show();
            }
        });

    }

    private void setMonthText() {

        currentYear = calendarAdapter.getCurrentYear();
        currentMonth = calendarAdapter.getCurrentMonth();

        textViewMainDate.setText(currentYear + "년" + (currentMonth + 1) + "월");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btNext :
                calendarAdapter.setNextMonth();
                calendarAdapter.notifyDataSetChanged();
                setMonthText();
                break;
            case R.id.btPrevious :
                calendarAdapter.setPreviousMonth();
                calendarAdapter.notifyDataSetChanged();
                setMonthText();
                break;
        }
    }
}
