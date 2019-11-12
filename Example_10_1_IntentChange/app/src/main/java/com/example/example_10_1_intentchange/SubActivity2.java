package com.example.example_10_1_intentchange;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Button btBack2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        btBack2 = findViewById(R.id.btBack2);

        btBack2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
