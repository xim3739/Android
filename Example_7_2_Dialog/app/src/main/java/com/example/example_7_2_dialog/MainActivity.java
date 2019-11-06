package com.example.example_7_2_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mainEditTextName, mainEditTextEmail, dialogEditTextName, dialogEditTextEmail;
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

                dialogEditTextName = dialogView.findViewById(R.id.dialogEditTextName);
                dialogEditTextEmail = dialogView.findViewById(R.id.dialogEditTextEmail);

                dialogEditTextName.setText(mainEditTextName.getText().toString().trim());
                dialogEditTextEmail.setText(mainEditTextEmail.getText().toString().trim());

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setTitle("사용자 정보 입력");

                dialog.setView(dialogView);

                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainEditTextName.setText(dialogEditTextName.getText().toString().trim());
                        mainEditTextEmail.setText(dialogEditTextEmail.getText().toString().trim());
                    }
                });

                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       toastView = View.inflate(MainActivity.this, R.layout.toast_activity, null);
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(toastView);
                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                        int x = (int) (Math.random() * display.getWidth());
                        int y = (int) (Math.random() * display.getHeight());
                        toast.setGravity(Gravity.TOP | Gravity.LEFT, x, y);
                        toast.show();
                    }
                });

                dialog.show();
            }
        });
    }
}
