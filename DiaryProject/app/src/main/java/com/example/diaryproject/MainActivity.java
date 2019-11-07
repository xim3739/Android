package com.example.diaryproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener, View.OnClickListener {
    private DatePicker datePicker;
    private EditText editText;
    private Button btSave;
    public static String fileName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        btSave = findViewById(R.id.btSave);

        pickCurrentDate();

        datePicker.setOnDateChangedListener(this);
        btSave.setOnClickListener(this);
    }

    private void pickCurrentDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, null);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        FileInputStream fileInputStream;

        try {
            fileName = String.valueOf(year) + String.valueOf(monthOfYear) + String.valueOf(dayOfMonth) + "_" + ".txt";
            fileInputStream = openFileInput(fileName);
            byte[] readDiary = new byte[fileInputStream.available()];
            fileInputStream.read(readDiary);

            editText.setText(new String(readDiary));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            editText.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSave :
                saveDiary();
                break;
        }
    }

    private void saveDiary() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            String diaryData = editText.getText().toString();
            if(diaryData.trim().length() >= 1) {
                fileOutputStream.write(diaryData.getBytes());
                fileOutputStream.close();
                toastDisaplay("저장완료");
            } else {
                toastDisaplay("실패");
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toastDisaplay(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
