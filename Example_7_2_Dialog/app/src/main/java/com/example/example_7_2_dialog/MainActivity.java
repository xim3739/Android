package com.example.example_7_2_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mainEditTextName, mainEditTextEmail;
    private Button btClick;
    private View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainEditTextName = findViewById(R.id.mainEditTextName);
        mainEditTextEmail = findViewById(R.id.mainEditTextEmail);
        btClick = findViewById(R.id.btClick);

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog_activity, null);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setTitle("사용자 정보 입력");
                dialog.setView(dialogView);
            }
        });
    }
}
