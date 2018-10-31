package com.em.emonitor.core;

import com.em.emonitor.bean.StatisticsBean;
import com.em.emonitor.callback.EmClickListener;
import com.em.emonitor.callback.EmEventListener;
import com.em.emonitor.callback.EmItemClickListener;
import com.em.emonitor.callback.StatisticsListener;
import com.em.emonitor.utils.StatisticsUtil;

/**
 * Time ： 2018/10/24 .
 * Author ： JN Zhang .
 * Description ：接口管理类 .
 */
public class EmBaseTask {
    private static EmBaseTask emBaseTask = null;

    private EmClickListener emClickListener;
    private EmItemClickListener emItemClickListener;
    private EmEventListener emEventListener;
    private StatisticsListener statisticsListener;

    public EmBaseTask(){
        initStatistics();
    }

    public static synchronized EmBaseTask getInstance(){
        if(emBaseTask == null){
            emBaseTask = new EmBaseTask();
        }
        return emBaseTask;
    }

    public EmBaseTask setEmClickListener(EmClickListener emClickListener){
        if(null == emClickListener){
            throw new NullPointerException();
        }
        this.emClickListener = emClickListener;
        return emBaseTask;
    }

    public EmBaseTask setEmItemClickListener(EmItemClickListener emItemClickListener){
        if(null == emItemClickListener){
            throw new NullPointerException();
        }
        this.emItemClickListener = emItemClickListener;
        return emBaseTask;
    }

    public EmBaseTask setEmEventListener(EmEventListener emEventListener){
        if(null == emEventListener){
            throw new NullPointerException();
        }
        this.emEventListener = emEventListener;
        return emBaseTask;
    }

    public EmClickListener getEmClickListener(){
        return emClickListener;
    }

    public EmItemClickListener getEmItemClickListener(){
        return emItemClickListener;
    }

    public EmEventListener getEmEventListener(){
        return emEventListener;
    }

    public StatisticsListener getStatisticsListener(){
        return statisticsListener;
    }

    /**
     * 初始化统计模块
     */
    private void initStatistics(){
        statisticsListener = new StatisticsListener() {
            @Override
            public void onStatistics(StatisticsBean statisticsBean) {
                //统计用户行为
                StatisticsUtil.statistics(statisticsBean);
            }
        };
    }
}
