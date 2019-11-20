package com.example.instargrammenutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_1 :
                        setChangeFragment(0);
                        break;
                    case R.id.action_2 :
                        setChangeFragment(1);
                        break;
                    case R.id.action_3 :
                        setChangeFragment(2);
                        break;
                    case R.id.action_4 :
                        setChangeFragment(3);
                        break;
                    case R.id.action_5 :
                        setChangeFragment(4);
                        break;
                }
                return false;
            }
        });

    }

    private void setChangeFragment(int position) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (position) {
            case 0:
                fragmentTransaction.replace(R.id.frameLayout, fragment1);
                break;
            case 1:
                fragmentTransaction.replace(R.id.frameLayout, fragment2);
                break;
            case 2:
                fragmentTransaction.replace(R.id.frameLayout, fragment3);
                break;
            case 3:
                fragmentTransaction.replace(R.id.frameLayout, fragment4);
                break;
            case 4:
                fragmentTransaction.replace(R.id.frameLayout, fragment5);
                break;
        }
        fragmentTransaction.commit();
    }
}
