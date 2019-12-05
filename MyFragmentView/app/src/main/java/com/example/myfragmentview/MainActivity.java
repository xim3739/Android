package com.example.myfragmentview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Fragment2Activity.OnFragmentInteractionListener {

    private Bundle bundle;

    private Button btMenu1, btMenu2, btMenu3;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMenu1 = findViewById(R.id.btMenu1);
        btMenu2 = findViewById(R.id.btMenu2);
        btMenu3 = findViewById(R.id.btMenu3);

        btMenu1.setOnClickListener(this);
        btMenu2.setOnClickListener(this);
        btMenu3.setOnClickListener(this);

        btMenu2.callOnClick();

    }

    @Override
    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.btMenu1 :
                fragment = new Fragment1Activity();

                fragment.setArguments(bundle);

                break;
            case R.id.btMenu2 :
                fragment = new Fragment2Activity();
                break;
            case R.id.btMenu3 :
                fragment = new Fragment3Activity();
                break;
        }

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteration(Bundle bundle) {
        this.bundle = bundle;
    }
}
