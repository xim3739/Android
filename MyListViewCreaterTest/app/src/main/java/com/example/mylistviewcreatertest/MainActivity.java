package com.example.mylistviewcreatertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyData> list = new ArrayList<>();
    private MyAdapter myAdapter;
    private int[] imageId = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
            R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
    private String[] imageNames = {"그림01", "그림02",  "그림03", "그림04", "그림05", "그림06", "그림07", "그림08", "그림09", "그림10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertListData();

        listView = findViewById(R.id.listView);
        myAdapter = new MyAdapter(this, list, R.layout.list_item);
        listView.setAdapter(myAdapter);
    }

    private void insertListData() {
        for(int i = 0; i < 20; i++) {
            list.add(new MyData(imageId[(int) (Math.random()*10)], imageNames[(int) (Math.random()*10)]));
        }
    }
}
