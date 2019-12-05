package com.example.messagetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int SMS_RECEIVE_PERMISSON = 1;

    private TextView textViewMessage, textViewContents, textViewDate;
    private Button btClick;

    BroadcastReceiver broadcastReceiver = new SmsReceiver();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case SMS_RECEIVE_PERMISSON :
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "승인", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "거부", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        registerReceiver(broadcastReceiver, intentFilter);

        int permissonCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SMS 권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "SMS 권한 없음", Toast.LENGTH_LONG).show();
        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
            Toast.makeText(getApplicationContext(), "SMS 권한 요구", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, SMS_RECEIVE_PERMISSON);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, SMS_RECEIVE_PERMISSON);
        }



        textViewMessage = findViewById(R.id.textViewMessage);
        textViewContents = findViewById(R.id.textViewContents);
        textViewDate = findViewById(R.id.textViewDate);
        btClick = findViewById(R.id.btClick);



        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent passedIntent = getIntent();
                processCommand(passedIntent);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent passedIntent) {

        String message = passedIntent.getStringExtra("sendMessage");
        String contents = passedIntent.getStringExtra("contents");
        String date = passedIntent.getStringExtra("receiveDate");

        textViewMessage.setText(message);
        textViewContents.setText(contents);
        textViewDate.setText(date);
    }
}
