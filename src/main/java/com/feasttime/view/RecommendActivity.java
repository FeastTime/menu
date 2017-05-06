package com.feasttime.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.feasttime.adapter.MainMenuPagerAdapter;
import com.feasttime.menu.R;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.presenter.menu.MenuPresenter;
import com.feasttime.presenter.shoppingcart.ShoppingCartPresenter;
import com.feasttime.tools.LogUtil;
import com.feasttime.tools.ScreenTools;
import com.feasttime.widget.jazzyviewpager.JazzyViewPager;

import butterknife.Bind;

/**
 * Created by chen on 2017/5/7.
 */

public class RecommendActivity extends BaseActivity implements MenuContract.IMenuView{
    private MenuPresenter mMenuPresenter = new MenuPresenter();

    @Bind(R.id.title_bar_content_rb)
    RadioGroup mTtitleBarMenuRb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenuPresenter.getMenu("158000000","0","0","0","0");
        mTtitleBarMenuRb.removeAllViews();
    }

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

    }



    @Override
    public void showMenu(MenuItemInfo menuItemInfo) {
        RadioButton menuRb = new RadioButton(this);
        menuRb.setButtonDrawable(android.R.color.transparent);
        menuRb.setText(menuItemInfo.getDishName() + "\n" + "hot");
        menuRb.setTextColor(Color.WHITE);
        menuRb.setPadding(ScreenTools.dip2px(this,40),0,ScreenTools.dip2px(this,40),0);
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
}
