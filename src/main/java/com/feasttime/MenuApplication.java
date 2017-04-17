package com.feasttime;

import android.app.Application;
import android.util.Log;

import com.feasttime.model.RetrofitService;
import com.tencent.bugly.crashreport.CrashReport;


public class MenuApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test","MenuApplication");
        CrashReport.initCrashReport(getApplicationContext(), "3b4f343c2c", false);
        RetrofitService.init();

    }
}
