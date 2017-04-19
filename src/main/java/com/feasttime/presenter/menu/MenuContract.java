package com.feasttime.presenter.menu;


import com.feasttime.model.bean.MainMenu;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.IBaseView;


public interface MenuContract {
    interface IMenuView extends IBaseView {
        void showMenu(MainMenu result);
    }

    interface IMenuPresenter extends IBasePresenter<IMenuView> {
        void getMenu(String data);
    }
}
