package com.ms.awe.msshowtime.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.widget.MyVideoView;

public class VideoViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private MyVideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        initView();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btn_start);
        videoView = (MyVideoView) findViewById(R.id.my_load_view);

        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                videoView.startTranglesAnimation();
                break;
            default:
                break;
        }
    }
}
