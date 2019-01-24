package com.ms.awe.msshowtime.ui.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.widget.FlodableButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnWidgetActivity;
    private Button btnToWeChat;
    private Button btnStartAnimator;
    private FlodableButton flodableButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
        initListener();
        calculate();

    }

    private void initViews() {

        btnWidgetActivity = findViewById(R.id.btn_widget_activity);
        btnToWeChat = findViewById(R.id.btn_we_chat);
        btnStartAnimator = findViewById(R.id.btn_start_animator);
        flodableButton = findViewById(R.id.flodable_button);
    }

    private void initData() {

    }

    private void initListener() {
        btnWidgetActivity.setOnClickListener(this);
        btnToWeChat.setOnClickListener(this);
        btnStartAnimator.setOnClickListener(this);

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
        switch (view.getId()) {
            case R.id.btn_widget_activity:
                startActivity(new Intent(MainActivity.this, WidgetActivity.class));
                break;
            case R.id.btn_we_chat:
                startActivity(new Intent(MainActivity.this, WeChatActivity.class));
                break;
            case R.id.btn_start_animator:
                startActivity(new Intent(MainActivity.this, VideoViewActivity.class));
                break;
            default:
                break;
        }
    }
}
