package com.sdu.tample;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.model.ModelGallery;
import com.sdu.tample.model.ModelLitanies;
import com.sdu.tample.model.ModelTemple;
import com.sdu.tample.model.ModelTempleDetail;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

import at.blogc.android.views.ExpandableTextView;
import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TempleActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,View.OnClickListener{
    Context mContext;
    TextView txt_title;
    LinearLayout view,view_detail,view_address,view_object,view_time,view_nearby;
    private SliderLayout mDemoSlider;
    HashMap<String, String> url_maps;
    Button btn_search, btn_litanies;
    String la, lo;
    int id = 0;
    String TAG = "TempleActivity";
    ImageView img_ex1,img_ex2,img_ex3, img_ex4, img_ex5;

    ExpandableTextView ex_detail,ex_address,ex_object, ex_time,ex_nearby;

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
        view = (LinearLayout) findViewById(R.id.view);

        view_detail = (LinearLayout) findViewById(R.id.view_detail);
        view_address = (LinearLayout) findViewById(R.id.view_address);
        view_object = (LinearLayout) findViewById(R.id.view_object);
        view_time = (LinearLayout) findViewById(R.id.view_time);
        view_nearby = (LinearLayout) findViewById(R.id.view_nearby);

        btn_litanies = (Button) findViewById(R.id.btn_litanies);
        btn_search = (Button) findViewById(R.id.btn_search);

        ex_detail = (ExpandableTextView) this.findViewById(R.id.ex_detail);
        ex_address = (ExpandableTextView) this.findViewById(R.id.ex_address);
        ex_object = (ExpandableTextView) this.findViewById(R.id.ex_object);
        ex_time = (ExpandableTextView) this.findViewById(R.id.ex_time);
        ex_nearby = (ExpandableTextView) this.findViewById(R.id.ex_nearby);

        img_ex1 = (ImageView) findViewById(R.id.img_ex1);
        img_ex2 = (ImageView) findViewById(R.id.img_ex2);
        img_ex3 = (ImageView) findViewById(R.id.img_ex3);
        img_ex4 = (ImageView) findViewById(R.id.img_ex4);
        img_ex5 = (ImageView) findViewById(R.id.img_ex5);

        setSettingEx(ex_detail);
        setSettingEx(ex_address);
        setSettingEx(ex_object);
        setSettingEx(ex_time);
        setSettingEx(ex_nearby);

        view_detail.setOnClickListener(this);
        view_address.setOnClickListener(this);
        view_object.setOnClickListener(this);
        view_time.setOnClickListener(this);
        view_nearby.setOnClickListener(this);

        btn_litanies.setOnClickListener(this);
        btn_search.setOnClickListener(this);

        Bundle i = getIntent().getExtras();
        id = i.getInt("id");
        new ConnectAPI().getTempleId(mContext, id);
    }

    private void setSettingEx(ExpandableTextView ex) {
        ex.setAnimationDuration(750L);
        ex.setInterpolator(new LinearOutSlowInInterpolator());
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

//        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setClickable(false);


        la = model.getTemple().getTempleLatittude();
        lo = model.getTemple().getTempleLongitude();


        getScreenOrientation(model.getTemple().getTempleName());

        ex_detail.setText(model.getTemple().getTempleDetail());
        ex_address.setText(model.getTemple().getTempleAddress());
        ex_object.setText(model.getTemple().getTempleHolyObject());
        ex_time.setText(model.getTemple().getTempleTimeOpen() + "-" + model.getTemple().getTempleTimeClose());
        ex_nearby.setText(model.getTemple().getTemplePlaceMost());

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

    public void onResume() {
        super.onResume();

        new ConnectAPI().getTempleId(mContext, id);
    }

    public void dialog_(final String strings) {

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelLitanies>>() {
        }.getType();
        Collection<ModelLitanies> enums = gson.fromJson(strings, collectionType);
        final ArrayList<ModelLitanies> posts = new ArrayList<ModelLitanies>(enums);
        final String[] list = new String[posts.size()];

        for (int i = 0; i < posts.size(); i++) {
            list[i] = posts.get(i).getName();
        }

        AlertDialog.Builder builder =
                new AlertDialog.Builder(mContext);
        builder.setTitle("เลือกบทสวดมนต์");
        builder.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selected = posts.get(which).getDetail();
                startActivity(new Intent(mContext, FullscreenActivity.class).putExtra("connect", selected));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        builder.setNegativeButton("ปิด", null);
        builder.create();

// สุดท้ายอย่าลืม show() ด้วย
        builder.show();
    }

    public void getScreenOrientation(String templeName) {
        String[] temp = templeName.split(" ");
        String name = templeName.substring(0, temp[0].length()) + "\n" +
                templeName.substring(temp[0].length());

        Display screenOrientation = getWindowManager().getDefaultDisplay();
        if (screenOrientation.getWidth() == screenOrientation.getHeight()) {
            txt_title.setText(name);
        } else {
            if (screenOrientation.getWidth() < screenOrientation.getHeight()) {
                txt_title.setText(name);
            } else {
                txt_title.setText(templeName);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_litanies:
                new ConnectAPI().getLitaniesId(mContext, id);
                break;
            case R.id.btn_search:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+la+","+lo));
                startActivity(intent);
                break;
            case R.id.view_detail:
                setEx(ex_detail,img_ex1);
                break;
            case R.id.view_address:
                setEx(ex_address,img_ex2);
                break;
            case R.id.view_object:
                setEx(ex_object,img_ex3);
                break;
            case R.id.view_time:
                setEx(ex_time,img_ex4);
                break;
            case R.id.view_nearby:
                setEx(ex_nearby,img_ex5);
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
