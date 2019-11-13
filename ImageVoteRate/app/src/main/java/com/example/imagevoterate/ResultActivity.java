package com.example.imagevoterate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView[] textViewList = new TextView[9];
    private Integer[] textViewIdList = {R.id.textView01, R.id.textView02, R.id.textView03, R.id.textView04, R.id.textView05,
            R.id.textView06, R.id.textView07, R.id.textView08, R.id.textView09};
    private RatingBar[] ratingBarsList = new RatingBar[9];
    private Integer[] ratingBarsIDList = {R.id.ratingBar01, R.id.ratingBar02, R.id.ratingBar03,  R.id.ratingBar04,  R.id.ratingBar05,
            R.id.ratingBar06, R.id.ratingBar07, R.id.ratingBar08, R.id.ratingBar09};
    private Button btClose;

    private String[] imageNameList = new String[9];
    private int count[] = new int[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        imageNameList = intent.getStringArrayExtra("imageNameList");
        count = intent.getIntArrayExtra("count");

        for(int i = 0; i < textViewList.length; i++) {

            textViewList[i] = findViewById(textViewIdList[i]);
            textViewList[i].setText(imageNameList[i]);

            ratingBarsList[i] = findViewById(ratingBarsIDList[i]);
            ratingBarsList[i].setRating(count[i]);

        }

        btClose = findViewById(R.id.btClose);
        btClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btClose) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("message", "다 끝났어요");
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
