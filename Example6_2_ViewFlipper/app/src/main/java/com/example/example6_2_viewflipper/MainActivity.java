package com.example.example6_2_viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private Button btStart, btStop;
    private ViewFlipper viewFlipper;
    private ImageView image1, image2, image3, image4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);
        viewFlipper = findViewById(R.id.viewFlipper);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);

        image1.setImageResource(R.drawable.admin);
        image2.setImageResource(R.drawable.ic_launcher_background);
        image3.setImageResource(R.drawable.ic_launcher_foreground);
        image4.setImageResource(R.drawable.jjal);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setFlipInterval(500);
                viewFlipper.startFlipping();
            }
        });
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.stopFlipping();
            }
        });

    }
}
