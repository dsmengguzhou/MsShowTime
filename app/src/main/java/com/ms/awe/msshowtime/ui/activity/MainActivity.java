package com.ms.awe.msshowtime.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.adapter.FragmentPageAdapter;
import com.ms.awe.msshowtime.ui.fragment.ClassifyFragment;
import com.ms.awe.msshowtime.ui.fragment.DiscoverFragment;
import com.ms.awe.msshowtime.ui.fragment.HomeFragment;
import com.ms.awe.msshowtime.ui.fragment.MineFragment;
import com.ms.awe.msshowtime.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.vp_content)
    ViewPager viewPager;
    @BindView(R.id.tabs_layout)
    TabLayout tabsLayout;

    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> titleList;
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
    }

    private void initData() {
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ClassifyFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new MineFragment());
        //设置ViewPager的adapter
        mAdapter = new FragmentPageAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(mAdapter);
        //TabLayout与ViewPager绑定
        tabsLayout.setupWithViewPager(viewPager);
        tabsLayout.setTabsFromPagerAdapter(mAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (secondTime - firstTime < 3000){
                System.exit(0);
            }else {
                ToastUtil.showShort(MainActivity.this,"再一次送飞机票");
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
