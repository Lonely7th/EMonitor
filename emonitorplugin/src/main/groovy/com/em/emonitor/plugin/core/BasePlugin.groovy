package com.em.emonitor.plugin.core

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Time ： 2018/10/24 .
 * Author ： JN Zhang .
 * Description ： .
 */
class BasePlugin implements Plugin<Project> {

    void apply(Project project) {
        def android = project.extensions.getByType(AppExtension)

        //注册一个Transform
        def classTransform = new BaseClassTransform(project)
        android.registerTransform(classTransform)
    }

}
