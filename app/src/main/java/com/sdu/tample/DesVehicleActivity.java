package com.sdu.tample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.model.ModelGallery;
import com.sdu.tample.model.ModelLitanies;
import com.sdu.tample.model.ModelTempleDetail;
import com.sdu.tample.model.ModelVehicle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import at.blogc.android.views.ExpandableTextView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DesVehicleActivity extends AppCompatActivity implements View.OnClickListener{
    Context mContext;
    TextView txt_title;
    LinearLayout view,view_detail,view_address,view_tel,view_time,view_price;
    Button btn_search;
    String la, lo;
    int id = 0;
    String TAG = "TempleActivity";
    ImageView img_ex1,img_ex2,img_ex3, img_ex4,img_ex5,image;

    ExpandableTextView ex_detail,ex_address,ex_tel, ex_time,ex_price;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vechicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;

        txt_title = (TextView) findViewById(R.id.txt_title);
        view = (LinearLayout) findViewById(R.id.view);

        view_detail = (LinearLayout) findViewById(R.id.view_detail);
        view_address = (LinearLayout) findViewById(R.id.view_address);
        view_tel = (LinearLayout) findViewById(R.id.view_tel);
        view_price = (LinearLayout) findViewById(R.id.view_price);
        view_time = (LinearLayout) findViewById(R.id.view_time);

        btn_search = (Button) findViewById(R.id.btn_search);

        ex_detail = (ExpandableTextView) findViewById(R.id.ex_detail);
        ex_address = (ExpandableTextView) findViewById(R.id.ex_address);
        ex_tel = (ExpandableTextView) findViewById(R.id.ex_tel);
        ex_time = (ExpandableTextView) findViewById(R.id.ex_time);
        ex_price = (ExpandableTextView) findViewById(R.id.ex_price);

        img_ex1 = (ImageView) findViewById(R.id.img_ex1);
        img_ex2 = (ImageView) findViewById(R.id.img_ex2);
        img_ex3 = (ImageView) findViewById(R.id.img_ex3);
        img_ex4 = (ImageView) findViewById(R.id.img_ex4);
        img_ex5 = (ImageView) findViewById(R.id.img_ex5);
        image = (ImageView) findViewById(R.id.image);

        setSettingEx(ex_detail);
        setSettingEx(ex_address);
        setSettingEx(ex_tel);
        setSettingEx(ex_time);
        setSettingEx(ex_price);

        view_detail.setOnClickListener(this);
        view_address.setOnClickListener(this);
        view_time.setOnClickListener(this);
        view_tel.setOnClickListener(this);
        view_price.setOnClickListener(this);

        btn_search.setOnClickListener(this);

        Bundle i = getIntent().getExtras();
        id = i.getInt("id");
        new ConnectAPI().getVehicleId(mContext, id);
    }

    private void setSettingEx(ExpandableTextView ex) {
        ex.setAnimationDuration(750L);
        ex.setInterpolator(new LinearOutSlowInInterpolator());
    }

    public void setView(String string, String url) {
        Gson gson = new Gson();
        ModelVehicle model = gson.fromJson(string, ModelVehicle.class);

        la = model.getVehicleLatitude();
        lo = model.getVehicleLongitude();
        
        try {
            Glide.with(mContext)
                    .load(url + "/vehicleImage/" +model.getVehicleImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.nopic)
                    .into(image);
        } catch (Exception ew) {

        }

        getScreenOrientation(model.getVehicleName());

        ex_detail.setText(model.getVehicleDetail());
        ex_address.setText(model.getVehicleAddress());
        ex_tel.setText(model.getVehicleTel1()+"\n"+model.getVehicleTel2());
        ex_price.setText(model.getVehiclePirceMin()+"-"+model.getVehiclePirceMax());
        ex_time.setText(model.getVehicleTimeOpen() + "-" + model.getVehicleTimeClose());

        ex_detail.setOnExpandListener(new ExpandableTextView.OnExpandListener()
        {
            @Override
            public void onExpand(final ExpandableTextView view)
            {
                Log.d(TAG, "ExpandableTextView expanded");
            }

            @Override
            public void onCollapse(final ExpandableTextView view)
            {
                Log.d(TAG, "ExpandableTextView collapsed");
            }
        });

        view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void getScreenOrientation(String VechicleName) {
        String[] temp = VechicleName.split(" ");
        String name = VechicleName.substring(0, temp[0].length()) + "\n" +
                VechicleName.substring(temp[0].length());

        Display screenOrientation = getWindowManager().getDefaultDisplay();
        if (screenOrientation.getWidth() == screenOrientation.getHeight()) {
            txt_title.setText(name);
        } else {
            if (screenOrientation.getWidth() < screenOrientation.getHeight()) {
                txt_title.setText(name);
            } else {
                txt_title.setText(VechicleName);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" +la + "," + lo));
                startActivity(intent);
                break;
            case R.id.view_detail:
                setEx(ex_detail,img_ex1);
                break;
            case R.id.view_address:
                setEx(ex_address,img_ex2);
                break;
            case R.id.view_tel:
                setEx(ex_tel,img_ex3);
                break;
            case R.id.view_time:
                setEx(ex_time,img_ex4);
                break;
            case R.id.view_price:
                setEx(ex_price,img_ex5);
                break;
        }
    }

    private void setEx(ExpandableTextView ex,ImageView img) {
        ex.toggle();
        if (ex.isExpanded()) {
            img.animate().rotation(0).start();
            ex.collapse();
        } else {
            img.animate().rotation(180).start();
            ex.expand();
        }
    }
}
