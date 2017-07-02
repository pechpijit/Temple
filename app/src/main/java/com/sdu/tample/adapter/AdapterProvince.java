package com.sdu.tample.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sdu.tample.R;
import com.sdu.tample.model.ModelNews;
import com.sdu.tample.model.ModelProvince;

import java.util.ArrayList;

public class AdapterProvince extends RecyclerView.Adapter<AdapterProvince.VersionViewHolder> {
    ArrayList<ModelProvince> posts;
    String url;
    Context context;
    OnItemClickListener clickListener;

    public AdapterProvince(Activity applicationContext, ArrayList posts, String url) {
        this.context = applicationContext;
        this.posts = posts;
        this.url = url;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_province, viewGroup, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        versionViewHolder.txt_name.setText(posts.get(i).getProvinceName());
        try {
            Glide.with(context)
                    .load(url + "/provinceImage/" +posts.get(i).getProvinceImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(R.drawable.nopic)
                    .into(versionViewHolder.img);
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {

        return posts.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView txt_name;

        public VersionViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
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