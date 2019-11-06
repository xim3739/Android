package com.example.loginproject;

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

    private Button btSignUp, btSignIn;
    private TextView textView;
    private View signUpView, loginView;
    private EditText signInEditTextId, signInEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSignIn = findViewById(R.id.btSignIn);
        btSignUp = findViewById(R.id.btSignUp);
        textView = findViewById(R.id.textView);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpView = View.inflate(MainActivity.this, R.layout.sign_up, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("회원가입");
                dialog.setView(signUpView);

                dialog.setPositiveButton("가입", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "가입 성공!!!!", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setNegativeButton("닫기", null);
                dialog.show();
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginView = View.inflate(MainActivity.this, R.layout.login, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("로그인");
                dialog.setView(loginView);
                signInEditTextId = loginView.findViewById(R.id.signInEditTextId);
                signInEditTextPassword = loginView.findViewById(R.id.signInEditTextPassword);

                dialog.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if((signInEditTextId.getText().toString().equals("xim")) && (signInEditTextPassword.getText().toString().equals("1234"))) {
                            Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_LONG).show();
                            textView.setText("로그인 했네 축하축하");
                        } else {
                            textView.setText("로그인 실패함 ㅎㅎㅎㅎ");
                        }
                    }
                });
                dialog.setNegativeButton("닫기", null);
                dialog.show();
            }
        });
    }
}
