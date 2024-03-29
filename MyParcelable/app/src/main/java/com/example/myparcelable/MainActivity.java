package com.example.myparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btShowMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btShowMenu = findViewById(R.id.btShowMenu);

        btShowMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        //arraylist 로 정보 넘기기
        ArrayList<String> names = new ArrayList();
        names.add("김진수");
        names.add("황수연");
        names.add("최진수");
        intent.putExtra("names", names);

        //parcel 형태로 정보 넘기기
        SimpleData data = new SimpleData(100, "hello");
        intent.putExtra("data", data);

        startActivityForResult(intent, 101);
    }
}
