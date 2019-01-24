package com.ms.awe.msshowtime.listener;


/**
 * @author Libra
 * @date 2018/12/6  权限申请回调的接口
 *
 */


public interface PermissionListener {

    void onGranted();

    void onDenied(String[] deniedPermissions);
}
