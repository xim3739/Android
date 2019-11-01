package com.example.exmple5_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private Button[] button = new Button[10];
    private Integer[] valueId = {R.id.bt0, R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9};
    private Button plus, minus, multi, divide;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multi = findViewById(R.id.multi);
        divide = findViewById(R.id.divide);
        result = findViewById(R.id.result);

        for(int i = 0; i < button.length; i++) {
            final int index;
            index = i;
            button[index] = findViewById(valueId[index]);
        }

        editText1.setOnFocusChangeListener (new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    for(int i = 0; i < button.length; i++) {
                        final int index1;
                        index1 = i;
                        button[index1].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText1.setText(editText1.getText().toString() + button[index1].getText().toString());
                            }
                        });
                    }
                }else {
                    return;
                }
            }
        });

        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    for(int i = 0; i < button.length; i++) {
                        final int index1;
                        index1 = i;
                        button[index1].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editText2.setText(editText2.getText().toString() + button[index1].getText().toString());
                            }
                        });
                    }
                }else {
                    return;
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요.", Toast.LENGTH_LONG).show();
                } else {
                    int sum = Integer.parseInt(editText1.getText().toString()) + Integer.parseInt(editText2.getText().toString());
                    result.setText(String.valueOf(sum));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요.", Toast.LENGTH_LONG).show();
                } else {
                    int sub = Integer.parseInt(editText1.getText().toString()) - Integer.parseInt(editText2.getText().toString());
                    result.setText(String.valueOf(sub));
                }
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요.", Toast.LENGTH_LONG).show();
                } else {
                    int mul = Integer.parseInt(editText1.getText().toString()) * Integer.parseInt(editText2.getText().toString());
                    result.setText(String.valueOf(mul));
                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요.", Toast.LENGTH_LONG).show();
                } else {
                    int divi = Integer.parseInt(editText1.getText().toString()) / Integer.parseInt(editText2.getText().toString());
                    result.setText(String.valueOf(divi));
                }
            }
        });

    }
}
