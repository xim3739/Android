package com.example.smsreceivertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    static final int SMS_READ_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "TEST", Toast.LENGTH_LONG).show();

        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){

            //Toast.makeText(getApplicationContext(), "SMS 수신권한 있음", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), "SMS 수신권한 없음", Toast.LENGTH_SHORT).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)){

                Toast.makeText(getApplicationContext(), "SMS 권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_SMS}, SMS_READ_PERMISSION);

            }else{

                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_SMS}, SMS_READ_PERMISSION);

            }

        }

        TextView textView = findViewById(R.id.textView);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){

            case SMS_READ_PERMISSION:

                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(getApplicationContext(), "SMS 권한 승인함", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(), "SMS 권한 거부함", Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewMessageEvent(NewMessageEvent event) {
        Log.d("MainActivity", event.getMessage());
        Toast.makeText(this, "Message from "+event.getSender()+": "+event.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
