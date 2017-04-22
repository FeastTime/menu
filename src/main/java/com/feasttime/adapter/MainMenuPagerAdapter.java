package com.feasttime.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feasttime.menu.R;
import com.feasttime.widget.jazzyviewpager.JazzyViewPager;
import com.feasttime.widget.jazzyviewpager.OutlineContainer;

/**
 * Created by chen on 2017/4/20.
 */



public class MainMenuPagerAdapter extends PagerAdapter {
    private Context context;
    private JazzyViewPager mJazzy;
    private LayoutInflater mLayoutInflater;

    public MainMenuPagerAdapter(Context context,JazzyViewPager jazzyViewPager) {
        this.context = context;
        this.mJazzy = jazzyViewPager;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout oneView = (LinearLayout) inflater.inflate(R.layout.menu_item_layout,null);
        LinearLayout twoView = (LinearLayout) inflater.inflate(R.layout.menu_item_layout,null);
        LinearLayout threeView = (LinearLayout) inflater.inflate(R.layout.menu_item_layout,null);

        oneView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        twoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        threeView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

        LinearLayout ll = new LinearLayout(context);
        ll.addView(oneView);
        ll.addView(twoView);
        ll.addView(threeView);

        ll.setBackgroundColor(Color.BLUE);
        ll.setGravity(Gravity.CENTER);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        container.addView(ll, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mJazzy.setObjectForPosition(ll, position);
        return ll;
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