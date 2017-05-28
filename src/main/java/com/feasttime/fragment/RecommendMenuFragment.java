/*
 * Copyright (c) 2017. sheng yan
 */

/*
 * Copyright (c) 2017. sheng yan
 */

package com.feasttime.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.feasttime.adapter.RecommendMenuAdapter;
import com.feasttime.fragment.BaseFragment;
import com.feasttime.menu.R;
import com.feasttime.model.bean.DishesCategoryInfo;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.presenter.menu.MenuPresenter;
import com.feasttime.tools.ScreenTools;
import com.feasttime.view.EndActivity;
import com.feasttime.widget.RecyclerViewDivider;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class RecommendMenuFragment extends BaseFragment implements MenuContract.IMenuView,View.OnClickListener{

    private MenuPresenter mMenuPresenter = new MenuPresenter();

    @Bind(R.id.title_bar_cart_ib)
    ImageButton barCartIb;

    @Bind(R.id.title_bar_content_rb)
    RadioGroup mTtitleBarMenuRb;

    @Bind(R.id.recommend_activity_dishes_level_ll)
    LinearLayout dishesLevelLl;

    @Bind(R.id.recommend_activity_hot_level_ll)
    LinearLayout dishesHotLevelLl;

    @Bind(R.id.recommend_activity_menu_list_rv)
    RecyclerView menuListRv;
    
    private Context mContext;

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[]{mMenuPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mMenuPresenter.init(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.recommend_activity;
    }

    @Override
    protected void initViews() {
        initRatingBar();
        menuListRv.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<Integer> datas = new ArrayList<Integer>();
        datas.add(1);
        datas.add(2);
        datas.add(3);
        RecommendMenuAdapter rma = new RecommendMenuAdapter(datas,this.getActivity());
        menuListRv.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.HORIZONTAL));
        menuListRv.setAdapter(rma);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = inflater.getContext();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMenuPresenter.getMenu("158000000","0","0","0","0");
        mTtitleBarMenuRb.removeAllViews();
    }

    @Override
    public void showMenu(MenuItemInfo menuItemInfo) {
        RadioButton menuRb = new RadioButton(mContext);
        menuRb.setButtonDrawable(android.R.color.transparent);
        menuRb.setText(menuItemInfo.getDishName() + "\n" + "hot");
        menuRb.setTextColor(Color.WHITE);
        menuRb.setPadding(ScreenTools.dip2px(mContext,40),0,ScreenTools.dip2px(mContext,40),0);

        if (mTtitleBarMenuRb.getChildCount() == 0) {
            menuRb.setBackgroundResource(R.drawable.title_left_menu_selector);
        } else {
            menuRb.setBackgroundResource(R.drawable.title_normal_menu_selector);
        }

        mTtitleBarMenuRb.addView(menuRb);

        ViewGroup.LayoutParams params = menuRb.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        menuRb.setLayoutParams(params);

        ((RadioButton)mTtitleBarMenuRb.getChildAt(0)).setChecked(true);
    }

    @Override
    public void showDishesCategory(DishesCategoryInfo.DishesCategoryListBean dishesCategoryListBean) {

    }

    @OnClick({R.id.title_bar_cart_ib})
    @Override
    public void onClick(View v) {
        if (barCartIb == v) {
            startActivity(new Intent(v.getContext(),EndActivity.class));
        }
    }

    private void initRatingBar() {
        for (int i = 0 ; i < 5 ; i ++) {
            CheckBox cb = new CheckBox(mContext);
            cb.setButtonDrawable(R.drawable.rating_bar_selector);
            cb.setChecked(true);
            cb.setTag(i);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int tag = (int)buttonView.getTag();
                        for (int i = 0 ; i < 5 ; i++) {
                            if (i <= tag) {
                                ((CheckBox)dishesLevelLl.getChildAt(i)).setChecked(true);
                            } else {
                                ((CheckBox)dishesLevelLl.getChildAt(i)).setChecked(false);
                            }
                        }
                    } else {
                        int tag = (int)buttonView.getTag();
                        for (int i = tag ; i < 5 ; i++) {
                            ((CheckBox)dishesLevelLl.getChildAt(i)).setChecked(false);
                        }
                    }
                }
            });
            dishesLevelLl.addView(cb);
        }

        for (int i = 0 ; i < 5 ; i ++) {
            CheckBox cb = new CheckBox(mContext);
            cb.setButtonDrawable(R.drawable.rating_bar_selector);
            cb.setChecked(true);
            cb.setTag(i);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int tag = (int)buttonView.getTag();
                        for (int i = 0 ; i < 5 ; i++) {
                            if (i <= tag) {
                                ((CheckBox)dishesHotLevelLl.getChildAt(i)).setChecked(true);
                            } else {
                                ((CheckBox)dishesHotLevelLl.getChildAt(i)).setChecked(false);
                            }
                        }
                    } else {
                        int tag = (int)buttonView.getTag();
                        for (int i = tag ; i < 5 ; i++) {
                            ((CheckBox)dishesHotLevelLl.getChildAt(i)).setChecked(false);
                        }
                    }
                }
            });
            dishesHotLevelLl.addView(cb);
        }
    }

}
