package com.feasttime.presenter.menu;


import com.feasttime.model.bean.MenuIfno;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.IBaseView;


public interface MenuContract {
    interface IMenuView extends IBaseView {
        void showMenu(MenuIfno result);
    }

    interface IMenuPresenter extends IBasePresenter<IMenuView> {
        void getMenu(String mobileNO, String token, String orderID, String classType, String page);
    }
}
