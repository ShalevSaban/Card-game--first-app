package com.example.myfirstapp.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstapp.Objects.MySharedPref;
import com.example.myfirstapp.Objects.Person;
import com.example.myfirstapp.Objects.TopTen;
import com.example.myfirstapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
//            LatLng newLocation = new LatLng(32.337257682755364, 34.8605934582391);
//            googleMap.addMarker(new MarkerOptions().position(newLocation).title("Shalev"));
            markLocations(googleMap);
        }
    };

    private void markLocations(GoogleMap googleMap) {
        TopTen list=MySharedPref.getInstance().getTopTen();
        Log.d("DATA","MarkLocation list"+list);
        int num=list.getNumOfPlayers();
        for(int i=num-1;i>num-11;i--) {
            if (list.getPlayer(i) != null) {
                Log.d("DATA","MarkLocation get playerloc"+list.getPlayer(i).getLocation());
                Person person= list.getPlayer(i);
                if (person.getLocation()!=null) {
                    LatLng newLocation = new LatLng(person.getLocation().getLatitude(), person.getLocation().getLongitude());
                    Log.d("DATA",""+newLocation.latitude+"  "+newLocation.longitude);
                    googleMap.addMarker(new MarkerOptions().position(newLocation).title(person.getName()));
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}