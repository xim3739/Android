package com.example.alertdialogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btDialog = findViewById(R.id.btDialog);

        btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());

                dialog.setTitle("흐어아");
                dialog.setMessage("흐어흐어흐어아?");
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setPositiveButton("흐어?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toastDisplay("흐어아어");
                    }
                });

                dialog.setNegativeButton("흐아?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toastDisplay("흐어어아앙");
                    }
                });

                dialog.show();
            }
        });
    }

    public void toastDisplay(String msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

}
