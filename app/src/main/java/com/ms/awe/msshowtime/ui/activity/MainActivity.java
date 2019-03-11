package com.ms.awe.msshowtime.ui.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.mvp.model.entity.Book;
import com.ms.awe.msshowtime.mvp.presenter.BookPresenter;
import com.ms.awe.msshowtime.mvp.view.BookView;
import com.ms.awe.msshowtime.widget.guide.HoleBean;
import com.ms.awe.msshowtime.widget.guide.NewbieGuideManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ll_item_book)
    RelativeLayout llItemBook;
    @BindView(R.id.btn_material_design)
    Button btnMdActivity;
    @BindView(R.id.btn_widget_activity)
    Button btnWidgetActivity;
    @BindView(R.id.btn_we_chat)
    Button btnWeChat;
    @BindView(R.id.btn_retrofit_request)
    Button btnRetrofitRequest;
    @BindView(R.id.tv_book_title)
    TextView tvBookTitle;
    @BindView(R.id.tv_book_author)
    TextView tvBookAuthor;
    @BindView(R.id.tv_book_publisher)
    TextView tvBookPublisher;
    @BindView(R.id.iv_left_img)
    ImageView ivLeftImg;
    @BindView(R.id.iv_close_item)
    ImageView ivCloseItem;
    @BindView(R.id.chronometer)
    Chronometer chronometer;
    @BindView(R.id.btn_cm_start)
    Button btnCmStart;
    @BindView(R.id.auto)
    AutoCompleteTextView auto;
    @BindView(R.id.btn_auto_confirm)
    Button btnAutoConfirm;

    private BookPresenter mBookPresenter = new BookPresenter(this);
    private Unbinder unbinder;

    private String[] widgets = new String[]{"window", "progress", "copy", "ball", "triangle", "light", "listview"};
    private ArrayAdapter<String> adapter;
    static final int NOTIFICATION_ID = 0x123;
    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);              //返回一个Unbinder对象

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, widgets);
        auto.setAdapter(adapter);

        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime() - chronometer.getBase() > 20 * 1000) {
                    chronometer.stop();
                    btnCmStart.setEnabled(true);
                }
            }
        });
        //获取系统的NotificationManager服务
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (NewbieGuideManager.isNeverShowed(this, NewbieGuideManager.TYPE_MS)) {
            new NewbieGuideManager(this, NewbieGuideManager.TYPE_MS).addView(btnMdActivity, HoleBean.TYPE_RECTANGLE).show();
        }
    }

    //为发送通知的按钮的点击事件定义事件处理方法
    public void send(View source) {
        //创建一个启动其他Activity的Intent
        Intent intent = new Intent(MainActivity.this, WidgetActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Notification notify = new Notification.Builder(this)
                .setAutoCancel(true)
                .setTicker("有新消息")
                .setSmallIcon(R.mipmap.icon_logo)
                .setContentTitle("一条新通知")
                .setContentText("行到水穷处，坐看云起时。")
//                .setDefaults(Notification.DEFAULT_SOUND || Notification.DEFAULT_LIGHTS)
//                .setSound(Uri.parse())
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pi)
                .build();
        nm.notify(NOTIFICATION_ID, notify);
    }

    //为删除通知的按钮的点击事件定义事件处理方法
    public void del(View v) {
        //取消通知
        nm.cancel(NOTIFICATION_ID);
    }

    public void simple(View source) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("This is AlertDialog")
                .setIcon(R.mipmap.icon_logo)
                .setMessage("对话框的测试内容\n第二行内容");
        //为AlertDialog.Builder添加"确定"按钮
        setPositiveButton(builder);
        //为AlertDialog.Builder添加"取消"按钮
        setNegativeButton(builder).create().show();
    }

    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
        //调用setPositiveButton方法添加"确定"按钮
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
        //调用setNegativeButton方法添加"取消"按钮
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.btn_material_design, R.id.btn_widget_activity, R.id.btn_we_chat, R.id.btn_auto_confirm,
            R.id.btn_retrofit_request, R.id.ll_item_book, R.id.iv_close_item, R.id.btn_cm_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_material_design:
//                startActivity(new Intent(MainActivity.this, MDActivity.class));
                simple(btnMdActivity);
                break;
            case R.id.btn_widget_activity:
                startActivity(new Intent(MainActivity.this, WidgetActivity.class));
                break;
            case R.id.btn_we_chat:
                startActivity(new Intent(MainActivity.this, WeChatActivity.class));
                break;
            case R.id.btn_retrofit_request:
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
                llItemBook.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_item_book:
                Toast.makeText(MainActivity.this, "想看书吗小老弟", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_close_item:
                llItemBook.setVisibility(View.GONE);
                break;
            case R.id.btn_cm_start:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                btnCmStart.setEnabled(false);
                break;
            case R.id.btn_auto_confirm:
                pickModule(auto.getText().toString());
                if (auto.getText().toString().isEmpty()) {
                    send(btnAutoConfirm);
                }
                break;
            default:
                break;
        }
    }

    private void pickModule(String module) {
        Intent intent;
        switch (module) {
            case "window":
                intent = new Intent(MainActivity.this, WindowActivity.class);
                startActivity(intent);
                break;
            case "progress":
                intent = new Intent(MainActivity.this, ProgressBarActivity.class);
                startActivity(intent);
                break;
            case "copy":
                intent = new Intent(MainActivity.this, CopyActivity.class);
                startActivity(intent);
                break;
            case "ball":
                intent = new Intent(MainActivity.this, BallActivity.class);
                startActivity(intent);
                break;
            case "triangle":
                intent = new Intent(MainActivity.this, VideoViewActivity.class);
                startActivity(intent);
                break;
            case "light":
                intent = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent);
                break;
            case "listview":
                intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            tvBookTitle.setText(Html.fromHtml(getString(R.string.main_book_title, mBook.getBooks().get(0).getTitle())));
            tvBookAuthor.setText(Html.fromHtml(getString(R.string.main_book_author, mBook.getBooks().get(0).getAuthor())));
            tvBookPublisher.setText(Html.fromHtml(getString(R.string.main_book_publisher, mBook.getBooks().get(0).getPublisher())));
            Glide.with(MainActivity.this)
                    .load(mBook.getBooks().get(0).getImages().getLarge())
                    .override(600, 390)
                    .into(ivLeftImg);

        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        mBookPresenter.onStop();
    }
}
