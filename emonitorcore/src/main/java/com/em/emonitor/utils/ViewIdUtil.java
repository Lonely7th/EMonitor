package com.em.emonitor.utils;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Time ： 2018/10/31 .
 * Author ： JN Zhang .
 * Description ：获取View的ViewPath .
 */
public class ViewIdUtil {

    public static String getId(String className, View view){
        Log.d("ViewIdUtil","className = " + className + "path = " + getPath(view));
        return getPath(view);
    }

    private static String getPath(View view) {
        //构造ViewPath中于view对应的节点:ViewType[index],获取view在兄弟节点中的index
        StringBuilder ViewPath = new StringBuilder();
        while(view.getParent() instanceof ViewGroup){
            String ViewType=view.getClass().getSimpleName();
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            for(int i = 0;i < viewGroup.getChildCount();i++){
                if(viewGroup.getChildAt(i) == view){
                    String content = ViewType + "[" + i + "]";
                    ViewPath.insert(0,content);
                }
            }
            view = (View) view.getParent();
        }
        return ViewPath.toString();
    }

}
