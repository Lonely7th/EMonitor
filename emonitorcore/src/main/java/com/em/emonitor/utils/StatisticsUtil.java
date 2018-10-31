package com.em.emonitor.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.em.emonitor.bean.StatisticsBean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Time ： 2018/10/31 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class StatisticsUtil {

    public static void statistics(Context context, StatisticsBean statisticsBean){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(statisticsBean.getCurrentTime()).append(" ");
        stringBuilder.append(statisticsBean.getClassName()).append(" ");
        stringBuilder.append(statisticsBean.getEventStr()).append(" ");
        if(!TextUtils.isEmpty(statisticsBean.getViewId())){
            stringBuilder.append(statisticsBean.getViewId());
        }
        stringBuilder.append("\n");
        //打印日志
        Log.d("EmLog",stringBuilder.toString().trim());
        //保存日志
        try {
            String path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/emonitor/log.txt";
            // 创建文件对象
            File fileText = new File(path);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText,true);
            // 写文件
            fileWriter.write(stringBuilder.toString());
            // 关闭
            fileWriter.close();
        }
        catch (IOException e) {
            //
            e.printStackTrace();
        }
    }

}
