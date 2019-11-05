package com.example.example6_3_tabhost;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    private ImageView image1, image2, image3, image4;
    private TabHost.TabSpec tabSpec1, tabSpec2, tabSpec3, tabSpec4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);

        image1.setImageResource(R.drawable.admin);
        image2.setImageResource(R.drawable.image2);
        image3.setImageResource(R.drawable.image3);
        image4.setImageResource(R.drawable.image4);

        TabHost tabHost = getTabHost();

        tabSpec1 = tabHost.newTabSpec("image1").setIndicator("사진1");
        tabSpec1.setContent(R.id.image1);
        tabHost.addTab(tabSpec1);

        tabSpec2 = tabHost.newTabSpec("image2").setIndicator("사진2");
        tabSpec2.setContent(R.id.image2);
        tabHost.addTab(tabSpec2);

        tabSpec3 = tabHost.newTabSpec("image3").setIndicator("사진3");
        tabSpec3.setContent(R.id.image3);
        tabHost.addTab(tabSpec3);

        tabSpec4 = tabHost.newTabSpec("image4").setIndicator("사진4");
        tabSpec4.setContent(R.id.image4);
        tabHost.addTab(tabSpec4);


    }
}
