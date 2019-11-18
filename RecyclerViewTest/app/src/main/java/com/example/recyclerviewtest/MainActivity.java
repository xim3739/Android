package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainData> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button btAdd;
    private LinearLayoutManager linearLayoutManager;
    private MainAdapter mainAdapter;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btAdd = findViewById(R.id.btAdd);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(list, R.layout.list_item_holder);
        recyclerView.setAdapter(mainAdapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 1;
                list.add(new MainData(R.mipmap.ic_launcher, "심재현" + count++, "졸려"));
                mainAdapter.notifyDataSetChanged();
            }
        });
    }
}
