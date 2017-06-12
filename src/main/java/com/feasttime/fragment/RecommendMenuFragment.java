/*
 * Copyright (c) 2017. sheng yan
 */

/*
 * Copyright (c) 2017. sheng yan
 */

package com.feasttime.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.feasttime.adapter.RecommendMenuAdapter;
import com.feasttime.menu.R;
import com.feasttime.model.bean.DishesCategoryInfo;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.model.bean.MyOrderListItemInfo;
import com.feasttime.model.bean.RecommendOrderListItemInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.presenter.menu.MenuPresenter;
import com.feasttime.presenter.shoppingcart.ShoppingCartContract;
import com.feasttime.presenter.shoppingcart.ShoppingCartPresenter;
import com.feasttime.tools.PreferenceUtil;
import com.feasttime.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class RecommendMenuFragment extends BaseFragment implements MenuContract.IMenuView,View.OnClickListener,ShoppingCartContract.IShoppingCartView,RecommendMenuAdapter.RecommendViewHolderClicks {

    private MenuPresenter mMenuPresenter = new MenuPresenter();
    private ShoppingCartPresenter mShoppingCartPresenter = new ShoppingCartPresenter();

    @Bind(R.id.recommend_activity_dishes_level_ll)
    LinearLayout dishesLevelLl;

    @Bind(R.id.recommend_activity_hot_level_ll)
    LinearLayout dishesHotLevelLl;

    @Bind(R.id.recommend_activity_menu_list_rv)
    RecyclerView menuListRv;
    
    private Context mContext;

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[]{mMenuPresenter,mShoppingCartPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mMenuPresenter.init(this);
        mShoppingCartPresenter.init(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.recommend_fragment;
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
        rma.setItemListener(this);
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

    }

    @Override
    public void showMenu(MenuInfo result) {

    }

    @Override
    public void showDishesCategory(DishesCategoryInfo.DishesCategoryListBean dishesCategoryListBean) {

    }

    @Override
    public void onClick(View v) {

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

    @Override
    public void addShoppingCartComplete() {

    }

    @Override
    public void removeShoppingCartComplete() {

    }

    @Override
    public void getShoppingcartListComplete() {

    }

    @Override
    public void onAddClicked(String uid) {
        String orderID = PreferenceUtil.getStringKey("orderID");
        if (!TextUtils.isEmpty(orderID)) {
            mShoppingCartPresenter.addShoppingCart("3",orderID);
        }
    }

    @Override
    public void onReduceClicked(String uid) {
        String orderID = PreferenceUtil.getStringKey("orderID");
        if (!TextUtils.isEmpty(orderID)) {
            mShoppingCartPresenter.removeShoppingCart("3",orderID);
        }
    }

    @Override
    public void showRecommendList(List<RecommendOrderListItemInfo> recommendOrderList) {

    }

    @Override
    public void showOrderList(List<MyOrderListItemInfo> myOrderList) {

    }
}
