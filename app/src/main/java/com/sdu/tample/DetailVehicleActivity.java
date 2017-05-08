package com.sdu.tample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.sdu.tample.model.ModelVehicle;

import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailVehicleActivity extends AppCompatActivity {

    TextView txt_title,txt_detail,txt_price,txt_time, txt_tel;
    ImageView imageView;
    Context mContext;
    LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_detail = (TextView) findViewById(R.id.txt_detail);
        txt_price = (TextView) findViewById(R.id.txt_price);
        txt_time = (TextView) findViewById(R.id.txt_time);
        txt_tel = (TextView) findViewById(R.id.txt_tel);
        imageView = (ImageView) findViewById(R.id.image);
        view = (LinearLayout) findViewById(R.id.view);


        Bundle i = getIntent().getExtras();
        int id = i.getInt("id");

        new ConnectAPI().getVehicleId(mContext,id);
    }

    public void setView(String string, String url) {
        Gson gson = new Gson();
        ModelVehicle model = gson.fromJson(string, ModelVehicle.class);

        try {
            Glide.with(mContext)
                    .load(url + "/vehicleImage/" +model.getVehicleImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(R.drawable.nopic)
                    .into(imageView);
        } catch (Exception ew) {

        }

        txt_title.setText(model.getVehicleName());
        txt_detail.setText(model.getVehicleDetail());
        txt_price.setText(model.getVehiclePirceMin()+"-"+model.getVehiclePirceMax());
        txt_time.setText(model.getVehicleTimeOpen()+"-"+model.getVehicleTimeClose());
        txt_tel.setText(model.getVehicleTel1()+"-"+model.getVehicleTel2());
        view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
