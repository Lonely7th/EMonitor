package com.em.emonitor.plugin.util

/**
 * Time ： 2018/11/27 .
 * Author ： JN Zhang .
 * Description ： .
 */
class InjectUtils {
    private final static String PackageName = "com.em.emonitor.core.manager"

    //监听点击事件的代码
    public final static String codeClickEvent = PackageName + ".EmClickManager.EMonitorClick(v);"
    public final static String codeItemClickEvent = PackageName + ".EmClickManager.EMonitorItemClick(view, position);"
    public final static String codeResumeEvent = PackageName + ".EmEventManager.onEvent(getClass().getSimpleName(), com.em.emonitor.core.ContentKey.EmOnResume);"
    public final static String codePauseEvent = PackageName + ".EmEventManager.onEvent(getClass().getSimpleName(), com.em.emonitor.core.ContentKey.EmOnPause);"

    public final static String codeResumeFun = "protected void onResume() { super.onResume(); " + codeResumeEvent + " }"
    public final static String codePauseFun = "protected void onPause() { super.onPause(); " + codePauseEvent + " }"

}
