package com.example.examplemaptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import static com.example.examplemaptest.R.id.gone;
import static com.example.examplemaptest.R.id.map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private GroundOverlayOptions groundOverlayOptionsMark;

    ArrayList<GroundOverlayOptions> cctvList = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"위성지도");
        menu.add(0,2,0,"일반지도");
        menu.add(0,3,0,"바로 전 CCTV 지우기");
        menu.add(0,4,0,"모든 CCTV 지우기");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:

                gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;

            case 2:

                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;

            case 3:

                gMap.clear();
                cctvList.remove(cctvList.size()-1);

                for(int i = 0; i < cctvList.size(); i++) {
                    gMap.addGroundOverlay(cctvList.get(i));
                }

                return true;

            case 4:

                gMap.clear();
                cctvList.removeAll(cctvList);
                return true;

        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((SupportMapFragment)getSupportFragmentManager().findFragmentById(map)).getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        gMap = googleMap;

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                groundOverlayOptionsMark = new GroundOverlayOptions().image
                        (BitmapDescriptorFactory.fromResource(R.drawable.aaa)).
                        position(latLng, 100f, 100f);

                cctvList.add(groundOverlayOptionsMark);

                for(int i = 0; i < cctvList.size(); i++) {

                    gMap.addGroundOverlay(cctvList.get(i));

                }

            }

        });

    }
}
