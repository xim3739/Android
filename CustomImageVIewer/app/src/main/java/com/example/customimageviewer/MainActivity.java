package com.example.customimageviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btPrevious, btNext;
    private MyPictureView myPictureView;
    private File[] imageFile;
    private int currentPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btPrevious = findViewById(R.id.btPrevious);
        btNext = findViewById(R.id.btNext);
        myPictureView = findViewById(R.id.myPictureView);

        imageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pic").listFiles();

        myPictureView.setSrc(imageFile[currentPoint].toString().trim());
        myPictureView.invalidate();

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

    private void imageChangeNext() {
        currentPoint +=1;
        currentPoint = (currentPoint > 3) ? (0) : (currentPoint);
        myPictureView.setSrc(imageFile[currentPoint].toString().trim());
        myPictureView.invalidate();
    }

    private void imageChangePrevious() {
        currentPoint -= 1;
        currentPoint = (currentPoint < 0) ? (3) : (currentPoint);
        myPictureView.setSrc(imageFile[currentPoint].toString().trim());
        myPictureView.invalidate();
    }
}
