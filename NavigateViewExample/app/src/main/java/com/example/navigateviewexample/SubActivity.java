package com.example.navigateviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ArrayList<String> mostRatingName = new ArrayList<String>();

    private Button btClose;

    private TextView[] textViewsList = new TextView[9];
    private Integer[] textViewIDList = {R.id.textView01, R.id.textView02, R.id.textView03, R.id.textView04, R.id.textView05,
            R.id.textView06, R.id.textView07, R.id.textView08, R.id.textView09};
    private RatingBar[] ratingBarsList = new RatingBar[9];
    private Integer[] ratingBarIDList = {R.id.ratingBar01, R.id.ratingBar02, R.id.ratingBar03, R.id.ratingBar04, R.id.ratingBar05,
            R.id.ratingBar06, R.id.ratingBar07, R.id.ratingBar08, R.id.ratingBar09};



    private String[] imageNameList = new String[9];
    private int[] count = new int[9];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        editText = findViewById(R.id.editText);
        btClose = findViewById(R.id.btClose);

        Intent intent = getIntent();
        count = intent.getIntArrayExtra("count");
        imageNameList = intent.getStringArrayExtra("imageNameList");

        for(int i = 0; i < textViewsList.length; i++) {
            textViewsList[i] = findViewById(textViewIDList[i]);
            textViewsList[i].setText(imageNameList[i]);

            ratingBarsList[i] = findViewById(ratingBarIDList[i]);
            ratingBarsList[i].setRating(count[i]);
        }

        for(int i = 0; i < textViewsList.length; i++) {
            if(ratingBarsList[i].getRating() == 5) {
                mostRatingName.add(textViewsList[i].getText().toString().trim());
            }
        }
        for(int i = 0; i < mostRatingName.size(); i++) {
            editText.setText(editText.getText().toString() + mostRatingName.get(i) + "\n");
        }

        btClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
