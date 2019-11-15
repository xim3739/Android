package com.example.example_11_1_gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<MovieData> list = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertListData();

        gridView = findViewById(R.id.gridView);

        movieAdapter = new MovieAdapter(MainActivity.this, R.layout.grid_itme_view, list);

        gridView.setAdapter(movieAdapter);

    }

    private void insertListData() {
        int[] imageId = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10, R.drawable.mov11, R.drawable.mov12};
        String[] nameList = {"그림 01", "그림 02", "그림 03", "그림 04", "그림 05", "그림 06",
                "그림 07", "그림 08", "그림 09", "그림 10", "그림 11", "그림 12"};
        for(int i = 0; i < 12; i++) {
            list.add(new MovieData(imageId[i], nameList[i]));
        }
    }
}
