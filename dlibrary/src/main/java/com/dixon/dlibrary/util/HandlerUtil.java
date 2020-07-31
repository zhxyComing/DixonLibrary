package com.dixon.dlibrary.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: 协助回到主线程
 */
public class HandlerUtil {

    private static final Handler UI_HANDLER = new Handler(Looper.getMainLooper());

    private HandlerUtil() {

    }

    public static void runOnUiThread(Runnable r) {
        if (Thread.currentThread().getId() == UI_HANDLER.getLooper().getThread().getId()) {
            r.run();
        } else {
            UI_HANDLER.post(r);
        }
    }

    public static void runOnUiThreadDelayed(Runnable r, long time) {
        UI_HANDLER.postDelayed(r, time);
    }

    public static Handler getUiHandler() {
        return UI_HANDLER;
    }
}
