/*
 * Copyright (c) 2017. sheng yan
 */

package com.feasttime.presenter.statistics;

import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.model.bean.StatisticsPersonInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.IBaseView;
import com.feasttime.presenter.menu.MenuContract;

public interface StatisticsContract {
    interface IStatisticsView extends IBaseView {
        void showData(StatisticsPersonInfo result);
    }

    interface IStatisticsPresenter extends IBasePresenter<StatisticsContract.IStatisticsView> {
        void getStatisticsPersonalInfo(String token);
    }
}
