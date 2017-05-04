package com.feasttime.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        oneView.setGravity(Gravity.CENTER);
        twoView.setGravity(Gravity.CENTER);
        threeView.setGravity(Gravity.CENTER);

        oneView.setBackgroundColor(Color.TRANSPARENT);
        twoView.setBackgroundColor(Color.TRANSPARENT);
        threeView.setBackgroundColor(Color.TRANSPARENT);

        ImageView adflagIv1 = (ImageView) oneView.findViewById(R.id.menu_item_layout_ad_flag_iv);
        ImageView adflagIv2 = (ImageView) twoView.findViewById(R.id.menu_item_layout_ad_flag_iv);
        ImageView adflagIv3 = (ImageView) threeView.findViewById(R.id.menu_item_layout_ad_flag_iv);

        adflagIv1.setImageResource(R.mipmap.ad_flag_chubang);
        adflagIv2.setImageResource(R.mipmap.ad_flag_chubang);
        adflagIv3.setImageResource(R.mipmap.ad_flag_chubang);


        ImageView dishes1 = (ImageView) oneView.findViewById(R.id.menu_item_layout_dishes_iv);
        ImageView dishes2 = (ImageView) twoView.findViewById(R.id.menu_item_layout_dishes_iv);
        ImageView dishes3 = (ImageView) threeView.findViewById(R.id.menu_item_layout_dishes_iv);


        ViewGroup.LayoutParams params1 = dishes1.getLayoutParams();
        params1.width = 500;
        dishes1.setLayoutParams(params1);

        ViewGroup.LayoutParams params2 = dishes2.getLayoutParams();
        params2.width = 450;
        dishes2.setLayoutParams(params2);

        ViewGroup.LayoutParams params3 = dishes3.getLayoutParams();
        params3.width = 250;
        dishes3.setLayoutParams(params3);



        LinearLayout ll = new LinearLayout(context);
        ll.addView(oneView);
        ll.addView(twoView);
        ll.addView(threeView);

        ll.setBackgroundColor(Color.TRANSPARENT);
        ll.setGravity(Gravity.CENTER);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        container.addView(ll, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mJazzy.setObjectForPosition(ll, position);

        dishes1.setImageResource(R.mipmap.temp_dishes_1);
        dishes2.setImageResource(R.mipmap.temp_dishes_2);
        dishes3.setImageResource(R.mipmap.temp_dishes_3);


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