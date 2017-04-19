package com.feasttime.presenter.mainmenu;


/**
 * 获取天气的Presenter
 * Created by glh on 2016-06-23.
 */
public class MenuPresenter implements MenuContract.IMenuPresenter {

    private MenuContract.IMenuView mIWeatherView;

    @Override
    public void init(MenuContract.IMenuView view) {
        this.mIWeatherView = view;
        mIWeatherView.initView();
    }

    @Override
    public void getMenu(final String city) {
//        mExecutorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                mWeatherManager.getNowWeather(city, new WeatherManager.WeatherListener() {
//                    @Override
//                    public void onSuccess(TPWeatherNow response) {
//                        mIWeatherView.showNowWeather(BeanUtil.createNowWeather(response));
//                    }
//
//                    @Override
//                    public void onFailed(String errString) {
//                        mIWeatherView.error(errString);
//                    }
//                });
//            }
//        });
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
