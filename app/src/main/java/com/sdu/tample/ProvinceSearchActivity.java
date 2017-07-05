package com.sdu.tample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sdu.tample.adapter.AdapterProvince;
import com.sdu.tample.model.ModelProvinceNew;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProvinceSearchActivity extends AppCompatActivity {
    Context mContext;
    ArrayList<ModelProvinceNew> posts;
    EditText input_province;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    TextView txt_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_province_search);
        mContext = this;

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.dummyfrag_scrollableview);
        txt_back = (TextView) findViewById(R.id.txt_back);

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        input_province = (EditText) findViewById(R.id.input_province);
        input_province.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                new ConnectAPI().getProvinceSearchAll(mContext,charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void setAdap(String data, String url) {

        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelProvinceNew>>() {}.getType();
        Collection<ModelProvinceNew> enums = gson.fromJson(data, collectionType);
        posts = new ArrayList<ModelProvinceNew>(enums);
        final String[] list = new String[posts.size()];

        for (int i = 0; i < posts.size(); i++) {
            list[i] = posts.get(i).getProvince().getProvinceName();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterProvince adapter = new AdapterProvince(this, posts, url);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterProvince.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = posts.get(position).getProvince().getId();
                String province = posts.get(position).getProvince().getProvinceName();
                startActivity(new Intent(mContext, MainActivity.class).putExtra("id", ID).putExtra("province", province));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}