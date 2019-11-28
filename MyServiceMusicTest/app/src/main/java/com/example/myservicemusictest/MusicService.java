package com.example.myservicemusictest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service {

    public static final String SERVICE_TAG = "MusicService";
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Log.d(SERVICE_TAG, "Service onCreate()");
        Toast.makeText(getApplicationContext(), "서비스가 시작되었습니다.", Toast.LENGTH_SHORT).show();
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(SERVICE_TAG, "Service onStartCommand()");

        mediaPlayer = MediaPlayer.create(this, R.raw.song7);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {

        Log.d(SERVICE_TAG, "Service onDestroy()");

        mediaPlayer.stop();

        super.onDestroy();

    }

}