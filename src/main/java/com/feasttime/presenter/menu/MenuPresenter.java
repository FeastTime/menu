package com.feasttime.presenter.menu;


import com.feasttime.model.RetrofitService;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.tools.LogUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 获取天气的Presenter
 * Created by glh on 2016-06-23.
 */
public class MenuPresenter implements MenuContract.IMenuPresenter {

    private MenuContract.IMenuView mIMenuView;

    @Override
    public void init(MenuContract.IMenuView view) {
        this.mIMenuView = view;
    }

    @Override
    public void getMenu(String mobileNO, String token, String orderID, String classType, String page) {
        RetrofitService.getMenu(mobileNO,token,orderID,classType,page).map(new Function<MenuInfo, List<MenuItemInfo>>() {
            @Override
            public List<MenuItemInfo> apply(MenuInfo menuIfno) throws Exception {
                return menuIfno.getDishesList();
            }
        }).flatMap(new Function<List<MenuItemInfo>, ObservableSource<MenuItemInfo>>() {
            @Override
            public ObservableSource<MenuItemInfo> apply(List<MenuItemInfo> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).subscribe(new Consumer<MenuItemInfo>() {
            @Override
            public void accept(MenuItemInfo menuItemInfo) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //这里接收onError
                LogUtil.d("result","error:");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //这里接收onComplete。
                LogUtil.d("result","complete");
            }
        });
    }


    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {

    }

}
