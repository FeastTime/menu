package com.feasttime.presenter.mainmenu;


import com.feasttime.model.bean.MainMenu;
import com.feasttime.presenter.IPresenter;
import com.feasttime.presenter.IView;


public interface MainMenuContract {
    interface MenuView extends IView {
//        void showNowWeather(MainMenu result);
//
//        void error(String error);
    }

    interface IWeatherPresenter extends IPresenter<MenuView> {
//        void getWeather(String city);
    }
}
