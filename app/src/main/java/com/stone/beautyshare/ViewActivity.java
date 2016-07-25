package com.stone.beautyshare;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.stone.beautyshare.config.Classify;
import com.stone.beautyshare.config.ClassifyConfig;
import com.stone.beautyshare.views.widget.SimplePageAdapter;
import com.utils.singleton.SingleTonVolley;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends Activity {
    private int classifyIndex;
    private int photosIndex;
    private ViewPager viewPager;
    private TextView viewBarText;
    private List<View> pageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        classifyIndex = getIntent().getExtras().getInt("ClassifyIndex");
        photosIndex = getIntent().getExtras().getInt("PhotosIndex");
        viewPager = (ViewPager) findViewById(R.id.viewimg_viewpager);
        final Classify.Photos photos = ClassifyConfig.getClassifies().get(classifyIndex).photos.get(photosIndex);
        viewBarText = (TextView) findViewById(R.id.view_bar_text);
        String baseUrl = photos.baseUrl;
        pageViews = new ArrayList<>();
        for (Classify.SinglePic pic : photos.pics) {
            NetworkImageView imageView = new NetworkImageView(this);
            imageView.setTag("url");
            imageView.setImageUrl(baseUrl + pic.url, SingleTonVolley.getInstance(this).getImageLoader());
            pageViews.add(imageView);
        }
        viewPager.setAdapter(new SimplePageAdapter(pageViews));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                viewBarText.setText(photos.name+" "+(i+1)+"/"+photos.pics.size());
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(0);
    }
}
