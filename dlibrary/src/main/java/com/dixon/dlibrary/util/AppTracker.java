package com.dixon.dlibrary.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.16
 * Functional desc: App状态监控，用于获取前后台状态，当前Activity
 */
public class AppTracker {

    public static final int STATE_FOREGROUND = 0;

    public static final int STATE_BACKGROUND = 1;

    private static int sCurrentState;

    private static Activity sCurActivity;

    private static Application sCurApplication;

    private static final List<WeakReference<AppStateChangeListener>> sTrackers = new ArrayList<>();

    public interface AppStateChangeListener {

        void appTurnIntoForeground();

        void appTurnIntoBackGround();
    }

    static void track(Application application) {
        sCurApplication = application;
        application.registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks() {

            // 前后台监控
            private int resumeActivityCount = 0;

            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                super.onActivityCreated(activity, bundle);
                sCurActivity = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (resumeActivityCount == 0) {
                    sCurrentState = STATE_FOREGROUND;
                    sStateGate.appTurnIntoForeground();
                }
                resumeActivityCount++;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                resumeActivityCount--;
                if (resumeActivityCount == 0) {
                    sCurrentState = STATE_BACKGROUND;
                    sStateGate.appTurnIntoBackGround();
                }
            }

            // 前台Activity监控
            @Override
            public void onActivityResumed(Activity activity) {
                sCurActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                sCurActivity = null;
            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }

        });
    }

    private static class SimpleActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }

    private static final AppStateChangeListener sStateGate = new AppStateChangeListener() {

        @Override
        public void appTurnIntoForeground() {
            Iterator<WeakReference<AppStateChangeListener>> iterator = sTrackers.iterator();
            while (iterator.hasNext()) {
                WeakReference<AppStateChangeListener> trackerReference = iterator.next();
                AppStateChangeListener tracker = trackerReference.get();
                if (tracker == null) {
                    // 移除过期元素
                    iterator.remove();
                } else {
                    // 执行函数
                    tracker.appTurnIntoForeground();
                }
            }
        }

        @Override
        public void appTurnIntoBackGround() {
            Iterator<WeakReference<AppStateChangeListener>> iterator = sTrackers.iterator();
            while (iterator.hasNext()) {
                WeakReference<AppStateChangeListener> trackerReference = iterator.next();
                AppStateChangeListener tracker = trackerReference.get();
                if (tracker == null) {
                    // 移除过期元素
                    iterator.remove();
                } else {
                    // 执行函数
                    tracker.appTurnIntoBackGround();
                }
            }
        }
    };

    public static void register(AppStateChangeListener newTracker) {
        Iterator<WeakReference<AppStateChangeListener>> iterator = sTrackers.iterator();
        while (iterator.hasNext()) {
            WeakReference<AppStateChangeListener> trackerReference = iterator.next();
            AppStateChangeListener tracker = trackerReference.get();
            if (tracker == null) {
                // 移除过期元素
                iterator.remove();
            } else if (tracker == newTracker) {
                // 如果已有该元素 则跳过
                return;
            }
        }
        sTrackers.add(new WeakReference<>(newTracker));
    }

    /**
     * 获取当前前台还是后台
     */
    public static int getsCurrentState() {
        return sCurrentState;
    }

    /**
     * 获取当前前台Activity
     */
    public static Activity getCurActivity() {
        return sCurActivity;
    }

    /**
     * 获取Application
     */
    public static Application getCurApplication() {
        return sCurApplication;
    }
}
