package com.example.example_10_1_intentchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2,radioButton3;
    private Button btClick;
    private int saveValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioGroup = findViewById(R.id.radioGroup);
        btClick = findViewById(R.id.btClick);

        radioGroup.setOnCheckedChangeListener(this);
        btClick.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButton1 :
                saveValue = 1;
                break;
            case R.id.radioButton2 :
                saveValue = 2;
                break;
            case R.id.radioButton3 :
                saveValue = 3;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (saveValue) {
            case 1 :
                intent = new Intent(MainActivity.this, SubActivity1.class);
                startActivity(intent);
                break;
            case 2 :
                intent = new Intent(MainActivity.this, SubActivity2.class);
                startActivity(intent);
                break;
            case 3 :
                intent = new Intent(MainActivity.this, SubActivity3.class);
                startActivity(intent);
                break;
        }
    }
}
