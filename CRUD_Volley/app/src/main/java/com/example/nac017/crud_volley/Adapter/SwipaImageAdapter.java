package com.example.nac017.crud_volley.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nac017.crud_volley.R;

/**
 * Created by nac017 on 08/11/17.
 */

public class SwipaImageAdapter extends PagerAdapter {
    private Context context;
    private String images[];

    public SwipaImageAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup parent, int position) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.slideimage, parent,
                false);

        ImageView imageView;
        imageView = (ImageView) layout.findViewById(R.id.imageViewForSlide);

        Glide.with(context).load(images[position]).into(imageView);
        //Glide.with(this).load("urlnya").into(imageView);

        parent.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}