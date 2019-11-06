package com.example.toastmovetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btNomarl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btNomarl = findViewById(R.id.btNomarl);

        btNomarl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast myToast = Toast.makeText(getApplicationContext(), "하이이이이이", Toast.LENGTH_LONG);

                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                int xOffset = (int) (Math.random() * display.getWidth());
                int yOffset = (int) (Math.random() * display.getHeight());

                myToast.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);
                myToast.show();
            }
        });

    }
}
