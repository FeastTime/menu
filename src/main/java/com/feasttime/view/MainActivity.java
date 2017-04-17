package com.feasttime.view;

import android.os.Bundle;

import com.feasttime.menu.R;
import com.feasttime.model.bean.MainMenu;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.mainmenu.MainMenuContract;

public class MainActivity extends BaseActivity implements MainMenuContract.MenuView {

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
