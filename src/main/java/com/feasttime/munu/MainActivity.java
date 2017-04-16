package com.feasttime.munu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    protected void onResume() {
        //请求订单
        super.onResume();
    }
}
