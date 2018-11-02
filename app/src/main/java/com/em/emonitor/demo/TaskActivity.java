package com.em.emonitor.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        btnContent = findViewById(R.id.btn_content);
        findViewById(R.id.btn_content).setOnClickListener(this);
        findViewById(R.id.btn_content2).setOnClickListener(this);
        findViewById(R.id.btn_content3).setOnClickListener(this);
        findViewById(R.id.btn_content4).setOnClickListener(this);
        findViewById(R.id.btn_content5).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_content:
                break;
            case R.id.btn_content2:
                break;
            case R.id.btn_content3:
                break;
            case R.id.btn_content4:
                break;
            case R.id.btn_content5:
                break;
        }
    }
}
