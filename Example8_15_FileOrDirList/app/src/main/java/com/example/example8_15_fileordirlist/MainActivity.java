package com.example.example8_15_fileordirlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btFileList;
    private EditText editText;
    private File[] fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        fileList = (new File(Environment.getExternalStorageDirectory().getAbsolutePath())).listFiles();

        btFileList = findViewById(R.id.btFileList);
        editText = findViewById(R.id.editText);

        btFileList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        editText.setText("");
        for(int i = 0; i <fileList.length; i++){
            if(fileList[i].isDirectory()) {
                editText.setText(editText.getText().toString().trim() + "\n" +"폴더 : " + fileList[i].toString().trim());
            } else {
                editText.setText(editText.getText().toString().trim() + "\n" +"파일 : " + fileList[i].toString().trim());
            }
        }

    }
}
