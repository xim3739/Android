package com.example.fragmentviewtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btMenu1, btMenu2, btMenu3, btMenu4;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMenu1 = findViewById(R.id.btMenu1);
        btMenu2 = findViewById(R.id.btMenu2);
        btMenu3 = findViewById(R.id.btMenu3);
        btMenu4 = findViewById(R.id.btMenu4);

        btMenu1.setOnClickListener(this);
        btMenu2.setOnClickListener(this);
        btMenu3.setOnClickListener(this);
        btMenu4.setOnClickListener(this);

        //초기 화면에 메뉴1번 창을 띄우기 위해서 사용
        btMenu1.callOnClick();

    }

    @Override
    public void onClick(View v) {
        //fragmentTransaction 을 만든다.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //frgmentActivity를 담을 수 있는 공간을 만든다.
        Fragment fragmentActivity = null;

        //버튼 값에 맞게 Activity를 저장한다.
        switch (v.getId()) {
            case R.id.btMenu1 :
                fragmentActivity = new Fragment1Activity();
                break;
            case R.id.btMenu2 :
                fragmentActivity = new Fragment2Activity();
                break;
            case R.id.btMenu3 :
                fragmentActivity = new Fragment3Activity();
                break;
            case R.id.btMenu4 :
                fragmentActivity = new Fragment4Activity();
                break;
        }

        //버튼값으로 가져온 fragment를 activity_main 에 있는 frameLayout 에 대체 시킨다.
        //frameLayout 이 현재 fragment를 표시하고 싶은 메인창이다.
        fragmentTransaction.replace(R.id.frameLayout, fragmentActivity);
        //대체 시킨것을 실행한다.
        fragmentTransaction.commit();
    }
}
