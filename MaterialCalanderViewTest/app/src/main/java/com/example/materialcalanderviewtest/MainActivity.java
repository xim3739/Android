package com.example.materialcalanderviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.icu.util.Calendar;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    MutableLiveData<ArrayList<Object>> mCalenderList = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void setmCalenderList() {

        GregorianCalendar cal = new GregorianCalendar();

        ArrayList<Object> calenderList = new ArrayList<>();

        for(int i = -300; i < 300; i++) {
            GregorianCalendar calendar = new GregorianCalendar(Calendar.YEAR,
                    cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);
            calenderList.add(calendar.getTimeInMillis());

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            for(int j = 0; j < dayOfWeek; j++) {
                calenderList.add(Keys.EMPTY);
            }
            for(int j = 0; j <= max; j++) {
                calenderList.add(new GregorianCalendar(calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), j));
            }
        }

        mCalenderList.setValue(calenderList);
    }
}
