/*
 * Copyright (c) 2017. sheng yan
 */

package com.feasttime.view;

import android.os.Bundle;
import android.webkit.WebView;

import com.feasttime.menu.R;
import com.feasttime.presenter.IBasePresenter;

import butterknife.Bind;

public class ShowWebActivity extends BaseActivity {
    @Bind(R.id.show_web_activity_wv)
    WebView webViewWv;

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
        webViewWv.loadUrl("http://www.baidu.com");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
