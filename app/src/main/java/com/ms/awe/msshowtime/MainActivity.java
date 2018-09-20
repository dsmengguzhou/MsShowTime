package com.ms.awe.msshowtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ms.awe.msshowtime.activity.ProgressBarActivity;
import com.ms.awe.msshowtime.activity.WindowActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnToWindowAct;
    private Button btnToProgressAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setListener();
    }

    private void setListener() {
        btnToWindowAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WindowActivity.class);
                startActivity(intent);
            }
        });
        btnToProgressAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProgressBarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        btnToWindowAct = findViewById(R.id.btn_window_act);
        btnToProgressAct = findViewById(R.id.btn_progress_bar);
    }
}
