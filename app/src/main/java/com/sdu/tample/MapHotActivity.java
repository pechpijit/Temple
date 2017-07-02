package com.sdu.tample;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.model.ModelHot;
import com.sdu.tample.model.ModelMapHot;
import com.sdu.tample.model.ModelTempleMap;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


public class MapHotActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    private static LatLng b9 = new LatLng(13.7154937, 100.5820363);
    private Marker mPerth;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map2);

        ID = getIntent().getExtras().getInt("id");

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        final GoogleMap mMap = map;
        new ConnectAPI().getHotAll(MapHotActivity.this, mMap,ID);
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

    private BitmapDescriptor setIcon(int i) {
        switch (i) {
            case 1:
                return BitmapDescriptorFactory.fromResource(R.drawable.ic_map3);
            case 2:
                return BitmapDescriptorFactory.fromResource(R.drawable.ic_map1);
            case 3:
                return BitmapDescriptorFactory.fromResource(R.drawable.ic_map2);
        }
        return BitmapDescriptorFactory.fromResource(R.drawable.ic_marker);
    }

    public void addMarker(GoogleMap mMap, String string,String url) {
        Gson gson = new Gson();
        ModelMapHot post = gson.fromJson(string, ModelMapHot.class);

        for (ModelHot contentModel :
                post.getHot()) {
            mPerth = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(contentModel.getInterestingLatitude()), Double.parseDouble(contentModel.getInterestingLongitude())))
                    .icon(setIcon(contentModel.getInterestingCategory()))
                    .title(contentModel.getInterestingName()));
            mPerth.setTag(contentModel.getId());

        }

        LatLng Position = null;
        int lastIndex = 0;

        for (ModelTempleMap contentModel :
                post.getWat()) {
            mPerth = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(contentModel.getLa()), Double.parseDouble(contentModel.getLo())))
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .icon((BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(url + "/templeImage_resize/" + contentModel.getImage()))))
                    .title(contentModel.getName()));
            mPerth.setTag(contentModel.getId());
            if (lastIndex == 0) {
                Position = new LatLng(Double.parseDouble(contentModel.getLa()), Double.parseDouble(contentModel.getLo()));
                lastIndex++;
            }
        }

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
        mMap.setBuildingsEnabled(true);
        if (!post.getWat().isEmpty()) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(Position).zoom(11).build(); // เอาไว้ Fix Location
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));//แบบมี Animation
        }
//        googleMap.getUiSettings().setScrollGesturesEnabled(false);
//        ปิดไม่ให้เลื่อน map
//        googleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE);// แบบจริง
//        googleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN);//แบบภาพวาด
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
    }

    private Bitmap getMarkerBitmapFromView(String url) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.avatar_marker_image_view);

        try {
            URL UR = new URL(url);
            Bitmap bmp = BitmapFactory.decodeStream(UR.openConnection().getInputStream());
            markerImageView.setImageBitmap(bmp);
        } catch (Exception e) {
            Log.d("loadImage", e+"");
        }

        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
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
