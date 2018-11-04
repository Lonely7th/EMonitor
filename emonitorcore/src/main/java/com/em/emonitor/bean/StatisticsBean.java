package com.em.emonitor.bean;

/**
 * Time ： 2018/10/31 .
 * Author ： JN Zhang .
 * Description ：数据统计模型 .
 */
public class StatisticsBean {
    private String className;
    private int event;//0.onResume  1.onPause  2.onClick  3.onItemClick
    private String viewId;
    private long currentTime;

    private int index;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getEventStr(){
        String strEvent = "";
        switch (event){
            case 0:
                strEvent = "onResume";
                break;
            case 1:
                strEvent = "onPause";
                break;
            case 2:
                strEvent = "onClick";
                break;
            case 3:
                strEvent = "onItemClick";
                break;
        }
        return strEvent;
    }
}
