package com.sdu.tample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.adapter.AdapterListVehicle;
import com.sdu.tample.adapter.AdapterListVehicleCat;
import com.sdu.tample.model.ModelVehicle;
import com.sdu.tample.model.ModelVehicleCat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VehicleCatActivity extends AppCompatActivity {

    SwipeRefreshLayout mSwipeRefreshLayout;
    Context mContext;
    int idPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        idPro = getIntent().getExtras().getInt("id");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mContext = this;

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ConnectAPI().getVehicleCat(VehicleCatActivity.this);
            }
        });

        new ConnectAPI().getVehicleCat(VehicleCatActivity.this);

    }

    public void setAdap(String data,String url) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelVehicleCat>>() {
        }.getType();
        Collection<ModelVehicleCat> enums = gson.fromJson(data, collectionType);
        final ArrayList<ModelVehicleCat> posts = new ArrayList<ModelVehicleCat>(enums);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterListVehicleCat adapter = new AdapterListVehicleCat(this, posts, url);
        recyclerView.setAdapter(adapter);

        try {
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {

        }

        adapter.SetOnItemClickListener(new AdapterListVehicleCat.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = posts.get(position).getId();
                startActivity(new Intent(mContext, VehicleActivity.class).putExtra("id",ID).putExtra("idPro",idPro));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
