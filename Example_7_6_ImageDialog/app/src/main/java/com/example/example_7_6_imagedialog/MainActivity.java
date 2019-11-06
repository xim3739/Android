package com.example.example_7_6_imagedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton btDog, btCat, btRabbit;
    private Button btShowImage;
    private ImageView imageView;
    private View dialogView;
    private String result = null;
    private int imageSRC = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btDog = findViewById(R.id.btDog);
        btCat = findViewById(R.id.btCat);
        btRabbit = findViewById(R.id.btRabbit);
        btShowImage = findViewById(R.id.btShowImage);

        btDog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imageSRC = R.drawable.dog;
                }
            }
        });
        btCat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imageSRC = R.drawable.cat;
                }
            }
        });
        btRabbit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imageSRC = R.drawable.rabbit;
                }
            }
        });

        btShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageSRC == 0) {
                    Toast.makeText(MainActivity.this, "사진을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                }
                else {


                    dialogView = View.inflate(MainActivity.this, R.layout.image_dialog_activity, null);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    getSelectedRadioButton(imageSRC);
                    dialog.setTitle(result);

                    imageView = dialogView.findViewById(R.id.imageView);
                    imageView.setImageResource(imageSRC);
                    dialog.setView(dialogView);
                    dialog.setNegativeButton("닫기", null);
                    dialog.show();
                }
            }
        });
    }

    public void getSelectedRadioButton(int imageSRC) {

        if(imageSRC == R.drawable.dog) {
            result = "강아지";
        } else if (imageSRC == R.drawable.cat) {
            result = "고양이";
        } else if (imageSRC == R.drawable.rabbit){
            result = "토끼";
        } else {
            return;
        }
    }
}
