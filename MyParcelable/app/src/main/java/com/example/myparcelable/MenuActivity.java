package com.example.myparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btBack = findViewById(R.id.btBack);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    private void processIntent(Intent passedIntent) {
        if(passedIntent != null) {
            ArrayList<String> names = (ArrayList<String>) passedIntent.getSerializableExtra("names");
            if(names != null) {
                Toast.makeText(getApplicationContext(), "전달 받은 이름 개수 : " + names.size(), Toast.LENGTH_SHORT).show();
            }

            SimpleData data = (SimpleData) passedIntent.getParcelableExtra("data");
            if(data != null) {
                Toast.makeText(getApplicationContext(), "전달 받은 simpledata : " + data.message + data.number, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
