package com.feasttime.presenter.mainmenu;


import com.feasttime.model.bean.MainMenu;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.IBaseView;


public interface MainMenuContract {
    interface MenuView extends IBaseView {
        void showMenu(MainMenu result);
    }

    interface MenuPresenter extends IBasePresenter<MenuView> {
        void getMenu(String data);
    }
}
