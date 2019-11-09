package com.example.my_call_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btCall;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCall = findViewById(R.id.btCall);
        editText = findViewById(R.id.editText);

        btCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String callNumber = editText.getText().toString().trim();

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + callNumber));
        startActivity(intent);

        //메뉴 엑티비티를 만들면 이렇게 쓴다.
//        Intent intent1 = new Intent();
//        ComponentName name = new ComponentName("com.example.my_call_intent", "com.example.my_call_intent.MenuActivity");
//        intent1.setComponent(name);
//        startActivity(intent1);
    }
}
