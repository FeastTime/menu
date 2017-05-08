package com.feasttime.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.feasttime.menu.R;
import com.feasttime.presenter.IBasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by chen on 2017/5/8.
 */

public class EndActivity extends BaseActivity {


    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.end_activity;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
