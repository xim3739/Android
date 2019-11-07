package com.example.neweventaccesstest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btChoice, btItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btChoice = findViewById(R.id.btChoice);
        btItems = findViewById(R.id.btItems);

        btChoice.setOnClickListener(this);
        btItems.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btChoice :
                ArrayList<String> items = new ArrayList<String>();
                items.add("사과");
                items.add("딸기");
                items.add("오렌지");
                items.add("수박");

                final String[] list = items.toArray(new String[items.size()]);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle("목록 상자");
                dialog.setItems(list, null);
                dialog.show();

                break;

            case R.id.btItems :
                showDialogOption();
                break;
            default:
                break;
        }
    }

    private void showDialogOption() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("사과");
        items.add("딸기");
        items.add("오렌지");
        items.add("수박");

        final String[] list = new String[items.size()];
        int size = 0;
        for(String data:items) {
            list[size++] = data;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

        dialog.setTitle("선택 상자");
        dialog.setSingleChoiceItems(list, -1,null);
        dialog.show();

    }
}
