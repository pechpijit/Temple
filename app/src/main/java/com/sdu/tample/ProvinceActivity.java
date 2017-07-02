package com.sdu.tample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.sdu.tample.adapter.AdapterNews;
import com.sdu.tample.adapter.AdapterProvince;
import com.sdu.tample.model.ModelNews;
import com.sdu.tample.model.ModelProvince;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class ProvinceActivity extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    Context mContext;
    MaterialSearchView searchView;
    ArrayList<ModelProvince> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_province);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(true);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);

        mContext = this;

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ConnectAPI().getProvinceAll(ProvinceActivity.this);
            }
        });

        new ConnectAPI().getProvinceAll(mContext);
    }

    public void setAdap(String data, String url) {

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelProvince>>() {
        }.getType();
        Collection<ModelProvince> enums = gson.fromJson(data, collectionType);
        posts = new ArrayList<ModelProvince>(enums);
        final String[] list = new String[posts.size()];

        for (int i = 0; i < posts.size(); i++) {
            list[i] = posts.get(i).getProvinceName();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterProvince adapter = new AdapterProvince(this, posts, url);
        recyclerView.setAdapter(adapter);

        try {
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {

        }

        adapter.SetOnItemClickListener(new AdapterProvince.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = posts.get(position).getId();
                String province = posts.get(position).getProvinceName();
                startActivity(new Intent(mContext, MainActivity.class).putExtra("id", ID).putExtra("province", province));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        setFormSearch(list);
    }

    private void setFormSearch(final String[] list) {
        searchView.setSuggestions(list);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean check = false;
                int ID = 0;
                String province = "";
                for (ModelProvince lists : posts) {
                    if (lists.getProvinceName().equals(query)) {
                        ID = lists.getId();
                        province = lists.getProvinceName();
                        check = true;
                    }
                }
                if (check) {
                    startActivity(new Intent(mContext, MainActivity.class).putExtra("id", ID).putExtra("province", province));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

//                Toast.makeText(ProvinceActivity.this, ""+temp, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, true);
                    Toast.makeText(ProvinceActivity.this, searchWrd, Toast.LENGTH_SHORT).show();
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}