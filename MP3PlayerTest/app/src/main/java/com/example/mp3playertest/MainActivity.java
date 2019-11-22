package com.example.mp3playertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listViewMP3;
    private Button btPlay, btStop;
    private ProgressBar progressBar;
    private TextView textView;

    private MediaPlayer mediaPlayer;
    private String selectMP3;

    private ArrayList<String> mp3List = new ArrayList<>();
    public static final String MP3_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        File[] listFile = new File(MP3_PATH).listFiles();

        for(File file : listFile) {

            String fileName = file.getName();
            String extendName = fileName.substring(fileName.length() - 3);

            if(extendName.equals("mp3") || extendName.equals("mp4")) {
                mp3List.add(fileName);
            }

        }

        listViewMP3 = findViewById(R.id.listViewMP3);
        btPlay = findViewById(R.id.btPlay);
        btStop = findViewById(R.id.btStop);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);

        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(arrayAdapter);
        listViewMP3.setItemChecked(0, true);

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectMP3 = mp3List.get(position);
            }
        });

        btPlay.setOnClickListener(this);
        btStop.setOnClickListener(this);

        settingInit(true, false, View.INVISIBLE);
    }

    private void settingInit(boolean b, boolean b1, int invisible) {
        btPlay.setClickable(b);
        btStop.setClickable(b1);
        progressBar.setVisibility(invisible);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btPlay :
                mediaPlayer = new MediaPlayer();
                try {

                    mediaPlayer.setDataSource(MP3_PATH + selectMP3);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    settingInit(false, true, View.VISIBLE);

                    textView.setText("실행중인 음악 : " + selectMP3);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btStop :

                mediaPlayer.stop();
                mediaPlayer.reset();
                settingInit(true, false, View.INVISIBLE);
                textView.setText("음악을 선택 하세요");

                break;
        }
    }
}
