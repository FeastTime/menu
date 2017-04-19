package com.feasttime.view;

import android.content.Intent;
import android.os.Bundle;

import com.feasttime.menu.R;
import com.feasttime.presenter.IBasePresenter;

/**
 * Created by chen on 2017/4/16.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }
}
