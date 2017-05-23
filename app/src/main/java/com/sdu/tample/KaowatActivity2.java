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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.adapter.AdapterListKaowat;
import com.sdu.tample.model.ModelTemple;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class KaowatActivity2 extends AppCompatActivity {

    SwipeRefreshLayout mSwipeRefreshLayout;
    Context mContext;

    String[] id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kaowat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mContext = this;
        id = getIntent().getExtras().getStringArray("id");
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ConnectAPI().getActivitiesSearch(KaowatActivity2.this,implode(", ", id));
            }
        });



        new ConnectAPI().getActivitiesSearch(KaowatActivity2.this,implode(", ", id));
//        Toast.makeText(KaowatActivity2.this, implode(", ", id), Toast.LENGTH_SHORT).show();

    }

    public static String implode(String separator, String... data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length - 1; i++) {
            //data.length - 1 => to not add separator at the end
            if (!data[i].matches(" *")) {//empty string are ""; " "; "  "; and so on
                sb.append(data[i]);
                sb.append(separator);
            }
        }
        sb.append(data[data.length - 1].trim());
        return sb.toString();
    }

    public void setAdap(String data,String url) {

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelTemple>>() {
        }.getType();
        Collection<ModelTemple> enums = gson.fromJson(data, collectionType);
        final ArrayList<ModelTemple> posts = new ArrayList<ModelTemple>(enums);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterListKaowat adapter = new AdapterListKaowat(this, posts, url);
        recyclerView.setAdapter(adapter);

        try {
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {

        }

        adapter.SetOnItemClickListener(new AdapterListKaowat.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = posts.get(position).getId();
                startActivity(new Intent(mContext, TempleActivity.class).putExtra("id",ID));
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
