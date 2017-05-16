package com.sdu.tample;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.model.ModelHot;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;


public class MapHotActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    private static LatLng b9 = new LatLng(13.7154937, 100.5820363);
    private Marker mPerth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map2);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        final GoogleMap mMap = map;
        new ConnectAPI().getHotAll(MapHotActivity.this, mMap);
    }

    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(MapHotActivity.this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    public void addMarker(GoogleMap mMap, String string) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelHot>>() {
        }.getType();
        Collection<ModelHot> enums = gson.fromJson(string, collectionType);
        ArrayList<ModelHot> post = new ArrayList<ModelHot>(enums);

        for (ModelHot contentModel :
                post) {
            mPerth = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(contentModel.getInterestingLatitude()), Double.parseDouble(contentModel.getInterestingLongitude())))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title(contentModel.getInterestingName()));
            mPerth.setTag(contentModel.getId());

        }


        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
        mMap.setBuildingsEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(14.3532128, 100.5689599)).zoom(10).build(); // เอาไว้ Fix Location
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));//แบบมี Animation
//        googleMap.getUiSettings().setScrollGesturesEnabled(false);
//        ปิดไม่ให้เลื่อน map
//        googleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE);// แบบจริง
//        googleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN);//แบบภาพวาด
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
    }

    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(final Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            Button button = (Button) myContentsView.findViewById(R.id.btn_click);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
