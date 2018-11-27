package com.em.emonitor.plugin.core

import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.gradle.api.Project

/**
 * Time ： 2018/10/24 .
 * Author ： JN Zhang .
 * Description ： .
 */
class BaseInjects {
    //监听点击事件的代码
    private final static String codeClickEvent = "com.em.emonitor.core.manager.EmClickManager.EMonitorClick(getClass().getSimpleName(), v);"
    private final static String codeItemClickEvent = "com.em.emonitor.core.manager.EmClickManager.EMonitorItemClick(getClass().getSimpleName(), view, position);"
    private final static String codeResumeEvent = "com.em.emonitor.core.manager.EmEventManager.onEvent(getClass().getSimpleName(), com.em.emonitor.core.ContentKey.EmOnResume);"
    private final static String codePauseEvent = "com.em.emonitor.core.manager.EmEventManager.onEvent(getClass().getSimpleName(), com.em.emonitor.core.ContentKey.EmOnPause);"

    private final static String codeResumeFun = "protected void onResume() { super.onResume(); " + codeResumeEvent + " }"
    private final static String codePauseFun = "protected void onPause() { super.onPause(); " + codePauseEvent + " }"

    //初始化类池
    private final static ClassPool pool = ClassPool.getDefault()

    //引入关联的jar包
    static void setClassPath(String path) {
        pool.appendClassPath(path)
    }

    static void inject(String path, Project project) {
        //将当前路径加入类池
        pool.appendClassPath(path)
        //project.android.bootClasspath 加入android.jar
        pool.appendClassPath(project.android.bootClasspath[0].toString())
        //引入android.os.Bundle包，因为onCreate方法参数有Bundle
        pool.importPackage("android.os.Bundle")

        File dir = new File(path)
        if (dir.isDirectory()) {
            //遍历文件夹
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath
                println("filePath = " + filePath)
                if (filePath.endsWith(".class") && !filePath.contains('R$') && !filePath.contains('R.class')
                        && !filePath.contains("BuildConfig.class") && !getPackagebyFilePath(filePath).equals("")){
                    //获取.class文件
                    println("class = " + getPackagebyFilePath(filePath))
                    CtClass ctClass = pool.getCtClass(getPackagebyFilePath(filePath))
                    println("superClass = " + ctClass.getName())
                    if (ctClass.isFrozen()){
                        //解冻
                        ctClass.defrost()
                    }
                    boolean inResume = false
                    boolean inPause = false
                    //获取.class中的全部方法
                    for(CtMethod ctMethod : ctClass.getDeclaredMethods()){
                        println("ctMethod = " + ctMethod.name)
                        switch (ctMethod.getName()){
                            case "onClick":
                                //在方法开头插入代码
                                if(ctMethod.getMethodInfo().getCodeAttribute() != null){
                                    ctMethod.insertBefore(codeClickEvent)
                                }
                                break
                            case "onItemClick":
                                //在方法开头插入代码
                                if(ctMethod.getMethodInfo().getCodeAttribute() != null){
                                    ctMethod.insertBefore(codeItemClickEvent)
                                }
                                break
                            case "onResume":
                                inResume = true
                                ctMethod.insertBefore(codeResumeEvent)
                                break
                            case "onPause":
                                inPause = true
                                ctMethod.insertBefore(codePauseEvent)
                                break
                        }
                    }
                    if(ctClass.getSuperclass().name.contains("Activity") && !inResume){
                        //如果不存在onResume方法则添加
                        ctClass.addMethod(CtMethod.make(codeResumeFun, ctClass))
                    }
                    if(ctClass.getSuperclass().name.contains("Activity") && !inPause){
                        //如果不存在onPause方法则添加
                        ctClass.addMethod(CtMethod.make(codePauseFun, ctClass))
                    }
                    ctClass.writeFile(path)
                    //释放
                    ctClass.detach()
                }
            }
        }

    }

    //获取当前类的绝对路径
    private static String getPackagebyFilePath(String filePath){
        //排除空路径
        if(filePath == null || filePath.equals("") || filePath.contains("\$")){
            return ""
        }
        //排除文件夹路径
        if(!filePath.contains(".class")){
            return ""
        }
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
