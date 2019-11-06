package com.example.example_mulichoiceitems;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btClick;
    private ArrayList<String> saveArray = new ArrayList<String>();

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
                saveArray.removeAll(saveArray);
                final String[] itmesArray = new String[] {"누가 크래커", "오레오 스낵", "호두파이"};
                final boolean[] checkArray = new boolean[] {false, false, false};

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("골라볼래?");
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setMultiChoiceItems(itmesArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked) {
                            saveArray.add(itmesArray[which]);
                        }
                    }
                });
                dialog.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i < saveArray.size(); i++) {

                            textView.setText(textView.getText().toString().trim() + " " + saveArray.get(i));

                        }
                    }
                });

                dialog.show();
            }
        });
    }
}
