package com.em.emonitor.plugin.core

import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.gradle.api.Project
import com.em.emonitor.plugin.util.*

/**
 * Time ： 2018/10/24 .
 * Author ： JN Zhang .
 * Description ： .
 */
class BaseInjects {
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

        File dir = new File(path)
        if (dir.isDirectory()) {
            //遍历文件夹
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath
                if (TransformUtils.classNeedInject(filePath)){
                    //获取.class文件
                    println("class = " + TransformUtils.getPackagebyFilePath(filePath))
                    CtClass ctClass = pool.getCtClass(TransformUtils.getPackagebyFilePath(filePath))
                    if (ctClass.isFrozen()){
                        ctClass.defrost()  //解冻
                    }
                    boolean inResume = false
                    boolean inPause = false
                    //获取.class中的全部方法
                    for(CtMethod ctMethod : ctClass.getDeclaredMethods()){
                        switch (ctMethod.getName()){
                            case "onClick":
                                if(ctMethod.getMethodInfo().getCodeAttribute() != null){
                                    ctMethod.insertBefore(InjectUtils.codeClickEvent)
                                }
                                break
                            case "onItemClick":
                                if(ctMethod.getMethodInfo().getCodeAttribute() != null){
                                    ctMethod.insertBefore(InjectUtils.codeItemClickEvent)
                                }
                                break
                            case "onResume":
                                inResume = true
                                ctMethod.insertBefore(InjectUtils.codeResumeEvent)
                                break
                            case "onPause":
                                inPause = true
                                ctMethod.insertBefore(InjectUtils.codePauseEvent)
                                break
                        }
                    }

                    //如果不存在onResume方法则添加
                    if(ctClass.getSuperclass().name.contains("Activity") && !inResume){
                        ctClass.addMethod(CtMethod.make(InjectUtils.codeResumeFun, ctClass))
                    }
                    //如果不存在onPause方法则添加
                    if(ctClass.getSuperclass().name.contains("Activity") && !inPause){
                        ctClass.addMethod(CtMethod.make(InjectUtils.codePauseFun, ctClass))
                    }
                    ctClass.writeFile(path)
                    ctClass.detach()  //释放
                }
            }
        }

    }
}
