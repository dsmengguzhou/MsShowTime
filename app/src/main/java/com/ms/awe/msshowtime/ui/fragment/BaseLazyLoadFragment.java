package com.ms.awe.msshowtime.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created By Musi
 * on 2019/4/2
 */
public abstract class BaseLazyLoadFragment extends Fragment{

    private boolean isFirstLoad = false;

    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        //将子类实现初始化视图
        View view = initView(inflater,container);
        //初始化事件
        initEvent();
        //视图创建完成，将变量置为true
        isFirstLoad = true;
        //如果Fragment可见进行数据加载
        if (getUserVisibleHint()){
            onLazyLoad();
            isFirstLoad = false;
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //视图销毁将变量置为false
        isFirstLoad = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //视图变为可见并且是第一次加载
        if (isFirstLoad & isVisibleToUser){
            onLazyLoad();
            isFirstLoad = false;
        }
    }

    //数据加载接口，留给子类实现
    public abstract void onLazyLoad();

    //初始化视图接口，子类必须实现
    public abstract View initView(LayoutInflater inflater,ViewGroup container);

    //初始化事件接口，留给子类实现
    public abstract void initEvent();
}
