package com.ms.awe.msshowtime.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.fragment.ChatMainTabFragment;
import com.ms.awe.msshowtime.fragment.ContactMainTabFragment;
import com.ms.awe.msshowtime.fragment.FriendMainTabFragment;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.QBadgeView;

public class WxActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private TextView mChatTextView;
    private TextView mFriendTextView;
    private TextView mContactTextView;

    private QBadgeView mBadgeView;

    private int mScreen1_3;
    private ImageView mTabline;
    private ImageView ivTopWeChat;

    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx);

        initTabline();
        initView();
        initData();
        initListener();
    }

    private void initView() {

        mChatTextView = findViewById(R.id.id_tv_chat);
        mFriendTextView = findViewById(R.id.id_tv_friend);
        mContactTextView = findViewById(R.id.id_tv_contact);
        ivTopWeChat = findViewById(R.id.iv_top_icon_we_chat);

        mViewPager = findViewById(R.id.id_viewpager);
        mDatas = new ArrayList<Fragment>();
    }

    private void initData() {
        ChatMainTabFragment tab01 = new ChatMainTabFragment();
        FriendMainTabFragment tab02 = new FriendMainTabFragment();
        ContactMainTabFragment tab03 = new ContactMainTabFragment();

        mDatas.add(tab01);
        mDatas.add(tab02);
        mDatas.add(tab03);
    }

    private void initListener() {
        ivTopWeChat.setOnClickListener(this);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }

            @Override
            public int getCount() {
                return mDatas.size();
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();
                if (mCurrentPageIndex == 0 && position == 0) {
                    //0->1
                    lp.leftMargin = (int) (positionOffset * mScreen1_3
                            + mCurrentPageIndex * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 0) {
                    //1->0
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3
                            + (positionOffset - 1) * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 1) {
                    //1->2
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3
                            + positionOffset * mScreen1_3);
                } else if (mCurrentPageIndex == 2 && position == 1) {
                    //2->1
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3
                            + (positionOffset - 1) * mScreen1_3);
                }
                mTabline.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mBadgeView = (QBadgeView) new QBadgeView(WxActivity.this)
                                .bindTarget(mChatTextView)
                                .setBadgeGravity(Gravity.CENTER | Gravity.END)
                                .setBadgeNumber(7);

                        mChatTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        mFriendTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 2:
                        mContactTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    default:
                        break;
                }
                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabline() {
        mTabline = findViewById(R.id.id_iv_tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
    }

    private void resetTextView() {
        mChatTextView.setTextColor(Color.BLACK);
        mFriendTextView.setTextColor(Color.BLACK);
        mContactTextView.setTextColor(Color.BLACK);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_icon_we_chat:
                finish();
                break;
            default:
                break;
        }
    }
}
