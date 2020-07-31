package com.dixon.dlibrary.util;

import android.app.Application;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: Util类的初始化器
 */
public class DUtil {

    public static void init(Application context) {
        ToastUtil.init(context);
        FontUtil.init(context);
        AppTracker.track(context);
    }

    public static void setDebug(boolean isDebug) {
        Constant.sDebug = isDebug;
    }

    public static boolean isDebug() {
        return Constant.sDebug;
    }

    /**
     * 下面提供初始化方法
     * <p>
     * 开发者也可以直接调用类.xx初始化
     * 这里重复提供是为了调用DUtil时，可以简单直观的看到哪些参数可以初始化
     * 且这些参数初始化对于后续开发是有益的（有益的，但不是必须的，对于有的项目用不着，所以需要按需手动初始化）
     */

    /**
     * 设置Font时调用的默认字体
     *
     * @param defaultFont
     */
    public static void setDefaultFont(String defaultFont) {
        FontUtil.setDefaultFont(defaultFont);
    }

    /**
     * 设置SharedPreference初始化参数
     *
     * @param context
     * @param sharedName
     */
    public static void setSharedPreference(Application context, String sharedName) {
        SharedUtil.init(context, sharedName);
    }
}
