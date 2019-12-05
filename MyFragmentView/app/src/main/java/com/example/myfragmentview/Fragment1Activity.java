package com.example.myfragmentview;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1Activity extends Fragment implements View.OnClickListener {

    private static final String TAG = "Fragment1";

    public View view;
    public Button f1BtClick;

    Bundle userID;

    public Fragment1Activity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment1, container, false);
        f1BtClick = view.findViewById(R.id.f1BtClick);

        userID = getArguments();

        if(userID != null) {

            Log.d("TAG", userID.getString("name"));

        } else {

            Log.d("TAG", "dddddd");

        }

        f1BtClick.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f1BtClick :
                break;
        }
    }
}
