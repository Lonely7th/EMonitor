package com.em.emonitor.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.em.emonitor.bean.EmEventBean;
import com.em.emonitor.bean.ItemClickBean;
import com.em.emonitor.bean.SingleClickBean;
import com.em.emonitor.callback.EmClickListener;
import com.em.emonitor.callback.EmEventListener;
import com.em.emonitor.callback.EmItemClickListener;
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

        EmBaseTask.getInstance().init(MainActivity.this);
        EmBaseTask.getInstance().setEmClickListener(new EmClickListener() {
            @Override
            public void onClick(SingleClickBean singleClickBean) {
                android.widget.Toast.makeText(MainActivity.this,"onClick",android.widget.Toast.LENGTH_SHORT).show();
            }
        });
        EmBaseTask.getInstance().setEmEventListener(new EmEventListener() {
            @Override
            public void EmOnResume(EmEventBean emEventBean) {
                android.widget.Toast.makeText(MainActivity.this,emEventBean.getClassName() + "EmOnResume",android.widget.Toast.LENGTH_SHORT).show();
            }

            @Override
            public void EmOnPause(EmEventBean emEventBean) {
                android.widget.Toast.makeText(MainActivity.this,emEventBean.getClassName() + "EmOnPause",android.widget.Toast.LENGTH_SHORT).show();
            }
        });
        EmBaseTask.getInstance().setEmItemClickListener(new EmItemClickListener() {
            @Override
            public void onItemClick(ItemClickBean itemClickBean) {
                android.widget.Toast.makeText(MainActivity.this,"onItemClick",android.widget.Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(this,TaskActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_count:
                break;
        }
    }
}
