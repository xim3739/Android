package com.example.example_11_3_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<MySpinnerData> list = new ArrayList<>();
    private MySpinnerAdapter mySpinnerAdapter;
    public static ImageView mainImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertMovieData();

        mainImageView = findViewById(R.id.mainImageView);
        spinner = findViewById(R.id.spinner);
        mySpinnerAdapter = new MySpinnerAdapter(this, R.layout.my_spinner, list);
        spinner.setAdapter(mySpinnerAdapter);

    }

    private void insertMovieData() {
        String[] movieName = {"영화 1", "영화 2", "영화 3", "영화 4", "영화 5", "영화 6", "영화 7", "영화 8", "영화 9", "영화 10"};
        Integer[] moiveID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};

        for(int i = 0; i <= 9; i++) {
            list.add(new MySpinnerData(movieName[i], moiveID[i]));
        }
    }
}
