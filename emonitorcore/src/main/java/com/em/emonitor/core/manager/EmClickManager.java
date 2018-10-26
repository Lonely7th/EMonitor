package com.em.emonitor.core.manager;

import android.view.View;

import com.em.emonitor.bean.SingleClickBean;
import com.em.emonitor.core.EmBaseTask;

/**
 * Time ： 2018/10/25 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class EmClickManager extends EmBaseManager{

    /**
     * 点击事件监听器
     * @param name 控件所属类名
     * @param v 被点击的控件
     */
    public static void EMonitorClick(String name, View v){
        if(EmBaseTask.getInstance().getEmClickListener() != null){
            SingleClickBean singleClickBean = new SingleClickBean();
            singleClickBean.setClassName(name);
            singleClickBean.setView(v);
            EmBaseTask.getInstance().getEmClickListener().onClick(singleClickBean);
        }
    }

    public static void EMonitorItemClick(){

    }
}
