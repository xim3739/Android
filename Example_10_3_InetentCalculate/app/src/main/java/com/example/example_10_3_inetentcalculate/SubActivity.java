package com.example.example_10_3_inetentcalculate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btBack;
    private int result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btBack = findViewById(R.id.btBack);
        btBack.setOnClickListener(this);

        Intent intent = getIntent();
        int number1 = intent.getIntExtra("number1", 0);
        int number2 = intent.getIntExtra("number2", 0);
        int calValue = intent.getIntExtra("calValue", 0);

        switch (calValue) {
            case 0 :
                result = 0;
                break;
            case 1 :
                result = number1 + number2;
                break;
            case 2 :
                result = number1 - number2;
                break;
            case 3 :
                result = number1 * number2;
                break;
            case 4 :
                if(number2 <= 0) {
                    result = 0;
                } else {
                    result = number1 / number2;
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("result", result);
        setResult(1001, intent);
        finish();
    }
}
