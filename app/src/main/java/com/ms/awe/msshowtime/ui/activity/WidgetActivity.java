package com.ms.awe.msshowtime.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.widget.FlodableButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created By Musi
 * on 2019/1/24
 */
public class WidgetActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_window_activity)
    Button btnToWindow;
    @BindView(R.id.btn_progress_bar)
    Button btnProgressBar;
    @BindView(R.id.btn_copy_activity)
    Button btnCopy;
    @BindView(R.id.btn_ball_activity)
    Button btnToBall;
    @BindView(R.id.btn_start_animator)
    Button btnStartAnimator;
    @BindView(R.id.btn_color_activity)
    Button btnColorActivity;
    @BindView(R.id.btn_list_activity)
    Button btnListActivity;
    @BindView(R.id.btn_back)
    RelativeLayout btnBack;

    private FlodableButton flodableButton;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        unbinder = ButterKnife.bind(this);
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
                Toast.makeText(WidgetActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.btn_window_activity, R.id.btn_progress_bar, R.id.btn_copy_activity, R.id.btn_ball_activity,
            R.id.btn_start_animator, R.id.btn_color_activity,R.id.btn_list_activity,R.id.btn_back})
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
            case R.id.btn_ball_activity:
                startActivity(new Intent(WidgetActivity.this, BallActivity.class));
                break;
            case R.id.btn_color_activity:
                startActivity(new Intent(WidgetActivity.this, ColorActivity.class));
                break;
            case R.id.btn_list_activity:
                startActivity(new Intent(WidgetActivity.this, ListActivity.class));
                break;
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
