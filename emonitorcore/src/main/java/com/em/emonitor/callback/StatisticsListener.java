package com.em.emonitor.callback;

import com.em.emonitor.bean.StatisticsBean;

/**
 * Time ： 2018/10/31 .
 * Author ： JN Zhang .
 * Description ： .
 */
public interface StatisticsListener {
    void onStatistics(StatisticsBean statisticsBean);
}
