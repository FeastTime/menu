/*
 * Copyright (c) 2017. sheng yan
 */

package com.feasttime.view;

import android.os.Bundle;

import com.feasttime.menu.R;
import com.feasttime.presenter.IBasePresenter;

public class ShowWebActivity extends BaseActivity {
    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.show_web_activity;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
