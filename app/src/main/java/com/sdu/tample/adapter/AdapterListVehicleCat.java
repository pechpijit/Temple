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
import com.sdu.tample.model.ModelVehicle;
import com.sdu.tample.model.ModelVehicleCat;

import java.util.ArrayList;

public class AdapterListVehicleCat extends RecyclerView.Adapter<AdapterListVehicleCat.VersionViewHolder> {
    ArrayList<ModelVehicleCat> posts;
    String url;
    Context context;
    OnItemClickListener clickListener;

    public AdapterListVehicleCat(Activity applicationContext, ArrayList<ModelVehicleCat> posts, String url) {
        this.context = applicationContext;
        this.posts = posts;
        this.url = url;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_vehicle, viewGroup, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        versionViewHolder.txt_name.setText(posts.get(i).getCategoryName());
        versionViewHolder.txt_desc.setText("");

        try {
            Glide.with(context)
                    .load(url + "/vehicleImage/" +posts.get(i).getCategoryImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(R.drawable.nopic)
                    .into(versionViewHolder.img);
        } catch (Exception ew) {

        }
    }

    @Override
    public int getItemCount() {

        return posts.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name, txt_desc;
        ImageView img;

        public VersionViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_desc = (TextView) itemView.findViewById(R.id.txt_desc);
            img = (ImageView) itemView.findViewById(R.id.img);
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