package com.example.systemmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button btMenu;
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMenu = findViewById(R.id.btMenu);
        layout = findViewById(R.id.layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.itemRed :
                layout.setBackgroundColor(Color.RED);
                break;
            case R.id.itemGreen :
                layout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemBlue :
                layout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.subRotate :
                btMenu.setRotation(45f);
                break;
            case R.id.subSize :
                btMenu.setScaleX(2);
                break;
            default: break;
        }

        return true;
    }
}
