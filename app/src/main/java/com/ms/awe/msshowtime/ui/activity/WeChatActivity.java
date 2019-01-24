package com.ms.awe.msshowtime.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ms.awe.msshowtime.R;

/**
 * Created By Musi
 * on 2019/1/24
 */
public class WeChatActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnWx5;
    private Button btnWx6;
    private ImageView ivWeChat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        btnWx5 = findViewById(R.id.btn_wx_521);
        btnWx6 = findViewById(R.id.btn_wx_600);
        ivWeChat = findViewById(R.id.iv_we_chat);
    }

    private void initData() {

    }

    private void initListener() {
        btnWx5.setOnClickListener(this);
        btnWx6.setOnClickListener(this);
        ivWeChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_wx_521:
                startActivity(new Intent(WeChatActivity.this, WxActivity.class));
                break;
            case R.id.btn_wx_600:
                startActivity(new Intent(WeChatActivity.this, NewWxActivity.class));
                break;
            case R.id.iv_we_chat:
                Toast toast = Toast.makeText(WeChatActivity.this,"面对疾风吧~",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                break;
            default:
                break;
        }
    }
}
