package com.example.example_10_3_inetentcalculate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public static final int SUM = 1, MINUS = 2, MULTI = 3, DIVIDE = 4;

    private EditText editText1, editText2;
    private RadioButton radioButtonSum, radioButtonMinus, radioButtonMulti, radioButtonDivide;
    private RadioGroup radioGroup;
    private Button btCalculate;
    private int saveRadioButtonValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        radioGroup = findViewById(R.id.radioGroup);
        radioButtonSum = findViewById(R.id.radioButtonSum);
        radioButtonMinus = findViewById(R.id.radioButtonMinus);
        radioButtonMulti = findViewById(R.id.radioButtonMulti);
        radioButtonDivide = findViewById(R.id.radioButtonDivide);

        btCalculate = findViewById(R.id.btCalculate);

        btCalculate.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);


        intent.putExtra("number1", Integer.parseInt(editText1.getText().toString().trim()));
        intent.putExtra("number2", Integer.parseInt(editText2.getText().toString().trim()));
        intent.putExtra("calValue", saveRadioButtonValue);

        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000 && resultCode == 1001) {
            int result = data.getIntExtra("result", 0);
            if(result == 0) {
                Toast.makeText(MainActivity.this, "잘못된형식입니다", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "계산 결과는 : " + result, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "잘못된형식입니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonSum :
                saveRadioButtonValue = SUM;
                break;
            case R.id.radioButtonMinus :
                saveRadioButtonValue = MINUS;
                break;
            case R.id.radioButtonMulti :
                saveRadioButtonValue = MULTI;
                break;
            case R.id.radioButtonDivide :
                saveRadioButtonValue = DIVIDE;
                break;
        }
    }
}
