package com.example.navigateviewexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private DrawerLayout mainDrawerLayout;
    public static Button btMoveToVote;
    private LinearLayout navigateLayout;

    private ImageView[] imageViewsList = new ImageView[9];
    private Integer[] imageIDList = new Integer[]{R.id.image01, R.id.image02, R.id.image03, R.id.image04, R.id.image05,
            R.id.image06, R.id.image07, R.id.image08, R.id.image09};
    private String[] imageNameList = {"1번 그림", "2번 그림", "3번 그림", "4번 그림", "5번 그림",
            "6번 그림", "7번 그림", "8번 그림", "9번 그림"};
    private int count[] = new int[9];

    final static int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);
        navigateLayout = findViewById(R.id.navigateLayout);

        btMoveToVote = findViewById(R.id.btMoveToVote);

        navigateLayout.setOnTouchListener(this);
        mainDrawerLayout.setDrawerListener(DrawActionDefine.drawerMethod());
        btMoveToVote.setOnClickListener(this);

        for(int i = 0; i < imageNameList.length; i++) {
            imageViewsList[i] = findViewById(imageIDList[i]);
            imageViewsList[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btMoveToVote :
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("imageNameList",imageNameList);
                intent.putExtra("count", count);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.image01 :
                imageVoteChecked(0);
                break;
            case R.id.image02 :
                imageVoteChecked(1);
                break;
            case R.id.image03 :
                imageVoteChecked(2);
                break;
            case R.id.image04 :
                imageVoteChecked(3);
                break;
            case R.id.image05 :
                imageVoteChecked(4);
                break;
            case R.id.image06 :
                imageVoteChecked(5);
                break;
            case R.id.image07 :
                imageVoteChecked(6);
                break;
            case R.id.image08 :
                imageVoteChecked(7);
                break;
            case R.id.image09 :
                imageVoteChecked(8);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String msg = data.getStringExtra("message");
            toastDisplay(msg);
            for(int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
        }
    }

    private void imageVoteChecked(int num) {
        count[num] = (count[num] == 5) ? 5 : ++count[num];
        if(count[num] == 5) {
            toastDisplay("투표 가능 횟수를 초과했습니다.");
        } else {
            toastDisplay(imageNameList[num] + "번째 그림에 투표가 반영 되었습니다.");
        }
    }

    private void toastDisplay(String s) {

        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }


}
