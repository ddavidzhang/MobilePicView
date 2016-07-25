package com.stone.beautyshare.views.widget;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by windows7 on 2015/6/29.
 */
public class SimplePageAdapter extends PagerAdapter {
    private List<View> views;

    public SimplePageAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object)
    {
        ((ViewPager) view).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position)
    {
        ((ViewPager) view).addView(views.get(position), 0);
        return views.get(position);
    }
}
