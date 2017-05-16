package com.feasttime.presenter.order;

import com.feasttime.model.RetrofitService;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.model.bean.OrderInfo;
import com.feasttime.tools.LogUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by chen on 2017/5/11.
 */

public class OrderPresenter implements OrderContract.IOrderPresenter {
    private OrderContract.IOrderView iOrderView;

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

    @Override
    public void init(OrderContract.IOrderView view) {
         this.iOrderView = view;
    }

    @Override
    public void getShoppingCartList(String token) {
        RetrofitService.getShoppingCartList(token).subscribe(new Consumer<OrderInfo>() {
            @Override
            public void accept(OrderInfo orderInfo) throws Exception {
                //mIMenuView.showMenu(menuItemInfo);
                iOrderView.showMyOrder(orderInfo.getMyOrderList());
                iOrderView.showRecommendOrder(orderInfo.getRecommendOrderList());
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
}
