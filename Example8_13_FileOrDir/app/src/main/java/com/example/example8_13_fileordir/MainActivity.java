package com.example.example8_13_fileordir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btAddDir, btRemoveDir;
    private File fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btAddDir = findViewById(R.id.btAddDir);
        btRemoveDir = findViewById(R.id.btRemoveDir);

        fileName = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/newFolder");

        btAddDir.setOnClickListener(this);
        btRemoveDir.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAddDir :
                if(fileName.exists()) {
                    Toast.makeText(MainActivity.this, fileName.toString().trim() + "파일이 이미 있습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    fileName.mkdir();
                    Toast.makeText(MainActivity.this, fileName.toString().trim() + "파일을 만들었습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btRemoveDir :
                if(fileName.exists()) {
                    fileName.delete();
                    Toast.makeText(MainActivity.this, fileName.toString().trim() + "파일을 지웠습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "파일이 존재 하지 않습니다." , Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
