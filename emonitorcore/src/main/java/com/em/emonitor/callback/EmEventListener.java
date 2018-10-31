package com.em.emonitor.callback;

import com.em.emonitor.bean.EmEventBean;

/**
 * Time ： 2018/10/31 .
 * Author ： JN Zhang .
 * Description ： .
 */
public interface EmEventListener {
    void EmOnResume(EmEventBean emEventBean);
    void EmOnPause(EmEventBean emEventBean);
}
