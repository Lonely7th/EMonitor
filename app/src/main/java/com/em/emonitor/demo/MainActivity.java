package com.em.emonitor.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.em.emonitor.bean.SingleClickBean;
import com.em.emonitor.callback.EmClickListener;
import com.em.emonitor.core.EmBaseTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvCount;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCount = findViewById(R.id.tv_count);
        tvCount.setOnClickListener(this);

        EmBaseTask.getInstance().setEmClickListener(new EmClickListener() {
            @Override
            public void onClick(SingleClickBean singleClickBean) {
                android.widget.Toast.makeText(MainActivity.this,singleClickBean.getView().getId()+"",android.widget.Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(this,TaskActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_count:
                //业务逻辑
                break;
        }
    }
}
