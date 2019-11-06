package com.example.example_mulichoiceitems;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btClick;
    private boolean[] checkArray = new boolean[] {false, false, false};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btClick = findViewById(R.id.btClick);

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                final String[] itemsArray = new String[] {"누가 크래커", "오레오 스낵", "호두파이"};


                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("골라볼래?");
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setMultiChoiceItems(itemsArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        String str = "";
                        for(int i = 0; i < checkArray.length; i++) {
                            if(checkArray[i]) {
                                str = str + itemsArray[i] + " ";
                            }
                        }
                        textView.setText(str);
                    }
                });
                dialog.setNegativeButton("닫기", null);
                dialog.show();
            }
        });
    }
}
