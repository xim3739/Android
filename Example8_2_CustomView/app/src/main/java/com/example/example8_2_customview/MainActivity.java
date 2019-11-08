package com.example.example8_2_customview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btPrevious, btNext;
    private TextView textView;
    private CustomView customView;
    private File[] imageFile;
    private int currentPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btPrevious = findViewById(R.id.btPrevious);
        btNext = findViewById(R.id.btNext);
        textView = findViewById(R.id.textView);
        customView = findViewById(R.id.customView);

        textView.setText("1/4");

        imageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pic").listFiles();

        customView.setSrc(imageFile[0].toString().trim());
        customView.invalidate();

        btPrevious.setOnClickListener(this);
        btNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btPrevious :
                imageChangePrevious();
                break;
            case R.id.btNext :
                imageChangeNext();
                break;
        }
    }

    private void imageChangePrevious() {
        currentPoint -= 1;
        currentPoint = (currentPoint < 0) ? (3) : (currentPoint);
        customView.setSrc(imageFile[currentPoint].toString().trim());
        customView.invalidate();

        textView.setText(String.valueOf(currentPoint+1) + "/" + String.valueOf(imageFile.length));

        Toast.makeText(MainActivity.this, imageFile[currentPoint].toString().trim(), Toast.LENGTH_SHORT).show();

    }

    private void imageChangeNext() {
        currentPoint += 1;
        currentPoint = (currentPoint > 3) ? (0) : (currentPoint);
        customView.setSrc(imageFile[currentPoint].toString().trim());
        customView.invalidate();

        textView.setText(String.valueOf(currentPoint+1) + "/" + String.valueOf(imageFile.length));

        Toast.makeText(MainActivity.this, imageFile[currentPoint].toString().trim(), Toast.LENGTH_SHORT).show();
    }
}
