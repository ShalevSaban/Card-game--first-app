////package com.example.myfirstapp.Objects;
////
////import android.Manifest;
////import android.annotation.SuppressLint;
////import android.content.pm.PackageManager;
////import android.location.Address;
////import android.location.Geocoder;
////import android.location.Location;
////
////import androidx.annotation.NonNull;
////import androidx.core.app.ActivityCompat;
////
////import com.example.myfirstapp.Activities.MenuActivity;
////import com.google.android.gms.tasks.OnCompleteListener;
////import com.google.android.gms.tasks.Task;
////
////import java.io.IOException;
////import java.util.List;
////import java.util.Locale;
////
//public class DDD {
//
//
//
//
//@SuppressLint("MissingPermission")
//    public Address getLocation(){
//        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
//            @Override
//            public void onComplete(@NonNull Task<Location> task) {
//                Location location = task.getResult();
//                if (location != null) {
//                    Geocoder geocoder = new Geocoder(MenuActivity.this, Locale.getDefault());
//                    try {
//                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
////
////
////        private void checkLocation(FusedLocationProviderClient mFusedLocationClient) {
////            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
////                    == PackageManager.PERMISSION_GRANTED) {
////
////            } else {
////                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
////            }
//        }
//
//
