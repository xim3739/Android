package com.example.myobjectsendintenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Data> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btClick = findViewById(R.id.btClick);

        btClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                list.add(new Data("시청", 201));
                list.add(new Data("을지로입구", 202));
                list.add(new Data("을지로 3가", 203));
                list.add(new Data("을지로 4가", 204));
                list.add(new Data("신당", 205));
                list.add(new Data("왕십리", 206));

                Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
                intent.putExtra("data", list);
                startActivity(intent);

            }
        });
    }
}
