package com.feasttime.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feasttime.widget.jazzyviewpager.JazzyViewPager;
import com.feasttime.widget.jazzyviewpager.OutlineContainer;

/**
 * Created by chen on 2017/4/20.
 */



public class MainMenuPagerAdapter extends PagerAdapter {
    private Context context;
    private JazzyViewPager mJazzy;

    public MainMenuPagerAdapter(Context context,JazzyViewPager jazzyViewPager) {
        this.context = context;
        this.mJazzy = jazzyViewPager;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        TextView text = new TextView(context);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(30);
        text.setTextColor(Color.WHITE);
        text.setText("Page " + position);
        text.setPadding(30, 30, 30, 30);
        int bg = Color.rgb((int) Math.floor(Math.random()*128)+64,
                (int) Math.floor(Math.random()*128)+64,
                (int) Math.floor(Math.random()*128)+64);
        text.setBackgroundColor(bg);
        container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mJazzy.setObjectForPosition(text, position);
        return text;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object obj) {
        container.removeView(mJazzy.findViewFromObject(position));
    }
    @Override
    public int getCount() {
        return 10;
    }
    @Override
    public boolean isViewFromObject(View view, Object obj) {
        if (view instanceof OutlineContainer) {
            return ((OutlineContainer) view).getChildAt(0) == obj;
        } else {
            return view == obj;
        }
    }
}