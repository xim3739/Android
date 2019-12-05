package com.example.smsgettest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //퍼미션을 구분하기 위한 상수
    static final int SMS_READ_PERMISSION = 1;

    private ListView listView;
    public ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter adapter;
    public static Context context;

    private final String TAG = "TAG";

    private String strMessage;

    private IntentFilter intentFilter= new IntentFilter();


    //sms 를 접근 할 수 있는 권한을 받는 함수(콜백)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){

            case SMS_READ_PERMISSION:

                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(getApplicationContext(), "SMS 권한 승인함", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(), "SMS 권한 거부함", Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 체크 후 없으면 권한을 승인할지 거부할지 정하는 함수를 콜 함 (onRequestPermissionsResult)
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){

            Toast.makeText(getApplicationContext(), "SMS 수신권한 있음", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), "SMS 수신권한 없음", Toast.LENGTH_SHORT).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)){

                Toast.makeText(getApplicationContext(), "SMS 권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_SMS}, SMS_READ_PERMISSION);

            }else{

                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_SMS}, SMS_READ_PERMISSION);

            }

        }

        listView = findViewById(R.id.listView);
        Button btClick = findViewById(R.id.btClick);
        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFilter.addAction(strMessage);
            }
        });

    }

    //시스템이 잠시 멈추어도 새로운 리스트롤 적용 시켜야 하므로 onResume 에 리스트를 세팅한다.
    @Override
    protected void onResume() {
        super.onResume();







        registerReceiver(broadcastReceiver, intentFilter);

        context = getApplicationContext();
        list = getSMS(context);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, list);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

    }

    @Override
    protected void onPause() {

        super.onPause();

        unregisterReceiver(broadcastReceiver);

    }

    //문자 메세지를 가져오는 함수
    public static ArrayList<String> getSMS(Context context) {

        ArrayList<String> list = new ArrayList<>();
        //이 곳이 문자들이 모여있는 장소이다. 이미 정해져있다. 여라가지 있다.
        //content://sms/ ~ 물결표 자리에 여라가지 온다. 검색 고고
        Uri uri = Uri.parse("content://sms/");
        //DB에 있는 자료를 가져오는 것이기 때문에 Cursor를 사용한다.
        Cursor cursor = context.getContentResolver().query(uri,null, null, null, null);

        while(cursor != null && cursor.moveToNext()) {
            //컬럼 인덱스를 가져온다. 컬럼네임으로 !
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));

            if(body.contains("신한") && body.contains("승인")) {

                list.add("Number : " + address + " .Message : " + body);

            }

//            list.add("NUMBER : " + address + ", MESSAGE : " + body);

        }

        //커서가 비어있으면 닫는다.
        if(cursor == null) {

            cursor.close();

        }

        return list;

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.

            String action = intent.getAction();

            if(action.equals(SMS_READ_PERMISSION)) {
                Bundle bundle = intent.getExtras();

                strMessage = "";

                if(bundle != null) {
                    Object[] smsExtra = (Object[]) bundle.get("pdus");

                    for(int i = 0; i < smsExtra.length; i++) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) smsExtra[i]);

                        String strBody = smsMessage.getMessageBody().toString();
                        String strSrc = smsMessage.getOriginatingAddress();

                        strMessage += "Sms From " + strSrc + " : " + strBody;

                        Log.d(TAG, strMessage);
                    }
                }
            }

        }
    };

}
