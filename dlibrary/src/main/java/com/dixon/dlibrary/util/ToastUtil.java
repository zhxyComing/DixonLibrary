package com.dixon.dlibrary.util;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: Toast工具类 能兼容多次Toast情况 同时简单易用
 */
public class ToastUtil {
    /**
     * 全局Toast对象
     */
    private static Toast mToast;

    private static Application sApplication;

    static void init(Application context) {
        sApplication = context;
    }

    public static void toast(final String message, final int duration) {

        toast(sApplication, message, duration);
    }

        public static void toast(final Context context, final String message, final int duration) {

        HandlerUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //先取消正在显示的Toast
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(context, message, duration);
                mToast.show();
            }
        });
    }

    public static void toast(String message) {
        if (!TextUtils.isEmpty(message)) {
            toast(message, Toast.LENGTH_SHORT);
        }
    }

    public static void toast(int message) {
        String text = sApplication.getResources().getString(message);
        if (!TextUtils.isEmpty(text)) {
            toast(text, Toast.LENGTH_SHORT);
        }
    }

    public static void toast(Context context, String message) {
        if (!TextUtils.isEmpty(message)) {
            toast(context, message, Toast.LENGTH_SHORT);
        }
    }

    public static void toast(Context context, int message) {
        String text = context.getResources().getString(message);
        if (!TextUtils.isEmpty(text)) {
            toast(context, text, Toast.LENGTH_SHORT);
        }
    }
}