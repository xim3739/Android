package com.example.dialogitems;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btClick;
    private String selectValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btClick = findViewById(R.id.btClick);

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] itmesArray = new String[]{"누가", "오레오", "파이"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("골라볼래?");
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setItems(itmesArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btClick.setText(itmesArray[which]);
                        textView.setText(itmesArray[which]);
                    }
                });

                dialog.setPositiveButton("닫기", null);
                dialog.show();
            }
        });


    }
}
