package com.example.implcitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btDial, btHomepage, btGoogle, btGoogleSearch, btMessageSend, btCamera, btClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate");

        btDial = findViewById(R.id.btDial);
        btHomepage = findViewById(R.id.btHomepage);
        btGoogle = findViewById(R.id.btGoogle);
        btGoogleSearch = findViewById(R.id.btGoogleSearch);
        btMessageSend = findViewById(R.id.btMessageSend);
        btCamera = findViewById(R.id.btCamera);
        btClose = findViewById(R.id.btClose);

        btDial.setOnClickListener(this);
        btHomepage.setOnClickListener(this);
        btGoogle.setOnClickListener(this);
        btGoogleSearch.setOnClickListener(this);
        btMessageSend.setOnClickListener(this);
        btCamera.setOnClickListener(this);
        btClose.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart");
    }

    @Override
    protected void onStart() {
        Log.d("MainActivity", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity", "oPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        Intent intent = null;

        switch (view.getId()) {
            case R.id.btDial :
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01021106711"));
                startActivity(intent);
                break;
            case R.id.btHomepage :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(intent);
                break;
            case R.id.btGoogle :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q="+37.562128 +","+127.035181));
                startActivity(intent);
                break;
            case R.id.btGoogleSearch :
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "라흐마니노프");
                startActivity(intent);
                break;
            case R.id.btMessageSend :
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "오점뭐?");
                intent.setData(Uri.parse("smsto:"+ Uri.encode("01021106711")));
                startActivity(intent);
                break;
            case R.id.btCamera :
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.btClose :
                finish();
                break;


        }

    }
}
