package com.example.customlistviewstringtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<ListItemData> list = new ArrayList<ListItemData>();
    private ListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        arraylistInsertData();

        adapter = new ListItemAdapter(getApplicationContext(), R.layout.list_item, list);

        listView.setAdapter(adapter);

    }

    private void arraylistInsertData() {

        for(int i = 0; i < 10; i++) {
            list.add(new ListItemData("아"));
        }

    }

}
