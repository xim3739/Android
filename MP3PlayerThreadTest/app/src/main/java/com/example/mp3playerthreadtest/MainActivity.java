package com.example.mp3playerthreadtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.mp3playerthreadtest.ListFragment1Activity.selectedMP3;
import static com.example.mp3playerthreadtest.ListFragment2Activity.likeList;
import static com.example.mp3playerthreadtest.MyDataBaseHelper.DB_NAME;
import static com.example.mp3playerthreadtest.MyDataBaseHelper.TBL_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar seekBar;
    private TextView textViewPlayingState, textViewPlayingMusicName;
    private Button btPlay, btPause, btLike, btMoveToLike, btMoveToSdCard;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentActivity;

    private SQLiteDatabase sqLiteDatabase;
    private MyDataBaseHelper myDataBaseHelper;

    public static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataBaseHelper = new MyDataBaseHelper(this);


        seekBar = findViewById(R.id.seekBar);
        textViewPlayingState = findViewById(R.id.textViewPlayingState);
        textViewPlayingMusicName = findViewById(R.id.textViewPlayingMusicName);
        btPlay = findViewById(R.id.btPlay);
        btPause = findViewById(R.id.btPause);
        btLike = findViewById(R.id.btLike);
        btMoveToLike = findViewById(R.id.btMoveToLike);
        btMoveToSdCard = findViewById(R.id.btMoveToSdCard);

        btMoveToLike.setOnClickListener(this);
        btMoveToSdCard.setOnClickListener(this);
        btLike.setOnClickListener(this);
        btMoveToSdCard.callOnClick();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btPlay.setOnClickListener(this);
        btPause.setOnClickListener(this);

        buttonSetting(true, false, 0);

    }

    private void buttonSetting(boolean b, boolean b1, int progress) {
        btPlay.setEnabled(b);
        btPause.setEnabled(b1);
        seekBar.setProgress(progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btPlay :
                try {
                    mediaPlayer.setDataSource(MP3_PATH+selectedMP3);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    buttonSetting(false, true, 0);
                    textViewPlayingMusicName.setText("실행중인 음악 : " + selectedMP3);

                    Thread thread = new Thread(){

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {

                            if(mediaPlayer == null) {
                                return;
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textViewPlayingState.setText(selectedMP3 + "재생 시간 : " + mediaPlayer.getDuration());
                                    seekBar.setMax(mediaPlayer.getDuration());
                                }
                            });

                            while(mediaPlayer.isPlaying()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                        textViewPlayingState.setText(selectedMP3 + "진행 시간 " + simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                                    }
                                });

                                SystemClock.sleep(100);
                            }

                        }
                    };

                    thread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btPause :

                mediaPlayer.stop();
                mediaPlayer.reset();
                buttonSetting(true,false,0);
                textViewPlayingMusicName.setText("실행 중인 음악 : ");
                textViewPlayingState.setText("진행 시간 : ");
                break;

            case R.id.btMoveToSdCard :

                btMoveToSdCard.setVisibility(View.GONE);
                btMoveToLike.setVisibility(View.VISIBLE);

                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentActivity = new ListFragment1Activity();
                fragmentTransaction.replace(R.id.frameLayout, fragmentActivity);
                fragmentTransaction.commit();

                break;

            case R.id.btMoveToLike :

                btMoveToSdCard.setVisibility(View.VISIBLE);
                btMoveToLike.setVisibility(View.GONE);

                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentActivity = new ListFragment2Activity();
                fragmentTransaction.replace(R.id.frameLayout, fragmentActivity);
                fragmentTransaction.commit();

                sqLiteDatabase = myDataBaseHelper.getReadableDatabase();
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TBL_NAME + ";" , null);

                String name = "";
                String singer = "";
                String genre = "";

                likeList.removeAll(likeList);

                while(cursor.moveToNext()) {
                    name = cursor.getString(0);
                    singer = cursor.getString(1);
                    genre = cursor.getString(2);

                    likeList.add(new MyData(name, singer, genre));
                }

                cursor.close();
                sqLiteDatabase.close();

                break;

            case R.id.btLike :

                View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);

                final EditText editTextSingName = dialogView.findViewById(R.id.editTextSingName);
                final EditText editTextSinger = dialogView.findViewById(R.id.editTextSinger);
                final EditText editTextSingGenre = dialogView.findViewById(R.id.editTextSingGenre);

                editTextSingName.setText(selectedMP3);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("찜하기");
                alertDialog.setView(dialogView);
                alertDialog.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try{
                            String name = editTextSingName.getText().toString().trim();
                            String singer = editTextSinger.getText().toString().trim();
                            String genre = editTextSingGenre.getText().toString().trim();

                            sqLiteDatabase = myDataBaseHelper.getWritableDatabase();
                            sqLiteDatabase.execSQL("INSERT INTO "+TBL_NAME+ " VALUES ('" + name + "','" + singer + "','" + genre + "');");
                            sqLiteDatabase.close();
                            Toast.makeText(MainActivity.this, "등록 돼었습니다.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "중복하여 등록 할 수 없습니다." , Toast.LENGTH_LONG).show();
                        }

                    }
                });

                alertDialog.setNegativeButton("취소", null);

                alertDialog.show();
                break;
        }
    }

}
