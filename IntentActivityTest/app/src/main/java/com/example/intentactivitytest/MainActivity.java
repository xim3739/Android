package com.example.intentactivitytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btClick;
    private EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btClick = findViewById(R.id.btClick);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        btClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(MainActivity.this, SubActivity.class);

        intent.putExtra("number1", Integer.parseInt(editText1.getText().toString().trim()));
        intent.putExtra("number2", Integer.parseInt(editText2.getText().toString().trim()));

        startActivityForResult(intent, 1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent returnIntent) {
        super.onActivityResult(requestCode, resultCode, returnIntent);

        if(requestCode == 1000 && resultCode == 1001) {
            int sum = returnIntent.getIntExtra("sum", 0);
            Toast.makeText(MainActivity.this, "sum is " + sum, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "wrong System Error", Toast.LENGTH_SHORT).show();
        }

    }
}
