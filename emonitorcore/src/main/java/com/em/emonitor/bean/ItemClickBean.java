package com.em.emonitor.bean;

import android.view.View;

/**
 * Time ： 2018/10/25 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class ItemClickBean {
    private View view;
    private int viewIndex;
    private String className;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getViewIndex() {
        return viewIndex;
    }

    public void setViewIndex(int viewIndex) {
        this.viewIndex = viewIndex;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
