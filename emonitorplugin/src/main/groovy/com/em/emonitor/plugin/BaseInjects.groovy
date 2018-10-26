package com.em.emonitor.plugin

import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.gradle.api.Project

/**
 * Time ： 2018/10/24 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class BaseInjects {
    //监听点击事件的代码
    private final static String codeClickEvent = "com.em.emonitor.core.manager.EmClickManager.EMonitorClick(getClass().getSimpleName(), v);"
    //初始化类池
    private final static ClassPool pool = ClassPool.getDefault()

    //引入关联的jar包
    public static void setClassPath(String path) {
        pool.appendClassPath(path)
    }

    public static void inject(String path, Project project) {
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
                if (!getPackagebyFilePath(filePath).equals("")){
                    //获取.class文件
                    println("class = " + getPackagebyFilePath(filePath))
                    CtClass ctClass = pool.getCtClass(getPackagebyFilePath(filePath))
                    //获取.class中的全部方法
                    for(CtMethod ctMethod : ctClass.getDeclaredMethods()){
                        println("ctMethod = " + ctMethod)
                        //添加点击事件监听
                        if(ctMethod.getName().equals("onClick")){
                            if (ctClass.isFrozen()){//解冻
                                ctClass.defrost()
                            }
                            //待添加的代码
                            String insetBeforeStr = codeClickEvent
                            //在方法开头插入代码
                            ctMethod.insertBefore(insetBeforeStr)
                            //添加相关引用
                            ctClass.writeFile(path)
                            ctClass.detach()//释放
                        }else{
                            //处理单个控件设置监听的情况
                        }
                        //添加浏览事件监听
                    }
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
        String strPackage = filePath.substring(filePath.indexOf("classes")+"classes".length()+1)
        strPackage = strPackage.replace(".class","")
        strPackage = strPackage.replace("\\",".")
        return strPackage
    }
}
