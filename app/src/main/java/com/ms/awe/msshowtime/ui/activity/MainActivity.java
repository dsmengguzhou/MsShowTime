package com.ms.awe.msshowtime.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.adapter.FragmentPageAdapter;
import com.ms.awe.msshowtime.ui.fragment.ClassifyFragment;
import com.ms.awe.msshowtime.ui.fragment.DiscoverFragment;
import com.ms.awe.msshowtime.ui.fragment.HomeFragment;
import com.ms.awe.msshowtime.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    public final static int MS_TAB_HOME = 0;
    public final static int MS_TAB_CLASSIFY = 1;
    public final static int MS_TAB_DISCOVER = 2;
    public final static int MS_TAB_MINE = 3;

    @BindView(R.id.vp_content)
    ViewPager viewPager;
    @BindView(R.id.tabs_layout)
    TabLayout tabsLayout;

    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> titleList;
    private FragmentPageAdapter mAdapter;

    private Unbinder unbinder;

//    @BindView(R.id.btn_material_design)
//    Button btnMdActivity;
//    @BindView(R.id.btn_widget_activity)
//    Button btnWidgetActivity;
//    @BindView(R.id.btn_we_chat)
//    Button btnWeChat;
//    @BindView(R.id.auto)
//    AutoCompleteTextView auto;
//    @BindView(R.id.btn_auto_confirm)
//    Button btnAutoConfirm;
//    @BindView(R.id.btn_drug_store)
//    Button btnDrugStore;
//
//    private BookPresenter mBookPresenter = new BookPresenter(this);

//
//    private String[] widgets = new String[]{"window", "progress", "copy", "ball", "triangle", "light", "listview"};
//    private ArrayAdapter<String> adapter;
//    static final int NOTIFICATION_ID = 0x123;
//    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //返回一个Unbinder对象
        unbinder = ButterKnife.bind(this);

        initData();

        init();
    }

    private void initData() {
        titleList = new ArrayList<>();
        titleList.add("首页");
        titleList.add("分类");
        titleList.add("发现");
        titleList.add("我的");
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ClassifyFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new MineFragment());
        //设置ViewPager的adapter
        mAdapter = new FragmentPageAdapter(getSupportFragmentManager(),titleList,fragmentList);
        viewPager.setAdapter(mAdapter);
        //TabLayout与ViewPager绑定
        tabsLayout.setupWithViewPager(viewPager);
        tabsLayout.setTabsFromPagerAdapter(mAdapter);
    }

    protected void init() {
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, widgets);
//        auto.setAdapter(adapter);
//
//        //获取系统的NotificationManager服务
//        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    //为发送通知的按钮的点击事件定义事件处理方法
//    public void send(View source) {
//        //创建一个启动其他Activity的Intent
//        Intent intent = new Intent(MainActivity.this, WidgetActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
//        Notification notify = new Notification.Builder(this)
//                .setAutoCancel(true)
//                .setTicker("有新消息")
//                .setSmallIcon(R.mipmap.icon_logo)
//                .setContentTitle("一条新通知")
//                .setContentText("行到水穷处，坐看云起时。")
//                .setWhen(System.currentTimeMillis())
//                .setContentIntent(pi)
//                .build();
//        nm.notify(NOTIFICATION_ID, notify);
//    }

    //为删除通知的按钮的点击事件定义事件处理方法
//    public void del(View v) {
//        //取消通知
//        nm.cancel(NOTIFICATION_ID);
//    }

//    public void simple(View source) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle("This is AlertDialog")
//                .setIcon(R.mipmap.icon_logo)
//                .setMessage("对话框的测试内容\n第二行内容");
//        //为AlertDialog.Builder添加"确定"按钮
//        setPositiveButton(builder);
//        //为AlertDialog.Builder添加"取消"按钮
//        setNegativeButton(builder).create().show();
//    }

//    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
//        //调用setPositiveButton方法添加"确定"按钮
//        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                startActivity(new Intent(MainActivity.this, MDActivity.class));
//            }
//        });
//    }
//
//    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
//        //调用setNegativeButton方法添加"取消"按钮
//        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    @OnClick({R.id.btn_material_design, R.id.btn_widget_activity, R.id.btn_we_chat, R.id.btn_auto_confirm,
//            R.id.btn_drug_store})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_material_design:
//                simple(btnMdActivity);
//                break;
//            case R.id.btn_widget_activity:
//                startActivity(new Intent(MainActivity.this, WidgetActivity.class));
//                break;
//            case R.id.btn_we_chat:
//                startActivity(new Intent(MainActivity.this, WeChatActivity.class));
//                break;
//            case R.id.btn_drug_store:
//                startActivity(new Intent(MainActivity.this, DrugActivity.class));
//                break;
//            case R.id.btn_auto_confirm:
//                pickModule(auto.getText().toString());
//                if (auto.getText().toString().isEmpty()) {
//                    send(btnAutoConfirm);
//                }
//                break;
//            default:
//                break;
//        }
//    }

//    private void pickModule(String module) {
//        Intent intent;
//        switch (module) {
//            case "window":
//                intent = new Intent(MainActivity.this, WindowActivity.class);
//                startActivity(intent);
//                break;
//            case "progress":
//                intent = new Intent(MainActivity.this, ProgressBarActivity.class);
//                startActivity(intent);
//                break;
//            case "copy":
//                intent = new Intent(MainActivity.this, CopyActivity.class);
//                startActivity(intent);
//                break;
//            case "ball":
//                intent = new Intent(MainActivity.this, BallActivity.class);
//                startActivity(intent);
//                break;
//            case "triangle":
//                intent = new Intent(MainActivity.this, VideoViewActivity.class);
//                startActivity(intent);
//                break;
//            case "light":
//                intent = new Intent(MainActivity.this, ColorActivity.class);
//                startActivity(intent);
//                break;
//            case "listview":
//                intent = new Intent(MainActivity.this, ListActivity.class);
//                startActivity(intent);
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
//        mBookPresenter.onStop();
    }
}
