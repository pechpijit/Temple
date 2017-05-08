package com.sdu.tample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sdu.tample.R;
import com.sdu.tample.model.ModelActivities;
import com.sdu.tample.model.ModelTemple;

import java.util.ArrayList;


public class AdapterMapTemple extends RecyclerView.Adapter<AdapterMapTemple.VersionViewHolder> {
    Boolean isHomeList = false;
    ArrayList<ModelTemple> posts;

    Context context;
    OnItemClickListener clickListener;

    public AdapterMapTemple(Context applicationContext, ArrayList<ModelTemple> posts) {
        this.context = applicationContext;
        this.posts = posts;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_map, viewGroup, false);

        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        String[] temp = posts.get(i).getTempleName().split(" ");
        versionViewHolder.txt_name.setText((i+1)+". "+posts.get(i).getTempleName().substring(0,temp[0].length())+"\n"+
                posts.get(i).getTempleName().substring(temp[0].length()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name;

        public VersionViewHolder(View itemView) {
            super(itemView);

            txt_name = (TextView) itemView.findViewById(R.id.txt_name);


            if (isHomeList) {
                itemView.setOnClickListener(this);
            } else {
                itemView.setOnClickListener(this);
            }


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
