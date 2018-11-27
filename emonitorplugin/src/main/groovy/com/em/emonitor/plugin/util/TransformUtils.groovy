package com.em.emonitor.plugin.util

/**
 * Time ： 2018/11/27 .
 * Author ： JN Zhang .
 * Description ： .
 */
class TransformUtils {
    static final String[] needInjects = ["\$","R.class","BuildConfig.class"]

    public static boolean classNeedInject(String filePath){
        if(filePath == null || filePath.equals("")){
            return false
        }
        if(!filePath.endsWith(".class")){
            return false
        }
        for(String str : needInjects){
            if(filePath.contains(str))
                return false
        }
        return true
    }

    /**
     * 获取当前类的绝对路径
     */
    public static String getPackagebyFilePath(String filePath){
        String strPackage
        if(filePath.contains("classes\\debug")){
            strPackage = filePath.substring(filePath.indexOf("classes\\debug")+"classes\\debug".length()+1)
        }else if(filePath.contains("classes\\release")){
            strPackage = filePath.substring(filePath.indexOf("classes\\release")+"classes\\release".length()+1)
        } else{
            strPackage = filePath.substring(filePath.indexOf("classes")+"classes".length()+1)
        }

        strPackage = strPackage.replace(".class","")
        strPackage = strPackage.replace("\\",".")
        return strPackage
    }

}
