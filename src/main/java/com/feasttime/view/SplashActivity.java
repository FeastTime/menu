package com.feasttime.view;

import android.os.Bundle;

import com.feasttime.presenter.IBasePresenter;

/**
 * Created by chen on 2017/4/16.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }
}
