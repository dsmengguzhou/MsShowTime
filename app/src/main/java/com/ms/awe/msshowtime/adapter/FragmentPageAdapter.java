package com.ms.awe.msshowtime.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.awe.msshowtime.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Musi
 * on 2019/4/2
 */
public class FragmentPageAdapter extends android.support.v4.app.FragmentPagerAdapter{

    private List<Fragment> fragmentList;
    private String[] titles = {"首页","分类","发现","我的"};
    private Context context;

    public FragmentPageAdapter(FragmentManager fm,ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * 返回一个可用的View的个数
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        fragment = (Fragment) super.instantiateItem(container,position);
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
