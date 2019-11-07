package com.example.innerfiletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btInnerFileWriter, btInnerFileReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInnerFileWriter = findViewById(R.id.btInnerFileWriter);
        btInnerFileReader = findViewById(R.id.btInnerFileReader);

        btInnerFileWriter.setOnClickListener(this);
        btInnerFileReader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btInnerFileWriter :
                fileInnerWrite();
                break;
            case R.id.btInnerFileReader :
                fileInnerRead();
                break;
        }

    }

    private void fileInnerWrite() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("file.txt", Context.MODE_PRIVATE);
            String string = "쿡북 안드로이드";
            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();
            toastDisplay("잘 생성 되었습니다.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void fileInnerRead() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput("file.txt");
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            toastDisplay(new String(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void toastDisplay(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
