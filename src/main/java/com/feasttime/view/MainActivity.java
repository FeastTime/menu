package com.feasttime.view;

import android.os.Bundle;

import com.feasttime.menu.R;
import com.feasttime.model.RetrofitService;
import com.feasttime.model.bean.MainMenu;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.tools.LogUtil;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MenuContract.IMenuView {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


    }

    @Override
    protected void onResume() {
        //请求订单
        super.onResume();
    }

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[0];
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
