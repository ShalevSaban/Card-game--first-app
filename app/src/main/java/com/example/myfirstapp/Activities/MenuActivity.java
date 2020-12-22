package com.example.myfirstapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfirstapp.Objects.MySharedPref;
import com.example.myfirstapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MenuActivity extends AppCompatActivity  implements LocationListener {

    Button start, topTen;
    EditText player1;
    EditText player2;
    FusedLocationProviderClient mFusedLocationClient;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        player1 = (EditText) findViewById(R.id.menu_LBL_playerOne);
        player2 = (EditText) findViewById(R.id.menu_LBL_playerTwo);
        topTen = findViewById(R.id.menu_BTN_TopTen);
        start = findViewById(R.id.menu_BTN_start);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MySharedPref.init(this);
        player1.setTextColor(-1);
        player2.setTextColor(-2);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MySharedPref.getInstance().addPlayerName(player1.getText().toString());
                MySharedPref.getInstance().addPlayerName(player2.getText().toString());
                // setLocation(mFusedLocationClient);
                checkLocationFirst();
                locationEnabled();
                getLocation(locationManager);
                openStart();
            }
        });
    topTen.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openTopTen();
        }
    });
    }

    private void checkLocationFirst(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }



    private void openTopTen() {
        Intent myIntent = new Intent(this, TopTenActivity.class);
        startActivity(myIntent);
        finish();
    }


    private void openStart() {
        Intent myIntent = new Intent(this, MainActivity.class);
        String[] players = {player1.getText().toString(), player2.getText().toString()};
        myIntent.putExtra("players", players);
        this.startActivity(myIntent);
        finish();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            MySharedPref.getInstance().updateLocation(addresses.get(0));
        } catch (Exception e) {
        }
    }


    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(MenuActivity.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    void getLocation(LocationManager locationManager) {
        try {
            locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }





}