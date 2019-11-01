package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button[] button = new Button[3];
    Integer[] valueId = {R.id.btDisplay1, R.id.btDisplay2, R.id.btDisplay3};

    private LinearLayout layout1, layout2, layout3;

    private Button [] button1 = new Button[3];
    Integer[] getValueId = {R.id.btToastButton1, R.id.btToastButton2, R.id.btToastButton3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);

        for(int i = 0; i < valueId.length; i++) {
            final int index = i;
            button[index] = findViewById(valueId[index]);
            button[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (index) {
                        case 0 :
                            setLayoutVisiable(View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                            break;
                        case 1 :
                            setLayoutVisiable(View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                            break;
                        case 2 :
                            setLayoutVisiable(View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                            break;
                        default :
                            break;

                    }
                }
            });

        }

        for(int i = 0; i < getValueId.length; i++) {
            final int index1 = i;
            button1[index1] = findViewById(getValueId[index1]);
            button1[index1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (index1) {
                        case 0:
                            toastDisplay(1);
                            break;
                        case 1 :
                            toastDisplay(3);
                            break;
                        case 2:
                            toastDisplay(2);
                            break;
                        default:

                            break;
                    }
                }
            });
        }

    }
    public void setLayoutVisiable(int visiable1, int visiable2, int visiable3) {
        layout1.setVisibility(visiable1);
        layout3.setVisibility(visiable2);
        layout2.setVisibility(visiable3);
    }

    public void toastDisplay(int index) {
        Toast.makeText(getApplicationContext(), index + "번째 화면", Toast.LENGTH_LONG).show();
    }
}
