package com.ms.awe.msshowtime.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Musi
 * on 2019/4/2
 */
public class FragmentPageAdapter extends android.support.v4.app.FragmentPagerAdapter{

    private List<Fragment> fragmentList;
    private List<String> tabTitles;

    public FragmentPageAdapter(FragmentManager fm,ArrayList<Fragment> fragmentList,List<String> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabTitles = titles;
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



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
