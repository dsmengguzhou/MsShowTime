package com.ms.awe.msshowtime.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.ui.fragment.TabFragment;
import com.ms.awe.msshowtime.widget.ChangeColorIconWithText;

import java.util.ArrayList;
import java.util.List;

public class NewWxActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private String[] mTitles = new String[]{"First Fragment!", "Second Fragment!", "Third Fragment!", "Fourth Fragment!"};
    private FragmentPagerAdapter mAdapter;
    private ImageView ivTopWeChat;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_new);

        initView();
        initDatas();

        mViewPager.setAdapter(mAdapter);
    }

    private void initView() {
        mViewPager = findViewById(R.id.id_viewpager_new);
        ivTopWeChat = findViewById(R.id.iv_top_icon_we_chat);
        ivTopWeChat.setOnClickListener(this);

        ChangeColorIconWithText one = findViewById(R.id.id_indicator_one);
        ChangeColorIconWithText two = findViewById(R.id.id_indicator_two);
        ChangeColorIconWithText three = findViewById(R.id.id_indicator_three);
        ChangeColorIconWithText four = findViewById(R.id.id_indicator_four);

        mTabIndicators.add(one);
        mTabIndicators.add(two);
        mTabIndicators.add(three);
        mTabIndicators.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(this);

        one.setIconAlpha(1.0f);
    }

    private void initDatas() {
        for (String title : mTitles) {
            TabFragment tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.TITLE, title);
            tabFragment.setArguments(bundle);
            mTabs.add(tabFragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        clickTab(view);
        switch (view.getId()) {
            case R.id.iv_top_icon_we_chat:
                finish();
                break;
            default:
                break;
        }
    }

    private void clickTab(View view) {
        resetOtherTabs();
        switch (view.getId()) {
            case R.id.id_indicator_one:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
