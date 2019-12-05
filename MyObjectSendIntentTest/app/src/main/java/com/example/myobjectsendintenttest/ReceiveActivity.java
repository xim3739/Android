package com.example.myobjectsendintenttest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReceiveActivity extends AppCompatActivity {

    private static final String LIST_DATA_TAG = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Intent intent = getIntent();

        ArrayList<Data> list = null;

        list = (ArrayList<Data>) intent.getSerializableExtra("data");

        Data data = list.get(2);
        Log.d("LIST_DATA_TAG", data.toString());

    }

}
