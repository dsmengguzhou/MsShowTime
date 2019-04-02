package com.ms.awe.msshowtime.widget.gangedrecycler;

/**
 * Created By Musi
 * on 2019/3/27
 */
public abstract class BasePresenter {

    protected ViewCallBack mViewCallBack;

    void add(ViewCallBack viewCallBack) {
        this.mViewCallBack = viewCallBack;
    }

    void remove() {
        this.mViewCallBack = null;
    }

    protected abstract void getData();

}
