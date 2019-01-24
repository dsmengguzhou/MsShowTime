package com.ms.awe.msshowtime.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;

import com.ms.awe.msshowtime.R;

public class WindowActivity extends AppCompatActivity{

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

        setWindowAttr();
    }

    private void setWindowAttr() {
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();      //为了获取屏幕宽和高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.height = (int) (d.getHeight() *0.8);     //高度设置为屏幕的0.6
        p.width = (int) (d.getWidth()*0.8);        //宽度设置为屏幕的0.7
        p.alpha = 1.0f;                            //设置本身透明度
        p.dimAmount = 0.2f;                        //设置窗口外黑暗度
        getWindow().setAttributes(p);              //设置窗口样式activity
    }
}
