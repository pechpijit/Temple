package com.sdu.tample.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sdu.tample.R;
import com.sdu.tample.model.ModelProvinceGallry;

import java.util.ArrayList;

public class DetailAdapter extends PagerAdapter {

    private ArrayList<ModelProvinceGallry> mImageResArrayList;
    private Context mContext;
    private String url;
    public DetailAdapter(Context context, ArrayList<ModelProvinceGallry> imageResArrayList,String url) {
        this.mImageResArrayList = imageResArrayList;
        this.mContext = context;
        this.url = url;
    }

    @Override
    public int getCount() {
        return (mImageResArrayList == null) ? 0 : mImageResArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem (ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        try {
            Glide.with(mContext)
                    .load(url + "/provinceImageGallery/" +mImageResArrayList.get(position).getGalleryImageName())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(R.drawable.nopic)
                    .into(imageView);
        } catch (Exception e) {

        }
        container.addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
