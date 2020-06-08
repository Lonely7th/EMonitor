
**1.引入EMonitor库**

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
**2.初始化EmBaseTask**

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
**3.实现相关接口**

如果EMonitor自带的Log文件不能满足你的需求，EMonitor也提供了一些接口，你可以调用这些接口来自定义业务逻辑。

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
