package com.example.userinterfacedialog;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewName,textViewEmail;
    private Button btClick;
    private View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        btClick = findViewById(R.id.btClick);

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog_activity, null);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("사용자 정보 입력");
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setView(dialogView);

                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText userName = dialogView.findViewById(R.id.editTextName);
                        EditText userEmail = dialogView.findViewById(R.id.editTextEmail);

                        textViewName.setText(userName.getText().toString().trim());
                        textViewEmail.setText(userEmail.getText().toString().trim());
                    }
                });

                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toastView = View.inflate(MainActivity.this, R.layout.toast_activity, null);
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(toastView);
                        toast.show();
                    }
                });

                dialog.show();
            }
        });
    }
}
