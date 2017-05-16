package com.sdu.tample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sdu.tample.util.SystemUiHider;

public class FullscreenActivity extends Activity {
    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final boolean TOGGLE_ON_CLICK = true;
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
    private SystemUiHider mSystemUiHider;
    LinearLayout layoutScroll;
    ScrollView scrollView1;
    SeekBar seekBarSpeed;
    ImageView buttonSlide;
    Boolean isBottom = true;
    int speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        buttonSlideClick();
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setShadowLayer(10, 0, 0, Color.BLACK);
        textView5.setTextColor(Color.WHITE);

        String connect = getIntent().getExtras().getString("connect");

        TextView txt_connect = (TextView) findViewById(R.id.txt_connect);
        txt_connect.setText(connect);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.buttonSlide).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.seekBarSpeed).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    private void buttonSlideClick() {
        layoutScroll = (LinearLayout) findViewById(R.id.layoutScroll);
        scrollView1 = (ScrollView) findViewById(R.id.scrollView1);

        seekBarSpeed = (SeekBar) findViewById(R.id.seekBarSpeed);
        seekBarSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                speed = arg1 + 5;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        speed = seekBarSpeed.getProgress();
        buttonSlide = (ImageView) findViewById(R.id.buttonSlide);
        buttonSlide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isBottom) {
                    buttonSlide.setImageResource(R.drawable.mpause_a);
                } else {
                    buttonSlide.setImageResource(R.drawable.mplay_a);
                }
                scolling();
            }
        });

    }

    public void scolling() {
        new Thread(new Runnable() {
            int bottom = layoutScroll.getHeight() - scrollView1.getHeight();

            public void run() {
                if(isBottom) {
                    isBottom = false;
                    runOnUiThread(new Runnable() {
                        public void run() {
//                            scrollView1.scrollTo(0, 0);

                        }
                    });

                    while(!isBottom) {

                        try {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    scrollView1.scrollBy(0, 1);
                                    if(scrollView1.getScrollY() == bottom)
                                        isBottom = true;
                                }
                            });
                            Thread.sleep(speed);
                        } catch (InterruptedException e) { }
                    }
                } else {

                    isBottom = true;
                }
            }
        }).start();
    }

    public void onPause() {
        super.onPause();
        isBottom = true;
    }

    public void stop(View view) {
        scrollView1.scrollTo(0, 0);
    }
}
