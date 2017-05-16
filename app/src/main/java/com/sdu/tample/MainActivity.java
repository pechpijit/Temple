package com.sdu.tample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.model.ModelImageSlide;
import com.sdu.tample.model.ModelTemple;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener{
    String URL;
    private SliderLayout mDemoSlider;
    HashMap<String, String> url_maps;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        url_maps = new HashMap<String, String>();

        findViewById(R.id.btn_temple).setOnClickListener(this);
        findViewById(R.id.btn_news).setOnClickListener(this);
        findViewById(R.id.btn_map).setOnClickListener(this);
        findViewById(R.id.btn_v).setOnClickListener(this);
        findViewById(R.id.btn_activities).setOnClickListener(this);
        findViewById(R.id.btn_quiz).setOnClickListener(this);

        new ConnectAPI().getImgTempleAll(mContext);
    }

    public void setHeader(String string , String url) {

        SharedPreferences sp = getSharedPreferences("", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("url", url);
        edit.commit();

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelImageSlide>>() {
        }.getType();
        Collection<ModelImageSlide> enums = gson.fromJson(string, collectionType);
        final ArrayList<ModelImageSlide> posts = new ArrayList<ModelImageSlide>(enums);

        for (ModelImageSlide name:
                posts) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name.getTempleName())
                    .image(url+"/templeImage/"+name.getTempleImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name.getTempleName());

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setClickable(false);
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        mDemoSlider.startAutoCycle();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_temple:
                startActivity(new Intent(mContext,KaowatActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.btn_news:
                startActivity(new Intent(mContext,NewsActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.btn_map:
                startActivity(new Intent(mContext,MapActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.btn_v:
                startActivity(new Intent(mContext,VehicleActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.btn_activities:
                startActivity(new Intent(mContext,ActivitiesActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.btn_quiz:
                startActivity(new Intent(mContext,MapHotActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
