package com.em.emonitor.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.em.emonitor.bean.StatisticsBean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Time ： 2018/10/31 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class StatisticsUtil {
    private static final String TAG = "EmLog";

    public static void statistics(Context context, StatisticsBean statisticsBean){
        if(null == context){
            throw new NullPointerException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getDateToString(statisticsBean.getCurrentTime(),"yyyy-MM-dd HH:mm:ss")).append(" ");
        stringBuilder.append(statisticsBean.getEventStr()).append("/");
        stringBuilder.append(statisticsBean.getClassName()).append(": ");
        if(!TextUtils.isEmpty(statisticsBean.getViewId())){
            stringBuilder.append(statisticsBean.getViewId());
        }
        stringBuilder.append("\n");
        //打印日志
        Log.d(TAG,stringBuilder.toString().trim());
        //保存日志
        try {
            String path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/emonitor_log.log";
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
            e.printStackTrace();
        }
    }

    /**
     * 时间戳转换
     */
    private static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

}
