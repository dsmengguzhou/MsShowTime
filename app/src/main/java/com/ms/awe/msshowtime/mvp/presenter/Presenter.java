package com.ms.awe.msshowtime.mvp.presenter;

import android.content.Intent;

import com.ms.awe.msshowtime.mvp.view.View;

/**
 * Created By Musi
 * on 2019/2/21
 */
public interface Presenter {

    void onCreate();
    void onStart();
    void onStop();
    void pause();
    void attachView(View view);     //挂载
    void attachIncomingIntent(Intent intent);

}
