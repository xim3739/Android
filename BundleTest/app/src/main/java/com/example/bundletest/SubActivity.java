package com.example.bundletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    public static final String TAG = "LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();

        ArrayList<Data> list = (ArrayList<Data>) intent.getSerializableExtra("data");

        Bundle bundle = intent.getBundleExtra("bundle");
        ArrayList<Integer> arrayList = bundle.getIntegerArrayList("array");

        Log.d(TAG, arrayList.toString());

    }
}
