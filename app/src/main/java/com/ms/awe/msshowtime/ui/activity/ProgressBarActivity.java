package com.ms.awe.msshowtime.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.widget.HorizontalProgressbarWithProgress;
import com.ms.awe.msshowtime.widget.RoundProgressBarWithProgress;

public class ProgressBarActivity extends AppCompatActivity{

    private static final int MSG_UPDATE = 0X110;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int progress = mProgress.getProgress();
            mProgress.setProgress(++progress);
            mRoundProgress.setProgress(++progress);
            if (progress >= 100){
                mHandler.removeMessages(MSG_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE,50);
        }
    };

    private HorizontalProgressbarWithProgress mProgress;

    private RoundProgressBarWithProgress mRoundProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        initViews();

        setListener();

        mHandler.sendEmptyMessage(MSG_UPDATE);
    }

    private void initViews() {
        mProgress = findViewById(R.id.id_progress01);
        mRoundProgress = findViewById(R.id.id_progress02);
    }

    private void setListener() {

    }
}
