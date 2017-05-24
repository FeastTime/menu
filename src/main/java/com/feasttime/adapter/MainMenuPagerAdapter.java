package com.feasttime.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.feasttime.tools.ScreenTools;
import com.feasttime.view.PlayVideoActivity;
import com.feasttime.view.ShowWebActivity;
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
    public Object instantiateItem(final ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout view1 = (LinearLayout) inflater.inflate(R.layout.menu_item_layout,null);
        LinearLayout view2 = (LinearLayout) inflater.inflate(R.layout.menu_item_layout,null);
        LinearLayout view3 = (LinearLayout) inflater.inflate(R.layout.menu_item_layout,null);

        view1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        view2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        view3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

        TextView dishesName1 = (TextView)view1.findViewById(R.id.menu_item_layout_dishes_name_tv);
        TextView dishesName2 = (TextView)view2.findViewById(R.id.menu_item_layout_dishes_name_tv);
        TextView dishesName3 = (TextView)view3.findViewById(R.id.menu_item_layout_dishes_name_tv);

        TextView seeDetail1 = (TextView)view1.findViewById(R.id.menu_item_layout_dishes_detail_tv);
        TextView seeDetail2 = (TextView)view2.findViewById(R.id.menu_item_layout_dishes_detail_tv);
        TextView seeDetail3 = (TextView)view2.findViewById(R.id.menu_item_layout_dishes_detail_tv);

        dishesName1.setText("麻辣香锅");
        dishesName2.setText("麻辣香锅");
        dishesName3.setText("麻辣香锅");

        view1.setGravity(Gravity.CENTER);
        view2.setGravity(Gravity.CENTER);
        view3.setGravity(Gravity.CENTER);

        view1.setBackgroundColor(Color.TRANSPARENT);
        view2.setBackgroundColor(Color.TRANSPARENT);
        view3.setBackgroundColor(Color.TRANSPARENT);

        ImageView adflagIv1 = (ImageView) view1.findViewById(R.id.menu_item_layout_ad_flag_iv);
        ImageView adflagIv2 = (ImageView) view2.findViewById(R.id.menu_item_layout_ad_flag_iv);
        ImageView adflagIv3 = (ImageView) view3.findViewById(R.id.menu_item_layout_ad_flag_iv);

        TextView playVideoTv1 = (TextView)view1.findViewById(R.id.menu_item_layout_play_video_tv);
        TextView playVideoTv2 = (TextView)view2.findViewById(R.id.menu_item_layout_play_video_tv);
        TextView playVideoTv3 = (TextView)view3.findViewById(R.id.menu_item_layout_play_video_tv);


        adflagIv1.setImageResource(R.mipmap.ad_flag_chubang);
        adflagIv2.setImageResource(R.mipmap.ad_flag_chubang);
        adflagIv3.setImageResource(R.mipmap.ad_flag_chubang);

        playVideoTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PlayVideoActivity.class));
            }
        });

        playVideoTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PlayVideoActivity.class));
            }
        });

        playVideoTv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PlayVideoActivity.class));
            }
        });



        seeDetail1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShowWebActivity.class));
            }
        });

        seeDetail2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShowWebActivity.class));
            }
        });

        seeDetail3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShowWebActivity.class));
            }
        });

        ImageView dishes1 = (ImageView) view1.findViewById(R.id.menu_item_layout_dishes_iv);
        ImageView dishes2 = (ImageView) view2.findViewById(R.id.menu_item_layout_dishes_iv);
        ImageView dishes3 = (ImageView) view3.findViewById(R.id.menu_item_layout_dishes_iv);


        ViewGroup.LayoutParams params1 = dishes1.getLayoutParams();
        params1.width = ScreenTools.dip2px(context,300);
        dishes1.setLayoutParams(params1);

        ViewGroup.LayoutParams params2 = dishes2.getLayoutParams();
        params2.width = ScreenTools.dip2px(context,245);
        dishes2.setLayoutParams(params2);

        ViewGroup.LayoutParams params3 = dishes3.getLayoutParams();
        params3.width = ScreenTools.dip2px(context,135);
        dishes3.setLayoutParams(params3);



        LinearLayout ll = new LinearLayout(context);
        ll.addView(view1);
        ll.addView(view2);
        ll.addView(view3);

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