package com.example.navigationmenutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private DrawerLayout mainDrawerLayout;
    private Button btClick, btClose;
    private LinearLayout navigateDrawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);
        navigateDrawerMenu = findViewById(R.id.navigateDrawerMenu);

        btClick = findViewById(R.id.btClick);
        btClose = findViewById(R.id.btClose);

        btClick.setOnClickListener(this);
        btClose.setOnClickListener(this);

        navigateDrawerMenu.setOnTouchListener(this);
        mainDrawerLayout.setDrawerListener(drawerListener);

    }

    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener(){

        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            Toast.makeText(MainActivity.this, "슬라이드 액션", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
            Toast.makeText(MainActivity.this, "메뉴 닫기 액션", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btClick :
                mainDrawerLayout.openDrawer(navigateDrawerMenu);
                break;
            case R.id.btClose :
                mainDrawerLayout.closeDrawer(navigateDrawerMenu);
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }
}
