package com.em.emonitor.core.manager;

import android.view.View;

import com.em.emonitor.bean.ItemClickBean;
import com.em.emonitor.bean.SingleClickBean;
import com.em.emonitor.bean.StatisticsBean;
import com.em.emonitor.core.ContentKey;
import com.em.emonitor.core.EmBaseTask;
import com.em.emonitor.utils.ViewIdUtil;

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

            //提交到统计模块
            StatisticsBean statisticsBean = new StatisticsBean();
            statisticsBean.setClassName(name);
            statisticsBean.setEvent(ContentKey.EmOnClick);
            statisticsBean.setViewId(ViewIdUtil.getId(name,v));
            statisticsBean.setCurrentTime(System.currentTimeMillis());
            EmBaseTask.getInstance().getStatisticsListener().onStatistics(statisticsBean);
        }
    }

    /**
     * Item点击事件监听器
     * @param name 控件所属类名
     * @param v 被点击的控件
     * @param index 被点击项的索引
     */
    public static void EMonitorItemClick(String name, View v, int index){
        if(EmBaseTask.getInstance().getEmItemClickListener() != null){
            ItemClickBean itemClickBean = new ItemClickBean();
            itemClickBean.setClassName(name);
            itemClickBean.setView(v);
            itemClickBean.setViewIndex(index);
            EmBaseTask.getInstance().getEmItemClickListener().onItemClick(itemClickBean);

            //提交到统计模块
            StatisticsBean statisticsBean = new StatisticsBean();
            statisticsBean.setClassName(name);
            statisticsBean.setEvent(ContentKey.EmOnItemClick);
            statisticsBean.setViewId(ViewIdUtil.getId(name,v));
            statisticsBean.setIndex(index);
            statisticsBean.setCurrentTime(System.currentTimeMillis());
            EmBaseTask.getInstance().getStatisticsListener().onStatistics(statisticsBean);
        }
    }
}
