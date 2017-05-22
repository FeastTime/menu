/*
 * Copyright (c) 2017. sheng yan
 */

package com.feasttime.view;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.feasttime.menu.R;
import com.feasttime.presenter.IBasePresenter;

import butterknife.Bind;

public class PlayVideoActivity extends BaseActivity {
    @Bind(R.id.play_video_activity_vv)
    VideoView videoView;

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.play_video_activity;
    }

    @Override
    protected void initViews() {

        videoView.setMediaController(new MediaController(this));


        // 播放在线视频
        Uri mVideoUri = Uri.parse("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4");
        videoView.setVideoPath(mVideoUri.toString());

        videoView.start();
        videoView.requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
