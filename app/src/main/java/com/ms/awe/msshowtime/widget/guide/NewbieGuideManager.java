package com.ms.awe.msshowtime.widget.guide;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.utils.ScreenUtils;

/**
 * Created By Musi
 * on 2019/2/20
 */
public class NewbieGuideManager {

    private static final String TAG = "newbie_guide";

    public static final int TYPE_MS = 0;

    private Activity mActivity;
    private SharedPreferences sp;
    private NewbieGuide mNewbieGuide;
    private int mType;

    public NewbieGuideManager(Activity activity, int type) {
        mNewbieGuide = new NewbieGuide(activity);
        sp = activity.getSharedPreferences(TAG, Activity.MODE_PRIVATE);
        mActivity = activity;
        mType = type;
    }

    public NewbieGuideManager addView(View view, int shape) {
        mNewbieGuide.addHighLightView(view, shape);
        return this;
    }

    public void show() {
        show(0);
    }

    public void show(int delayTime) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(TAG + mType, false);
        editor.apply();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (mType) {
                    case TYPE_MS:
                        mNewbieGuide.setEveryWhereTouchable(false).addMsgAndKnowTv("",ScreenUtils.dpToPx(mActivity, 150)).show();
                        break;
                }
            }
        }, delayTime);
    }

    public void showWithListener(int delayTime, NewbieGuide.OnGuideChangedListener onGuideChangedListener) {
        mNewbieGuide.setOnGuideChangedListener(onGuideChangedListener);
        show(delayTime);
    }

    /**
     * 判断新手引导也是否已经显示了
     */
    public static boolean isNeverShowed(Activity activity, int type) {
        return activity.getSharedPreferences(TAG, Activity.MODE_PRIVATE).getBoolean(TAG + type, true);
    }
}
