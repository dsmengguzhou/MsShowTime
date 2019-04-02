package com.ms.awe.msshowtime.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.awe.msshowtime.R;

/**
 * Created By Musi
 * on 2019/4/2
 */
public class DiscoverFragment extends Fragment{

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //避免重复创建
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_discover, container, false);
        }
        return rootView;
    }

    public static HomeFragment newInstance(String extra) {
        //利用Bundle传值
        Bundle bundle = new Bundle();
        bundle.putString("Home",extra);
        //实例化
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
