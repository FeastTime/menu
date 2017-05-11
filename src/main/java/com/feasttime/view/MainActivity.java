package com.feasttime.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.feasttime.adapter.MainMenuPagerAdapter;
import com.feasttime.fragment.MyOrderFragment;
import com.feasttime.menu.R;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.model.bean.ScreenInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.presenter.menu.MenuPresenter;
import com.feasttime.presenter.shoppingcart.ShoppingCartContract;
import com.feasttime.presenter.shoppingcart.ShoppingCartPresenter;
import com.feasttime.tools.DeviceTool;
import com.feasttime.tools.LogUtil;
import com.feasttime.tools.ScreenTools;
import com.feasttime.widget.jazzyviewpager.JazzyViewPager;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MenuContract.IMenuView,ShoppingCartContract.IShoppingCartView, View.OnClickListener,ViewPager.OnPageChangeListener {
    private static final String TAG = "MainActivity";
    private ShoppingCartPresenter mShoppingCartPresenter = new ShoppingCartPresenter();
    private MenuPresenter mMenuPresenter = new MenuPresenter();

    @Bind(R.id.main_menu_viewpager)
    JazzyViewPager jazzyViewPager;

    @Bind(R.id.title_bar_content_rb)
    RadioGroup mTtitleBarMenuRb;

    @Bind(R.id.main_activity_left_ib)
    ImageButton leftIb;

    @Bind(R.id.main_activity_right_ib)
    ImageButton rightIb;

    @Bind(R.id.menu_item_layout_viewpage_indicate_rg)
    RadioGroup viewpageIndicateRg;

    @Bind(R.id.toTheAdBtn)
    Button mToAdBtn;

    @Bind(R.id.title_bar_cart_ib)
    ImageButton cartIb;

    @Bind(R.id.title_bar_layout_menu_ib)
    ImageButton menuIb;

    private MyOrderFragment myOrderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenInfo info = DeviceTool.getDeviceScreenInfo(this);
        LogUtil.d(TAG,info.getWidth() + "X" + info.getHeight());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mShoppingCartPresenter.createOrder("");
        mTtitleBarMenuRb.removeAllViews();
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
        int count = mainMenuPagerAdapter.getCount();

        for (int i = 0 ; i < count ; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setBackgroundResource(R.drawable.viewpage_indicate_selector);
            rb.setButtonDrawable(android.R.color.transparent);
            rb.setWidth(ScreenTools.dip2px(this,10));
            rb.setHeight(ScreenTools.dip2px(this,10));
            viewpageIndicateRg.addView(rb);

            rb.setTag(i);
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     LogUtil.d("result","the position ->" + buttonView.getTag());

                }
            });
        }

        jazzyViewPager.setOnPageChangeListener(this);
        ((RadioButton)viewpageIndicateRg.getChildAt(0)).setChecked(true);

    }



    @Override
    protected int getLayoutResId() {
        return R.layout.main_activity;
    }


    @Override
    public void showMenu(final MenuItemInfo menuItemInfo) {

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

        menuRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    removeOrder();
                }
            }
        });

        mTtitleBarMenuRb.addView(menuRb);

        ViewGroup.LayoutParams params = menuRb.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        menuRb.setLayoutParams(params);

        ((RadioButton)mTtitleBarMenuRb.getChildAt(0)).setChecked(true);
    }

    @Override
    public void createOrderComplete() {
        mMenuPresenter.getMenu("158000000","0","0","0","0");
    }

    @OnClick({R.id.main_activity_left_ib,R.id.main_activity_right_ib,R.id.toTheAdBtn,R.id.title_bar_cart_ib,R.id.title_bar_layout_menu_ib})
    @Override
    public void onClick(View v) {
        if (v == leftIb) {
            jazzyViewPager.setCurrentItem(jazzyViewPager.getCurrentItem() - 1);
        } else if (v == rightIb) {
            jazzyViewPager.setCurrentItem(jazzyViewPager.getCurrentItem() + 1);
        } else if (v == mToAdBtn) {
            startActivity(new Intent(this,SilentADActivity.class));
        } else if (v == cartIb) {
            startActivity(new Intent(this,RecommendActivity.class));
        } else if (v == menuIb) {
            myOrderFragment = new MyOrderFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_activity_my_order_container_fl, myOrderFragment);
            transaction.commit();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((RadioButton)viewpageIndicateRg.getChildAt(position)).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void removeOrder() {
        if (myOrderFragment != null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.remove(myOrderFragment);
            transaction.commit();
        }

    }
}
