package com.em.emonitor.core;

import com.em.emonitor.callback.EmClickListener;
import com.em.emonitor.callback.EmItemClickListener;

/**
 * Time ： 2018/10/24 .
 * Author ： JN Zhang .
 * Description ：接口管理类 .
 */
public class EmBaseTask {
    private static EmBaseTask emBaseTask = null;
    private EmClickListener emClickListener;
    private EmItemClickListener emItemClickListener;

    public EmBaseTask(){}

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

    public EmClickListener getEmClickListener(){
        return emClickListener;
    }

    public EmItemClickListener getEmItemClickListener(){
        return emItemClickListener;
    }
}
