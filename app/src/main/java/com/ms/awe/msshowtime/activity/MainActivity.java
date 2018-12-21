package com.ms.awe.msshowtime.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.manager.ThreadPoolManager;
import com.ms.awe.msshowtime.widget.FlodableButton;
import com.ms.awe.msshowtime.widget.MyVideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToWindowAct;
    private Button btnToProgressAct;
    private Button btnToCopyAct;
    private Button btnToWx;
    private Button btnToWx6;
    private Button btnStartAnimator;
    private FlodableButton flodableButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        calculate();

        //线程池的案例使用
//        for (int i = 0; i < 9; i++) {
//            ThreadPoolManager.getInstance().execute(new DownloadTask(i));
//        }
    }

//    //模仿下载任务实现Runnable
//    class DownloadTask implements Runnable {
//        private int num;
//
//        public DownloadTask(int num) {
//            super();
//            this.num = num;
//            Log.d("Java", "task-" + num + "等待中...");
//        }
//
//        @Override
//        public void run() {
//            Log.d("Java","task -" + num + "开始执行了...开始执行了...");
//            SystemClock.sleep(5000);    //模拟延时执行的时间
//            Log.e("Java","task -"+num+"结束了");
//        }
//    }

    private void initViews() {
        btnToWindowAct = findViewById(R.id.btn_window_act);
        btnToProgressAct = findViewById(R.id.btn_progress_bar);
        btnToCopyAct = findViewById(R.id.btn_copy_activity);
        btnToWx = findViewById(R.id.btn_wx_activity);
        btnToWx6 = findViewById(R.id.btn_wx_activity_6);
        btnStartAnimator = findViewById(R.id.btn_start_animator);
        flodableButton = findViewById(R.id.flodable_button);
        flodableButton.setOnClickListener(new FlodableButton.OnClickListener() {
            @Override
            public void onClick(FlodableButton sfb) {
                flodableButton.startScroll();
            }
        });
        flodableButton.setFoldListener(new FlodableButton.FoldListener() {
            @Override
            public void onFold(boolean isIncrease, FlodableButton sfb) {
                String text = isIncrease ? "展开了" : "折叠了";
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        btnToWindowAct.setOnClickListener(this);
        btnToProgressAct.setOnClickListener(this);
        btnToCopyAct.setOnClickListener(this);
        btnToWx.setOnClickListener(this);
        btnToWx6.setOnClickListener(this);
        btnStartAnimator.setOnClickListener(this);
    }

    private void calculate() {
        StringBuilder strBuilder = new StringBuilder();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memClass = activityManager.getMemoryClass();    //以兆为单位
        int LargememClass = activityManager.getLargeMemoryClass();  //以M为单位
        strBuilder.append("memClass:" + memClass + "\n");
        strBuilder.append("LargememClass:" + LargememClass + "\n");

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_window_act:
                intent = new Intent(MainActivity.this, WindowActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_progress_bar:
                intent = new Intent(MainActivity.this, ProgressBarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_copy_activity:
                intent = new Intent(MainActivity.this, CopyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wx_activity:
                intent = new Intent(MainActivity.this, WxActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wx_activity_6:
                intent = new Intent(MainActivity.this, NewWxActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_start_animator:
                intent = new Intent(MainActivity.this, VideoViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
