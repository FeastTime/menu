package com.feasttime.view;

import android.os.Bundle;

import com.feasttime.menu.R;
import com.feasttime.model.RetrofitService;
import com.feasttime.model.bean.MainMenu;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.presenter.shoppingcart.ShoppingCartPresenter;
import com.feasttime.tools.LogUtil;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MenuContract.IMenuView {
    private static final String TAG = "MainActivity";
    private ShoppingCartPresenter mShoppingCartPresenter = new ShoppingCartPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mShoppingCartPresenter.createOrder("");
    }

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[]{mShoppingCartPresenter};
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void showMenu(MainMenu result) {

    }
}
