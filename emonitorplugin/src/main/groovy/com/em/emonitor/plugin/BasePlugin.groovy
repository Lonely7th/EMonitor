package com.em.emonitor.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Time ： 2018/10/24 .
 * Author ： JN Zhang .
 * Description ： .
 */
public class BasePlugin implements Plugin<Project> {

    void apply(Project project) {
        //AppExtension就是build.gradle中android{...}这一块
        def android = project.extensions.getByType(AppExtension)

        //注册一个Transform
        def classTransform = new BaseClassTransform(project)
        android.registerTransform(classTransform)

        //执行Transform
        project.task('transformPath') {
            doLast {
                System.out.println('start transformPath task')
            }
        }

//        //创建一个Extension，名字叫做testCreatJavaConfig 里面可配置的属性参照MyPlguinTestClass
//        project.extensions.create("testCreatJavaConfig", MyPlguinTestClass)

//        //生产一个类
//        if (project.plugins.hasPlugin(AppPlugin)) {
//            //获取到Extension，Extension就是 build.gradle中的{}闭包
//            android.applicationVariants.all { variant ->
//                //获取到scope,作用域
//                def variantData = variant.variantData
//                def scope = variantData.scope
//
//                //拿到build.gradle中创建的Extension的值
//                def config = project.extensions.getByName("testCreatJavaConfig")
//
//                //创建一个task
//                def createTaskName = scope.getTaskName("CreatNewClass", "MyTestPlugin")
//                def createTask = project.task(createTaskName)
//                //设置task要执行的任务
//                createTask.doLast {
//                    //生成java类
//                    createJavaTest(variant, config)
//                }
//                //设置task依赖于生成BuildConfig的task，然后在生成BuildConfig后生成我们的类
//                String generateBuildConfigTaskName = variant.getVariantData().getScope().getGenerateBuildConfigTask().name
//                def generateBuildConfigTask = project.tasks.getByName(generateBuildConfigTaskName)
//                if (generateBuildConfigTask) {
//                    createTask.dependsOn generateBuildConfigTask
//                    generateBuildConfigTask.finalizedBy createTask
//                }
//            }
//
//        }
    }

//    static def void createJavaTest(variant, config) {
//        //要生成的内容
//        def content = """package zjn;
//
//                        public class MyPlguinTestClass {
//                            public static final String str = "${config.str}";
//                        }
//                        """
//        //获取到BuildConfig类的路径
//        File outputDir = variant.getVariantData().getScope().getBuildConfigSourceOutputDir()
//
//        def javaFile = new File(outputDir, "MyPlguinTestClass.java")
//
//        javaFile.write(content, 'UTF-8')
//    }
}
