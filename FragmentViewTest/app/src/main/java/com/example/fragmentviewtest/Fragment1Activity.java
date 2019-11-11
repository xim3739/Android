package com.example.fragmentviewtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1Activity extends Fragment implements View.OnClickListener {

    //xml에 있는 버튼객체를 담기 위한 변수 생성, fragment를 객체화 시켜서 담기위한 변수 생성
    public Button f1BtClick;
    public View view;

    //생성자
    public Fragment1Activity() {

    }

    //mainActivity에 있는 onCreate와 같은 역할이다.
    //Activity 클래스는 셩명주기로 인해 onCreate 부분을 콜백으로 실행한다.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //fragment1 xml파일을 infalte를 이용해서 객채화 시킬수있게 한다.
        view = inflater.inflate(R.layout.fragment1, container, false);
        f1BtClick= view.findViewById(R.id.f1BtClick);

        f1BtClick.setOnClickListener(this);

        //view 를 리턴한다.
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f1BtClick :
                toastMessage("반가워요");
                break;
        }
    }

    private void toastMessage(String msg) {

        Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();

    }

}
