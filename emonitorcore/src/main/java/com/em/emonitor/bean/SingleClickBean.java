package com.em.emonitor.bean;

import android.view.View;

/**
 * Time ： 2018/10/25 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class SingleClickBean {
    private View view;
    private String className;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
