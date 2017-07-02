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
import com.sdu.tample.model.ModelVehicle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VehicleActivity extends AppCompatActivity {

    SwipeRefreshLayout mSwipeRefreshLayout;
    Context mContext;
    int idPro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mContext = this;
        idPro = getIntent().getExtras().getInt("idPro");

        Bundle i = getIntent().getExtras();
        final int id = i.getInt("id");

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ConnectAPI().getVehicleCatId(VehicleActivity.this,id,idPro);
            }
        });

        new ConnectAPI().getVehicleCatId(VehicleActivity.this,id,idPro);

    }

    public void setAdap(String data,String url) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelVehicle>>() {
        }.getType();
        Collection<ModelVehicle> enums = gson.fromJson(data, collectionType);
        final ArrayList<ModelVehicle> posts = new ArrayList<ModelVehicle>(enums);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterListVehicle adapter = new AdapterListVehicle(this, posts, url);
        recyclerView.setAdapter(adapter);

        try {
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {

        }

        adapter.SetOnItemClickListener(new AdapterListVehicle.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = posts.get(position).getId();
                startActivity(new Intent(mContext, DesVehicleActivity.class).putExtra("id",ID));
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
