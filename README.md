# 常用基础库集合

## 一、使用说明

在 Application 中调用 `DUtil.init(Application)` 完成初始化。

根据需要调用 `DUtil.setDebug()`，它将决定你的日志是否可见。

## 二、功能描述

### 工具类

类名|说明|备注
---|---|---
AnimationUtil|动画工具类|支持动画链
FileUtil|文件操作工具类|
FontUtil|字体切换工具类|支持设置通用字体，支持针对 ViewGroup 下的所有 TextView 及其子类设置字体。
HandlerUtil|线程切换工具类|
ScreenUtil|屏幕尺寸数据工具类|dp 转 px、屏幕 dp、屏幕 px 等
SharedUtil|SharedPreference工具类|支持 putBean()
ToastUtil|Toast工具类|支持 Toast 安全展示，简化调用。
Ln|日志工具类|支持日志根据环境决定是否输出，支持日志分段展示。
AppStateUtil|app状态监控|前后台状态切换回调，前台Activity获取

### View

类名|说明
---|---
CircleImageView|一个圆形 ImageView 控件

### 功能类

类名|说明
---|---
Dson|一种数据格式，详见源码说明


## 三、未来补充

1. 沉浸式方案：目标方便快捷，不需要 Activity 做改动，或做很小改动；
2. AsyncImageView：设置 url 后自动加载图片，有加载中、加载失败状态，并有状态切换动画；
3. FileUtil：文件操作工具类需要完善；
4. AnimationUtil：动画链功能完善。


