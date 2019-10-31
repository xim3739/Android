package com.example.buttongonevisible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bt1, bt2, bt3, bt4, bt5;
    float rotationValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt3.getVisibility() == View.VISIBLE) {
                    bt3.setVisibility(View.INVISIBLE);
                } else {
                    bt3.setVisibility(View.VISIBLE);
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt4.getVisibility() == View.VISIBLE) {
                    bt4.setVisibility(View.INVISIBLE);
                } else {
                    bt4.setVisibility(View.VISIBLE);
                }
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotationValue = rotationValue + 5.0f;
                bt1.setRotation(rotationValue);
            }
        });



    }
}
