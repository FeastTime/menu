package com.feasttime.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feasttime.adapter.MainMenuPagerAdapter;
import com.feasttime.menu.R;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.presenter.menu.MenuPresenter;
import com.feasttime.presenter.shoppingcart.ShoppingCartContract;
import com.feasttime.presenter.shoppingcart.ShoppingCartPresenter;
import com.feasttime.widget.jazzyviewpager.JazzyViewPager;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MenuContract.IMenuView,ShoppingCartContract.IShoppingCartView {
    private static final String TAG = "MainActivity";
    private ShoppingCartPresenter mShoppingCartPresenter = new ShoppingCartPresenter();
    private MenuPresenter mMenuPresenter = new MenuPresenter();

    @Bind(R.id.main_menu_viewpager)
    JazzyViewPager jazzyViewPager;

    @Bind(R.id.title_bar_content_ll)
    LinearLayout mTtitleBarMenuLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mShoppingCartPresenter.createOrder("");
    }

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[]{mShoppingCartPresenter,mMenuPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mShoppingCartPresenter.init(this);
        mMenuPresenter.init(this);
    }

    @Override
    protected void initViews() {
        jazzyViewPager.setTransitionEffect(JazzyViewPager.TransitionEffect.Tablet);
        jazzyViewPager.setPageMargin(30);

        MainMenuPagerAdapter mainMenuPagerAdapter = new MainMenuPagerAdapter(this,jazzyViewPager);
        jazzyViewPager.setAdapter(mainMenuPagerAdapter);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_activity;
    }


    @Override
    public void showMenu(final MenuItemInfo menuItemInfo) {
        TextView menuItemTv = new TextView(MainActivity.this);
        menuItemTv.setTextColor(Color.parseColor("#987889"));
        menuItemTv.setBackgroundColor(Color.parseColor("#111236"));
        menuItemTv.setText(menuItemInfo.getDishNO());
        mTtitleBarMenuLl.addView(menuItemTv);
    }

    @Override
    public void createOrderComplete() {
        mMenuPresenter.getMenu("158000000","0","0","0","0");
    }


}
