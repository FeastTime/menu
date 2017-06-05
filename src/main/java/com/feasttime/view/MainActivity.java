package com.feasttime.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.feasttime.adapter.MainMenuPagerAdapter;
import com.feasttime.fragment.MainMenuFragment;
import com.feasttime.fragment.MyOrderFragment;
import com.feasttime.fragment.RecommendMenuFragment;
import com.feasttime.menu.R;
import com.feasttime.model.bean.DishesCategoryInfo;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.model.bean.MyOrderListItemInfo;
import com.feasttime.model.bean.RecommendOrderListItemInfo;
import com.feasttime.model.bean.ScreenInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.presenter.menu.MenuPresenter;
import com.feasttime.presenter.order.OrderContract;
import com.feasttime.presenter.order.OrderPresenter;
import com.feasttime.presenter.shoppingcart.ShoppingCartContract;
import com.feasttime.presenter.shoppingcart.ShoppingCartPresenter;
import com.feasttime.tools.DeviceTool;
import com.feasttime.tools.LogUtil;
import com.feasttime.tools.PreferenceUtil;
import com.feasttime.tools.ScreenTools;
import com.feasttime.widget.jazzyviewpager.JazzyViewPager;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MenuContract.IMenuView,ShoppingCartContract.IShoppingCartView, View.OnClickListener,OrderContract.IOrderView {
    private static final String TAG = "MainActivity";
    private ShoppingCartPresenter mShoppingCartPresenter = new ShoppingCartPresenter();
    private MenuPresenter mMenuPresenter = new MenuPresenter();
    private OrderPresenter mOrderPresenter = new OrderPresenter();

    @Bind(R.id.main_fragment_container_fl)
    FrameLayout fragmentContainerFl;

    @Bind(R.id.title_bar_cart_ib)
    ImageButton cartIb;

    @Bind(R.id.title_bar_layout_menu_ib)
    ImageButton menuIb;

    @Bind(R.id.title_bar_content_rb)
    RadioGroup mTtitleBarMenuRb;

    @Bind(R.id.title_bar_layout_login_tv)
    TextView loginTv;


    private MyOrderFragment myOrderFragment;
    private MainMenuFragment mainMenuFragment;
    private RecommendMenuFragment recommendMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenInfo info = DeviceTool.getDeviceScreenInfo(this);
        mMenuPresenter.getDishesCategory();
        LogUtil.d(TAG,info.getWidth() + "X" + info.getHeight());
    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = PreferenceUtil.getStringKey("token");

//        if (!TextUtils.isEmpty(token)) {
//            mOrderPresenter.createOrder(token);
//        }

        String orderID = PreferenceUtil.getStringKey("orderID");

//        mShoppingCartPresenter.addShoppingCart("2",orderID);
        mShoppingCartPresenter.removeShoppingCart("2",orderID);
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
        mainMenuFragment = new MainMenuFragment();
        recommendMenuFragment = new RecommendMenuFragment();
        jumpFragment(mainMenuFragment);
    }

    private void jumpFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fragment_container_fl, fragment);
        transaction.commit();
    }

    public void jumpToRecommendFragment() {
        jumpFragment(recommendMenuFragment);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_activity;
    }


    @Override
    public void showMenu(final MenuItemInfo menuItemInfo) {

    }

    @Override
    public void createOrderComplete() {
        mMenuPresenter.getMenu("158000000","0","0","0","0");
    }

    @OnClick({R.id.title_bar_cart_ib,R.id.title_bar_layout_menu_ib,R.id.title_bar_layout_login_tv})
    @Override
    public void onClick(View v) {
        if (v == cartIb) {
            //jumpFragment(recommendMenuFragment);
            startActivity(new Intent(v.getContext(),EndActivity.class));
        } else if (v == menuIb) {
            if (myOrderFragment == null) {
                myOrderFragment = new MyOrderFragment();
            }

            jumpFragment(myOrderFragment);
        } else if (cartIb == v) {
            startActivity(new Intent(v.getContext(),EndActivity.class));
        } else if (loginTv == v) {
            startActivity(new Intent(v.getContext(),LoginActivity.class));
        }
    }


    private void removeOrder() {
        if (myOrderFragment != null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.remove(myOrderFragment);
            transaction.commit();
        }

    }

    @Override
    public void showDishesCategory(DishesCategoryInfo.DishesCategoryListBean dishesCategoryListBean) {
        RadioButton menuRb = new RadioButton(this);
        menuRb.setButtonDrawable(android.R.color.transparent);
        menuRb.setGravity(Gravity.CENTER);
        menuRb.setText(dishesCategoryListBean.getCategoryName() + "\n" + "hot");
        menuRb.setTextColor(Color.WHITE);
        menuRb.setTag(dishesCategoryListBean.getCategoryID());
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
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK && !mainMenuFragment.isVisible()) {
            jumpFragment(mainMenuFragment);
            return true;
        }
        return super.onKeyUp(keyCode, event);
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
    public void showMyOrder(List<MyOrderListItemInfo> myOrderList) {

    }
    @Override
    public void showRecommendOrder(List<RecommendOrderListItemInfo> recommendOrderList) {

    }
}
