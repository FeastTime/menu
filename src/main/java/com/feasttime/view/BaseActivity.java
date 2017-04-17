package com.feasttime.view;

import android.app.Activity;
import android.os.Bundle;

import com.feasttime.presenter.IPresenter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chen on 2017/4/16.
 */

public abstract class BaseActivity extends Activity{
    private Set<IPresenter> mAllPresenters = new HashSet<>(1);
    /**
     * 需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter
     */
    protected abstract IPresenter[] getPresenters();

    /**
     * 初始化presenters
     */
    protected abstract void onInitPresenters();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPresenters();
        onInitPresenters();
    }

    private void addPresenters() {
        IPresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (int i = 0; i < presenters.length; i++) {
                mAllPresenters.add(presenters[i]);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //依次调用IPresenter的onResume方法
        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onResume();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //依次调用IPresenter的onStop方法
        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onStop();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //依次调用IPresenter的onPause方法
        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onPause();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //依次调用IPresenter的onStart方法
        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onStart();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //依次调用IPresenter的onDestroy方法
        for (IPresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onDestroy();
            }
        }
    }
}