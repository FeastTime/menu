package com.feasttime.view;

import android.os.Bundle;

import com.feasttime.menu.R;
import com.feasttime.model.RetrofitService;
import com.feasttime.model.bean.MainMenu;
import com.feasttime.model.bean.MyLocation;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.tools.LogUtil;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MenuContract.IMenuView {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        RetrofitService.getLocationList("10","1").subscribe(new Consumer<MyLocation>(){
            @Override
            public void accept(MyLocation myLocation) throws Exception {
                LogUtil.d(TAG,"success");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //这里接收onError
                LogUtil.d(TAG,"error");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //这里接收onComplete。
                LogUtil.d(TAG,"complete");
            }
        });
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
