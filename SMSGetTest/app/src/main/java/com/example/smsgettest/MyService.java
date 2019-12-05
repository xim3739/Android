package com.example.smsgettest;

import android.app.Service;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private IntentFilter intentFilter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
