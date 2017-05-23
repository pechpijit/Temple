package com.sdu.tample;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.adapter.AdapterActivities;
import com.sdu.tample.model.ModelActivities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActivitiesActivity extends AppCompatActivity {
    Context mContext;
    FancyButton btn_search;
    public int[] activitie;
    String[] search;
    int sum = 0;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_activities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;
        btn_search = (FancyButton) findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                for (int i = 0; i < activitie.length; i++) {
                    if (activitie[i] != 0) {
                        sum++;
                    }
                }
                search = new String[sum];
                for (int i = 0; i < activitie.length; i++) {
                    if (activitie[i] != 0) {
                        search[index] = String.valueOf(activitie[i]);
                        index++;
                    }
                }
//                Toast.makeText(ActivitiesActivity.this, ""+ Arrays.toString(search), Toast.LENGTH_SHORT).show();
                sum = 0;
                index = 0;

                startActivity(new Intent(mContext,KaowatActivity2.class).putExtra("id",search));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        new ConnectAPI().getActivitiesAll(mContext);
    }

    public void setAdap(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelActivities>>() {}.getType();
        Collection<ModelActivities> enums = gson.fromJson(data, collectionType);
        final ArrayList<ModelActivities> posts = new ArrayList<ModelActivities>(enums);
        activitie = new int[posts.size()];
        for (int i = 0; i < activitie.length; i++) {
            activitie[i] = 0;
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dummyfrag_scrollableview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        AdapterActivities adapter = new AdapterActivities(mContext, posts);
        recyclerView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new AdapterActivities.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID =posts.get(position).getId();

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

//    public void fetchProjects() {
//        GlobalVariables.getRestClient().getApiService().projects(globalVariables.getApiKey(), new RestCallback<List<Project>>() {
//            @Override
//            public void failure(RestError restError) {
//                Log.e(TAG, "failure");
//                Log.e(TAG, restError.getError());
//            }
//
//            @Override
//            public void success(List<Project> jsonProjects, Response response) {
//                Log.d(TAG, "success");
//            }
//        });
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

}
