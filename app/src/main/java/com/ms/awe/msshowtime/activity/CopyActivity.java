package com.ms.awe.msshowtime.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.awe.msshowtime.R;

public class CopyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCopyTxt01;
    private Button btnCopyTxt02;
    private TextView tvMsg01;
    private TextView tvMsg02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy);

        initView();
    }

    private void initView() {
        btnCopyTxt01 = findViewById(R.id.btn_copy_txt01);
        btnCopyTxt01.setOnClickListener(this);
        btnCopyTxt02 = findViewById(R.id.btn_copy_txt02);
        btnCopyTxt02.setOnClickListener(this);

        tvMsg01 = findViewById(R.id.tvMsg01);
        tvMsg02 = findViewById(R.id.tvMsg02);
    }

    @Override
    public void onClick(View view) {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        switch (view.getId()) {

            case R.id.btn_copy_txt01:
                //把文本内容放到系统剪切板里
                cm.setText(tvMsg01.getText());
                Toast.makeText(this, "复制成功(关雎)", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_copy_txt02:
                cm.setText(tvMsg02.getText());
                Toast.makeText(this, "复制成功(草)", Toast.LENGTH_LONG).show();
            default:

                break;
        }
    }
}
