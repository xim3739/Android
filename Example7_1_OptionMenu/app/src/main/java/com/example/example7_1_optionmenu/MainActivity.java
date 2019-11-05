package com.example.example7_1_optionmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ImageView image1, image2, image3;
    private FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        frame = findViewById(R.id.frame);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.rotate :
                frame.setRotation(frame.getRotation() + Float.parseFloat(editText.getText().toString()));
                break;
            case R.id.item1 :
                frame.setRotation(0);
                image1.setImageResource(R.drawable.image2);
                break;
            case R.id.item2 :
                frame.setRotation(0);
                image1.setImageResource(R.drawable.image3);
                break;
            case R.id.item3 :
                frame.setRotation(0);
                image1.setImageResource(R.drawable.image4);
                break;
        }

        return true;
    }
}
