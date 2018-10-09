package com.ms.awe.msshowtime;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ms.awe.msshowtime.activity.CopyActivity;
import com.ms.awe.msshowtime.activity.ProgressBarActivity;
import com.ms.awe.msshowtime.activity.WindowActivity;
import com.ms.awe.msshowtime.activity.WxActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToWindowAct;
    private Button btnToProgressAct;
    private Button btnToCopyAct;
    private Button btnToWx;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        calculate();
    }



    private void initViews() {
        btnToWindowAct = findViewById(R.id.btn_window_act);
        btnToProgressAct = findViewById(R.id.btn_progress_bar);
        btnToCopyAct = findViewById(R.id.btn_copy_activity);
        btnToWx = findViewById(R.id.btn_wx_activity);

        btnToWindowAct.setOnClickListener(this);
        btnToProgressAct.setOnClickListener(this);
        btnToCopyAct.setOnClickListener(this);
        btnToWx.setOnClickListener(this);

        tvInfo = findViewById(R.id.tv_info);
    }

    private void calculate() {
        StringBuilder strBuilder = new StringBuilder();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memClass = activityManager.getMemoryClass();    //以兆为单位
        int LargememClass = activityManager.getLargeMemoryClass();  //以M为单位
        strBuilder.append("memClass:" + memClass + "\n");
        strBuilder.append("LargememClass:" + LargememClass + "\n");

        tvInfo.setText(strBuilder.toString());
        Log.d("ms", strBuilder.toString());
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
            default:
                break;
        }
    }
}
