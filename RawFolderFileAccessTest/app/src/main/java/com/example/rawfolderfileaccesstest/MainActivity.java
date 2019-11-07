package com.example.rawfolderfileaccesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btRawFileRead;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRawFileRead = findViewById(R.id.btRawFileRead);
        editText = findViewById(R.id.editText);

        btRawFileRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btRawFileRead :
                readRawFile();
                break;
        }
    }

    private void readRawFile() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.raw_test);
            byte[] txt = new byte[inputStream.available()];
            inputStream.read(txt);
            editText.setText(new String(txt));
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
