package com.example.mymp3player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> sdCardList = new ArrayList<>();
    private ArrayList<MainData> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button btLike, btMoveToSdCard, btPlay, btStop, btMoveToLikeList;

    private LinearLayoutManager linearLayoutManager;
    private MyAdapter myAdapter;

    public static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        getSdCardList();

        recyclerView = findViewById(R.id.recyclerView);
        btLike = findViewById(R.id.btLike);
        btMoveToSdCard = findViewById(R.id.btMoveToSdCard);
        btPlay = findViewById(R.id.btPlay);
        btStop = findViewById(R.id.btStop);
        btMoveToLikeList = findViewById(R.id.btMoveToLikeList);

        linearLayoutManager = new LinearLayoutManager(this);

        MyAdapter adapter = new MyAdapter(list, android.R.layout.simple_list_item_single_choice);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getSdCardList() {
        File[] fileList = new File(MP3_PATH).listFiles();

        for(File file : fileList) {
            String fileName = file.getName();
            String extendsString = fileName.substring(fileName.length() - 3);

            if(extendsString.equals("mp3")) {
                list.add(new MainData(fileName));
            }
        }
    }
}
