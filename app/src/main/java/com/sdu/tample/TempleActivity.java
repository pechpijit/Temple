package com.sdu.tample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.sdu.tample.model.ModelGallery;
import com.sdu.tample.model.ModelTempleDetail;

import java.util.HashMap;

import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TempleActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    Context mContext;
    TextView txt_title, txt_detail, txt_address, txt_holy, txt_time;
    LinearLayout view;
    private SliderLayout mDemoSlider;
    HashMap<String, String> url_maps;
    FancyButton btn_search;
    String la, lo;

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
        setContentView(R.layout.activity_temple);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        url_maps = new HashMap<String, String>();

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_detail = (TextView) findViewById(R.id.txt_detail);
        txt_address = (TextView) findViewById(R.id.txt_address);
        txt_holy = (TextView) findViewById(R.id.txt_holy);
        txt_time = (TextView) findViewById(R.id.txt_time);
        view = (LinearLayout) findViewById(R.id.view);
        btn_search = (FancyButton) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+la+","+lo));
                startActivity(intent);
            }
        });

        Bundle i = getIntent().getExtras();
        int id = i.getInt("id");
        new ConnectAPI().getTempleId(mContext, id);
    }

    public void setView(String string, String url) {
        Gson gson = new Gson();
        ModelTempleDetail model = gson.fromJson(string, ModelTempleDetail.class);

        for (ModelGallery name :
                model.getGallery()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(model.getTemple().getTempleName())
                    .image(url + "/templeImageGallery/" + name.getGalleryImageName())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", model.getTemple().getTempleName());

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setClickable(false);
        String[] temp = model.getTemple().getTempleName().split(" ");
        String name = model.getTemple().getTempleName().substring(0, temp[0].length()) + "\n" +
                model.getTemple().getTempleName().substring(temp[0].length());

        la = model.getTemple().getTempleLatittude();
        lo = model.getTemple().getTempleLongitude();
        txt_title.setText(name);
        txt_detail.setText(model.getTemple().getTempleDetail());
        txt_address.setText(model.getTemple().getTempleAddress());
        txt_holy.setText(model.getTemple().getTempleHolyObject());
        txt_time.setText(model.getTemple().getTempleTimeOpen() + "-" + model.getTemple().getTempleTimeClose());
        view.setVisibility(View.VISIBLE);
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
