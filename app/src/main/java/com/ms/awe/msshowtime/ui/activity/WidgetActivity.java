package com.ms.awe.msshowtime.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ms.awe.msshowtime.R;

/**
 * Created By Musi
 * on 2019/1/24
 */
public class WidgetActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToWindowAct;
    private Button btnToProgressAct;
    private Button btnToCopyAct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        initViews();
        initListener();
    }

    private void initViews() {
        btnToWindowAct = findViewById(R.id.btn_window_activity);
        btnToProgressAct = findViewById(R.id.btn_progress_bar);
        btnToCopyAct = findViewById(R.id.btn_copy_activity);
    }

    private void initListener() {
        btnToWindowAct.setOnClickListener(this);
        btnToProgressAct.setOnClickListener(this);
        btnToCopyAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_window_activity:
                startActivity(new Intent(WidgetActivity.this, WindowActivity.class));
                break;
            case R.id.btn_progress_bar:
                startActivity(new Intent(WidgetActivity.this, ProgressBarActivity.class));
                break;
            case R.id.btn_copy_activity:
                startActivity(new Intent(WidgetActivity.this, CopyActivity.class));
                break;
            default:
                break;
        }
    }
}
