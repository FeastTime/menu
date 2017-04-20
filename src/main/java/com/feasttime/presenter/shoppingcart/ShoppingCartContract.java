package com.feasttime.presenter.shoppingcart;

import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.IBaseView;

/**
 * Created by chen on 2017/4/19.
 */

public interface ShoppingCartContract {
    interface IShoppingCartView extends IBaseView {
        void createOrderComplete();
    }

    interface IShoppingCartPresenter extends IBasePresenter<IShoppingCartView> {
        void createOrder(String token);
    }
}
