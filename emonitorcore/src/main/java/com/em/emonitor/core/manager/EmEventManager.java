package com.em.emonitor.core.manager;

import com.em.emonitor.bean.EmEventBean;
import com.em.emonitor.bean.StatisticsBean;
import com.em.emonitor.core.ContentKey;
import com.em.emonitor.core.EmBaseTask;
import com.em.emonitor.utils.ViewIdUtil;

/**
 * Time ： 2018/10/25 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class EmEventManager extends EmBaseManager{

    /**
     * 页面跳转状态
     * @param className 类名
     * @param event 事件  0.onResume  1.onPause
     */
    public static void onEvent(String className, int event){
        if(EmBaseTask.getInstance().getEmEventListener() != null){
            EmEventBean emEventBean = new EmEventBean();
            emEventBean.setClassName(className);
            emEventBean.setStatus(event);
            switch (event){
                case ContentKey.EmOnResume:
                    EmBaseTask.getInstance().getEmEventListener().EmOnResume(emEventBean);
                    break;
                case ContentKey.EmOnPause:
                    EmBaseTask.getInstance().getEmEventListener().EmOnPause(emEventBean);
                    break;
            }

            //提交到统计模块
            StatisticsBean statisticsBean = new StatisticsBean();
            statisticsBean.setClassName(className);
            statisticsBean.setEvent(event);
            statisticsBean.setViewId("");
            statisticsBean.setCurrentTime(System.currentTimeMillis());
            EmBaseTask.getInstance().getStatisticsListener().onStatistics(statisticsBean);
        }
    }

}
