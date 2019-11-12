package com.example.example_10_1_intentchange;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity1 extends AppCompatActivity implements View.OnClickListener {

    private Button btBack1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        btBack1 = findViewById(R.id.btBack1);

        btBack1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
