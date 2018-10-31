package com.em.emonitor.bean;

/**
 * Time ： 2018/10/31 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class StatisticsBean {
    private String className;
    //0.onResume  1.onPause  2.onClick
    private int event;
    private String viewId;
    private long currentTime;

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
        }
        return strEvent;
    }
}
