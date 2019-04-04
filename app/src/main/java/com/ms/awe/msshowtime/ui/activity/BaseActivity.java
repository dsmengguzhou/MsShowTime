package com.ms.awe.msshowtime.ui.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.ms.awe.msshowtime.receiver.NetBroadcastReceiver;
import com.ms.awe.msshowtime.utils.ActivityUtil;
import com.ms.awe.msshowtime.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created By Musi
 * on 2019/4/4
 */
public abstract class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.NetChangeListener{

    //网络状态改变监听事件
    public static NetBroadcastReceiver.NetChangeListener netEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        ActivityUtil.getInstance().addActivity(this);

        netEvent = this;

    }

    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }

    //网络状态改变时间监听
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
