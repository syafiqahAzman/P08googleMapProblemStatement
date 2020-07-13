package com.myapplicationdev.android.p08googlemapproblemstatement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);



        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                LatLng spore = new LatLng(1.352083,103.819839);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(spore,15));
                final LatLng north = new LatLng(1.445575, 103.785163);
                Marker rp = map.addMarker(new MarkerOptions()
                .position(north)
                .title("HQ - North")
                .snippet("9 Woodlands Ave 9, Singapore 738964 \nOperating hours: 10am-5pm\nTel:65433456\n")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.star)));

                final LatLng central = new LatLng(1.436003, 103.785366);
                Marker cp = map.addMarker(new MarkerOptions()
                .position(central)
                .title("Causeway Point")
                .snippet("1 Woodlands Square, Singapore 738099\nOperating hours: 11am-8pm\nTel:67788652\n")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                final LatLng east = new LatLng(1.437634, 103.804110);
                Marker of = map.addMarker(new MarkerOptions()
                .position(east)
                .title("Office")
                .snippet("693 Woodlands Avenue 6, Singapore 730693\nOperating hours: 9am-5pm\nTel:66776677\n")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("Gmap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(north, 15));
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(central, 15));
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(east, 15));
                    }
                });
            }
        });
    }
}
