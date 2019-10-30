package com.example.nugaandoreo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btTextShow, btHomPage;
    private RadioButton btNuga, btOreo;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("좀 그럴듯한 프로그램");

        editText = findViewById(R.id.editText);
        btTextShow = findViewById(R.id.btTextShow);
        btHomPage = findViewById(R.id.btHomPage);
        btNuga = findViewById(R.id.btNuga);
        btOreo = findViewById(R.id.btOreo);
        image = findViewById(R.id.image);

        btTextShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastDisplay(editText.getText().toString().trim());
            }
        });

        btHomPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(editText.getText().toString()));
                startActivity(intent);
            }
        });

        btNuga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.mipmap.ic_launcher);
            }
        });
        btOreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.ic_launcher_foreground);
            }
        });

    }

    public void toastDisplay(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
