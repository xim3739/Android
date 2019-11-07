package com.example.example8_1_diary;

import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener, View.OnClickListener {

    private DatePicker datePicker;
    private EditText editText;
    private Button btEdit, btSave, btExit;
    public static String fileName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        btEdit = findViewById(R.id.btEdit);
        btSave = findViewById(R.id.btSave);
        btExit = findViewById(R.id.btExit);


        getCurrentDate();

        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            editText.setText(new String(data));
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            editText.setText("");
            editText.setHint("없어요");
        } catch (IOException e) {
            e.printStackTrace();
        }


        datePicker.setOnDateChangedListener(this);

        btEdit.setOnClickListener(this);
        btSave.setOnClickListener(this);
        btExit.setOnClickListener(this);
    }

    private void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                FileInputStream fileInputStream = null;
                try {
                    fileName = String.valueOf(year) + String.valueOf(monthOfYear) + String.valueOf(dayOfMonth) + "_" + ".txt";
                    fileInputStream = openFileInput(fileName);
                    byte[] readDiary = new byte[fileInputStream.available()];
                    fileInputStream.read(readDiary);

                    editText.setText(new String(readDiary));

                    btEdit.setClickable(true);
                    btSave.setClickable(false);

                } catch (FileNotFoundException e) {
                    editText.setText("");
                    editText.setHint("파일이 없어요오");
                    btEdit.setClickable(false);
                    btSave.setClickable(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        FileInputStream fileInputStream = null;
        try {
            fileName = String.valueOf(year) + String.valueOf(monthOfYear) + String.valueOf(dayOfMonth) + "_" + ".txt";
            fileInputStream = openFileInput(fileName);
            byte[] readDiary = new byte[fileInputStream.available()];
            fileInputStream.read(readDiary);

            editText.setText(new String(readDiary));

            btEdit.setClickable(true);
            btSave.setClickable(false);

        }catch (FileNotFoundException e) {
            editText.setText("");
            editText.setHint("파일이 없어요오");
            btEdit.setClickable(false);
            btSave.setClickable(true);
        } catch (IOException e) {
            editText.setHint("파일이 없어요오");
            btEdit.setClickable(false);
            btSave.setClickable(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btEdit :
                fileEdit();
                break;
            case R.id.btSave :
                fileSave();
                break;

            case R.id.btExit:
                finish();
                break;
        }
    }

    private void fileSave() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            String data = editText.getText().toString();
            if(data.trim().length() >= 1) {
                fileOutputStream.write(data.getBytes());
                fileOutputStream.close();
                Toast.makeText(MainActivity.this, "저장 성공", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "저장 실패", Toast.LENGTH_SHORT).show();
            }
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileEdit() {

        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            String data = editText.getText().toString();
            if(data.trim().length() >= 1) {
                fileOutputStream.write(data.getBytes());
                fileOutputStream.close();
                Toast.makeText(MainActivity.this, "수정 성공", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "수정 실패", Toast.LENGTH_SHORT).show();
            }
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
