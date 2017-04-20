package com.feasttime.presenter.shoppingcart;

import com.feasttime.model.bean.CreateOrderInfo;
import com.feasttime.model.bean.MainMenu;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.IBaseView;

/**
 * Created by chen on 2017/4/19.
 */

public class ShoppingCartContract {
    interface IShoppingCartView extends IBaseView {

    }

    interface IShoppingCartPresenter extends IBasePresenter<ShoppingCartContract.IShoppingCartView> {
        void createOrder(String token);
    }
}
