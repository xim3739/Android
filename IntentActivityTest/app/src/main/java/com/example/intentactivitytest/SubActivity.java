package com.example.intentactivitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btBack;
    private int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btBack = findViewById(R.id.btBack);
        btBack.setOnClickListener(this);

        Intent intent = getIntent();
        int num1 = intent.getIntExtra("number1", 0);
        int num2 = intent.getIntExtra("number2", 0);

        sum = num1 + num2;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("sum", sum);
        setResult(1001, intent);
        finish();
    }
}
