package com.em.emonitor.callback;

import com.em.emonitor.bean.EmEventBean;

/**
 * Time ： 2018/10/31 .
 * Author ： JN Zhang .
 * Description ： .
 */
public interface EmEventListener {
    public void onEvent(EmEventBean emEventBean);
}
