package com.sdu.tample.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.sdu.tample.MainActivity;
import com.sdu.tample.ProvinceActivity;
import com.sdu.tample.R;
import com.sdu.tample.model.ModelImageSlide;
import com.sdu.tample.model.ModelNews;
import com.sdu.tample.model.ModelProvince;
import com.sdu.tample.model.ModelProvinceGallry;
import com.sdu.tample.model.ModelProvinceNew;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterProvince extends RecyclerView.Adapter<AdapterProvince.VersionViewHolder> {
    ArrayList<ModelProvinceNew> posts;
    String url;
    Context mContext;
    OnItemClickListener clickListener;

    public AdapterProvince(Activity applicationContext, ArrayList posts, String url) {
        this.mContext = applicationContext;
        this.posts = posts;
        this.url = url;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_list_province, viewGroup, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
    versionViewHolder.viewPager.setAdapter(new DetailAdapter(mContext,posts.get(i).getImage(),url));
    versionViewHolder.txt_name.setText(posts.get(i).getProvince().getProvinceName());
    }

    @Override
    public int getItemCount() {

        return posts.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewPager viewPager;
        FrameLayout cardlist_item,back_txt,back;
        TextView txt_name;
        public VersionViewHolder(View itemView) {
            super(itemView);
            cardlist_item = (FrameLayout) itemView.findViewById(R.id.cardlist_item);
            back_txt = (FrameLayout) itemView.findViewById(R.id.back_txt);
            back = (FrameLayout) itemView.findViewById(R.id.back);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            viewPager = (ViewPager) itemView.findViewById(R.id.detail_view_pager);

//            back.setOnClickListener(this);
            back_txt.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}