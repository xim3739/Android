package com.example.smsreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    //퍼미션을 위한 값
    static final int SMS_RECEIVE_PERMISSION = 1;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        //권한 설정 부분
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Already SMS Accept", Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(this, "No SMS Accept", Toast.LENGTH_LONG).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {

                Toast.makeText(this, "Need SMS Accept", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSION);

            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSION);

            }
        }

//        Intent passedIntent = getIntent();
//        processIntent(passedIntent);

    }

    @Override
    protected void onResume() {

        super.onResume();

        String date = "";
        String sender = "";
        String contents = "";

        Bundle bundle = getIntent().getExtras();

        if(bundle == null) {

            contents = "no received message";
            Log.d("check", "bundle null" + contents);

        } else {

            date = bundle.getString("date", "no date");
            sender = bundle.getString("sender", "no sender");
            contents = bundle.getString("contents", "no contents");
            Log.d("check", "bundle not null" + contents);

        }

        editText.setText(date + "\n" + sender + "\n" + contents + "\n");

        NotificationManagerCompat.from(this).cancel(0);

    }

    //권한 설정
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case SMS_RECEIVE_PERMISSION :

                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getApplicationContext(), "SMS Accept", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getApplicationContext(), "SMS not Accept", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

//        //인텐트가 오면 작업할 부분
//    private void processIntent(Intent passedIntent) {
//        if(passedIntent != null) {
//            string = passedIntent.getStringExtra("string");
//            editText.setText(string);
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.cancel(0);
//        }
//    }
//
//    //브로드캐스트 리시버가 앱이 죽어있어도 핸도폰의 바뀌는 내용을 인텐트로 전달한다.
//    //앱이 죽어있어도 인텐트가 전달되면 자동으로 인텐트를 실행하면서 앱을 실행 시킨다.
//    @Override
//    protected void onNewIntent(Intent intent) {
//        processIntent(intent);
//        super.onNewIntent(intent);
//    }
}
