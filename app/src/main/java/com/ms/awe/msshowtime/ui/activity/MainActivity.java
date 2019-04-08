package com.ms.awe.msshowtime.ui.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.adapter.FragmentPageAdapter;
import com.ms.awe.msshowtime.ui.fragment.ClassifyFragment;
import com.ms.awe.msshowtime.ui.fragment.DiscoverFragment;
import com.ms.awe.msshowtime.ui.fragment.HomeFragment;
import com.ms.awe.msshowtime.ui.fragment.MineFragment;
import com.ms.awe.msshowtime.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {
    @BindView(R.id.vp_content)
    ViewPager viewPager;
    @BindView(R.id.tabs_layout)
    TabLayout tabsLayout;

    private ArrayList<Fragment> fragmentList;
    private List<String> tabTitles;
    private FragmentPageAdapter mAdapter;

    private Unbinder unbinder;
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //返回一个Unbinder对象
        unbinder = ButterKnife.bind(this);

        initData();
        initTabs();
    }

    private void initTabs() {
        tabsLayout.setTabMode(TabLayout.MODE_FIXED);
        tabsLayout.setSelectedTabIndicator(0);
        ViewCompat.setElevation(tabsLayout, 10);
        //TabLayout与ViewPager绑定
        tabsLayout.setupWithViewPager(viewPager);

        TypedArray ta = this.getResources().obtainTypedArray(R.array.selector_tabs);
        int[] resIds = new int[4];

        for (int i = 0; i < fragmentList.size(); i++) {
            TabLayout.Tab itemTab = tabsLayout.getTabAt(i);
            if (itemTab != null) {
                resIds[i] = ta.getResourceId(i,0);
                itemTab.setCustomView(R.layout.item_tab_layout_custom);
                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_tab_item);
                ImageView itemIv = (ImageView)itemTab.getCustomView().findViewById(R.id.iv_tab_item);
                itemTv.setText(tabTitles.get(i));
                itemIv.setImageResource(resIds[i]);
            }
        }
        tabsLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ClassifyFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new MineFragment());
        tabTitles = new ArrayList<>();
        tabTitles.add("推荐");
        tabTitles.add("分类");
        tabTitles.add("发现");
        tabTitles.add("我的");
        //设置ViewPager的adapter
        mAdapter = new FragmentPageAdapter(getSupportFragmentManager(), fragmentList, tabTitles);
        viewPager.setAdapter(mAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 3000) {
                System.exit(0);
            } else {
                ToastUtil.showShort(MainActivity.this, "再一次送飞机票");
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
