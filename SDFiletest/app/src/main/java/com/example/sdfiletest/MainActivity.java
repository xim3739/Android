package com.example.sdfiletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btRead;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRead = findViewById(R.id.btRead);
        editText = findViewById(R.id.editText);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try{
            FileInputStream fileInputStream = new FileInputStream(/sdcard);
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            editText.setText(new String(data));
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
