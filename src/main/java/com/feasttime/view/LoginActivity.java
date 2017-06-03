/*
 * Copyright (c) 2017. sheng yan
 */

package com.feasttime.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.feasttime.menu.R;
import com.feasttime.presenter.IBasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.login_activity_login_btn)
    Button loginBtn;

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.login_activity_login_btn})
    @Override
    public void onClick(View v) {
        if (v == loginBtn) {

        }
    }
}
