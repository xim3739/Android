package com.example.bundletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LOG";

    private ArrayList<Data> list = new ArrayList<>();

    private Button btClick;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        btClick = findViewById(R.id.btClick);
        editText = findViewById(R.id.editText);

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.add(new Data(100, "ddd"));
                list.add(new Data(233, "221"));
                list.add(new Data(444, "123"));
                list.add(new Data(555, "qwe"));
                list.add(new Data(666, "1521"));

                Bundle bundle = new Bundle();

                bundle.putString("name", "sim");
                bundle.putInt("age", 2323);

                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(1);
                arrayList.add(2);
                arrayList.add(3);
                arrayList.add(4);

                bundle.putIntegerArrayList("array", arrayList);

                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("data", list);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "onRestoreInstanceState");

        String name = savedInstanceState.getString("name");

        btClick.setText(name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState");

        String name = editText.getText().toString();

        outState.putString("name", name);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destroy");
    }

}
