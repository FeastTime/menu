package com.feasttime.presenter.shoppingcart;

import com.feasttime.model.RetrofitService;
import com.feasttime.model.bean.CreateOrderInfo;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.tools.LogUtil;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by chen on 2017/4/19.
 */

public class ShoppingCartPresenter implements ShoppingCartContract.IShoppingCartPresenter{

    @Override
    public void createOrder(String token) {
        RetrofitService.createOrder(token).subscribe(new Consumer<CreateOrderInfo>(){
            @Override
            public void accept(CreateOrderInfo createOrderInfo) throws Exception {
                LogUtil.d("result","aa");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //这里接收onError
                LogUtil.d("result","error");
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
    public void init(ShoppingCartContract.IShoppingCartView view) {

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
