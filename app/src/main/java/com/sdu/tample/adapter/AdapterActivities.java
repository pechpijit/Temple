package com.sdu.tample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sdu.tample.ActivitiesActivity;
import com.sdu.tample.R;
import com.sdu.tample.model.ModelActivities;

import java.util.ArrayList;


public class AdapterActivities extends RecyclerView.Adapter<AdapterActivities.VersionViewHolder> {
    Boolean isHomeList = false;
    ArrayList<ModelActivities> posts;

    Context context;
    OnItemClickListener clickListener;

    public AdapterActivities(Context applicationContext, ArrayList<ModelActivities> posts) {
        this.context = applicationContext;
        this.posts = posts;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_activities, viewGroup, false);

        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        versionViewHolder.checkBox.setText(posts.get(i).getActivitiesName());

        versionViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ((ActivitiesActivity) context).activitie[i] = posts.get(i).getId();
                }else{
                    ((ActivitiesActivity) context).activitie[i] = 0;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox checkBox;

        public VersionViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);


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
