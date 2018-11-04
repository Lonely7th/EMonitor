package com.em.emonitor.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private List<Map<String,Object>> mapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        findViewById(R.id.btn_content).setOnClickListener(this);
        findViewById(R.id.btn_content2).setOnClickListener(this);
        findViewById(R.id.btn_content3).setOnClickListener(this);
        findViewById(R.id.btn_content4).setOnClickListener(this);
        findViewById(R.id.btn_content5).setOnClickListener(this);
        ListView listView = findViewById(R.id.em_list_item);
        for(int i = 0;i < 16;i++){
            Map<String,Object> items = new HashMap<>();
            items.put("content", "task"+i);
            mapList.add(items);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(TaskActivity.this, mapList,R.layout.item_task,
                new String[]{"content"},new int[]{R.id.tv_item});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.em_list_item:
                break;
        }
    }
}
