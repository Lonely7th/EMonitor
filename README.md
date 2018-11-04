本文将会介绍Android端非侵入式数据采集框架EMonitor

手机客户端作为重要的流量入口，经常会肩负着用户数据监测和数据采集的重任。待采集的数据包括用户何时启动应用，浏览了哪些页面，点击了哪些按钮等等，对于此类需求，比较简单粗暴的做法就是手动埋点，在需要采集数据的位置添加相应的代码。这种做法不仅工作量巨大，而且会增加代码的耦合度，后期维护成本高。

EMonitor是一款非侵入式的数据采集框架，开发者不需要改动任何代码，只要引入EMonitor库并调用相关接口就可以实现全量的数据采集，代码耦合度低，易于后期维护。

**1.引入EMonitor库，2.初始化EmBaseTask，3.实现相关接口**

#1.引入EMonitor库
在project下的build.gradle文件中添加依赖：
```
maven {url 'https://dl.bintray.com/jnzhang/EMonitor'}

classpath 'com.emonitor.core:emonitorplugin:1.4.8'
```
在module下的build.gradle文件中添加引用和插件：
```
apply plugin: 'emplugin'

implementation 'com.emonitor.core:emonitorcore:1.3.1'
```
#2.初始化EmBaseTask
在应用程序的入口初始化EmBaseTask：
```
EmBaseTask.getInstance().init(context);
```
初始化成功后，EMonitor将会对应用的各种行为进行统计，并将统计到的数据保存在应用目录下的emonitor_log.log文件中，开发者可以获取log中的内容进行相应的处理，log文件中数据的样式：
```
2018-11-02 16:38:41 onPause/MainActivity: 
2018-11-02 16:38:41 onResume/TaskActivity: 
2018-11-02 16:38:43 onClick/TaskActivity: AppCompatButton[0]LinearLayout[0]ConstraintLayout[0]ContentFrameLayout[0]ActionBarOverlayLayout[0]FrameLayout[1]LinearLayout[0]
2018-11-02 16:39:25 onClick/TaskActivity: AppCompatButton[1]LinearLayout[0]ConstraintLayout[0]ContentFrameLayout[0]ActionBarOverlayLayout[0]FrameLayout[1]LinearLayout[0]
2018-11-02 16:39:29 onClick/TaskActivity: AppCompatButton[2]LinearLayout[0]ConstraintLayout[0]ContentFrameLayout[0]ActionBarOverlayLayout[0]FrameLayout[1]LinearLayout[0]
2018-11-02 16:39:32 onClick/TaskActivity: AppCompatButton[0]LinearLayout[3]LinearLayout[0]ConstraintLayout[0]ContentFrameLayout[0]ActionBarOverlayLayout[0]FrameLayout[1]LinearLayout[0]
2018-11-02 16:40:18 onClick/TaskActivity: AppCompatButton[1]LinearLayout[3]LinearLayout[0]ConstraintLayout[0]ContentFrameLayout[0]ActionBarOverlayLayout[0]FrameLayout[1]LinearLayout[0]
2018-11-02 16:45:11 onResume/MainActivity: 
2018-11-02 16:45:11 onPause/MainActivity: 
2018-11-02 16:45:11 onResume/TaskActivity: 
2018-11-02 16:45:13 onClick/TaskActivity: LinearLayout[0]FrameLayout[1]ActionBarOverlayLayout[0]ContentFrameLayout[0]ConstraintLayout[0]LinearLayout[0]AppCompatButton[0]
2018-11-02 16:45:47 onClick/TaskActivity: LinearLayout[0]FrameLayout[1]ActionBarOverlayLayout[0]ContentFrameLayout[0]ConstraintLayout[0]LinearLayout[0]AppCompatButton[1]
2018-11-02 16:47:02 onPause/TaskActivity: 
2018-11-02 16:47:02 onResume/MainActivity: 
```
#3.实现相关接口
很多时候EMonitor自带的Log文件并不能满足开发者的需求，EMonitor也提供了一些接口，开发者可以调用这些接口来实现自己的业务逻辑。

*3.1调用用户点击某个控件的监听：*
```
  EmBaseTask.getInstance().setEmClickListener(new EmClickListener() {
        @Override
        public void onClick(SingleClickBean singleClickBean) {
            //业务逻辑
        }
    });
```

*3.2调用用户进出某个页面的监听：*
```
  EmBaseTask.getInstance().setEmEventListener(new EmEventListener() {
        @Override
        public void EmOnResume(EmEventBean emEventBean) {
            //业务逻辑
        }

        @Override
        public void EmOnPause(EmEventBean emEventBean) {
            //业务逻辑
        }
    });
```
EMonitor框架接下来将会逐步添加列表浏览事件监测和用户地理位置监测等功能。
