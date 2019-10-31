        package com.example.example4_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Key;

        public class MainActivity extends AppCompatActivity {

    private EditText editTextFirst, editTextSecond;
    private Button buttonSum, buttonSub, buttonMultiple, buttonDivide;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFirst = findViewById(R.id.editTextFirst);
        editTextSecond = findViewById(R.id.editTextSecond);

        buttonSum = findViewById(R.id.buttonSum);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMultiple = findViewById(R.id.buttonMultiple);
        buttonDivide = findViewById(R.id.buttonDivide);

        textViewResult = findViewById(R.id.textViewResult);

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextFirst.getText().toString().isEmpty() || editTextSecond.getText().toString().isEmpty()) {
                    textViewResult.setText("숫자를 입력하세요");
                } else {
                    int sum = Integer.parseInt(editTextFirst.getText().toString().trim()) + Integer.parseInt(editTextSecond.getText().toString().trim());
                    textViewResult.setText(String.valueOf(sum));
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextFirst.getText().toString().isEmpty() || editTextSecond.getText().toString().isEmpty()) {
                    textViewResult.setText("숫자를 입력하세요");
                } else {
                    int sum = Integer.parseInt(editTextFirst.getText().toString().trim()) - Integer.parseInt(editTextSecond.getText().toString().trim());
                    textViewResult.setText(String.valueOf(sum));
                }
            }
        });

        buttonMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextFirst.getText().toString().isEmpty() || editTextSecond.getText().toString().isEmpty()) {
                    textViewResult.setText("숫자를 입력하세요");
                } else {
                    int sum = Integer.parseInt(editTextFirst.getText().toString().trim()) * Integer.parseInt(editTextSecond.getText().toString().trim());
                    textViewResult.setText(String.valueOf(sum));
                }
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextFirst.getText().toString().isEmpty() || editTextSecond.getText().toString().isEmpty()) {
                    textViewResult.setText("숫자를 입력하세요");
                } else {
                    int sum = Integer.parseInt(editTextFirst.getText().toString().trim()) / Integer.parseInt(editTextSecond.getText().toString().trim());
                    textViewResult.setText(String.valueOf(sum));
                }
            }
        });
    }

}
