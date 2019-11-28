package com.example.myservicemusictest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.myservicemusictest.MusicService.SERVICE_TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btMusicStart, btMusicStop;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMusicStart = findViewById(R.id.btMusicStart);
        btMusicStop = findViewById(R.id.btMusicStop);

        intent = new Intent(MainActivity.this, MusicService.class);

        btMusicStart.setOnClickListener(this);
        btMusicStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btMusicStart :
                startService(intent);
                Log.d(SERVICE_TAG, "Main MusicStart & Service Start");
                break;
            case R.id.btMusicStop:
                stopService(intent);
                Log.d(SERVICE_TAG, "Main MusicStop & Service Stop");
                break;
        }
    }
}
