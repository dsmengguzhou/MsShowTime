package com.ms.awe.msshowtime.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ms.awe.msshowtime.R;

/**
 * Created By Musi
 * on 2019/3/14
 */
public abstract class TopBarBaseActivity extends AppCompatActivity{

    Toolbar toolbar;
    FrameLayout viewContent;
    TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_top_bar);

        toolbar = findViewById(R.id.tool_bar);
        viewContent = findViewById(R.id.viewContent);
        tvTitle = findViewById(R.id.tv_title);
        //初始化设置Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //将继承TopBarBaseActivity的布局解析到FrameLayout里面
        LayoutInflater.from(TopBarBaseActivity.this).inflate(getContentView(),viewContent);
        init(savedInstanceState);
    }

    protected void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
    }

    protected abstract int getContentView();
    protected abstract void init(Bundle savedInstanceState);

    //修改TopBarBaseActivity，需要造接口并声明
    OnClickListener onClickListenerTopLeft;
    public interface OnClickListener{
        void onClick();
    }
    //重写方法并处理点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onClickListenerTopLeft.onClick();
        }
        return true;    //true告诉我们自己处理了点击事件
    }

    //添加一个方法用于设置图标资源id和监听器
    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener){
        toolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }

}
