package com.dixon.dlibrary.util;

import android.app.Application;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: 字体设置类 字体文件要放在[assets/font]下
 */
public class FontUtil {

    private static Application sApplication;
    private static String sDefaultFont;

    static void init(Application context) {
        sApplication = context;
    }

    private static Map<String, Typeface> cache = new HashMap<>();

    /**
     * 设置字体
     *
     * @param font 字体文件名
     * @param v    要设置字体的TextView
     */
    public static void font(String font, TextView v) {
        v.setTypeface(getType(font));
    }

    public static void font(String font, TextView... v) {
        for (TextView view : v) {
            view.setTypeface(getType(font));
        }
    }

    /**
     * 设置字体 使用默认字体
     *
     * @param v 要设置字体的TextView
     */
    public static void font(TextView v) {
        if (TextUtils.isEmpty(sDefaultFont)) {
            throw new NullPointerException("The default font is not set, try to call DUtil.setDefaultFont() to complete the initialization.");
        }
        v.setTypeface(getType(sDefaultFont));
    }

    public static void font(TextView... v) {
        if (TextUtils.isEmpty(sDefaultFont)) {
            throw new NullPointerException("The default font is not set, try to call DUtil.setDefaultFont() to complete the initialization.");
        }
        for (TextView view : v) {
            view.setTypeface(getType(sDefaultFont));
        }
    }

    /**
     * 设置字体 会遍历ViewGroup下的所有TextView或其子类 并设置字体
     *
     * @param view
     */
    public static void font(View view) {
        if (TextUtils.isEmpty(sDefaultFont)) {
            throw new NullPointerException("The default font is not set, try to call DUtil.setDefaultFont() to complete the initialization.");
        }
        setFont(view, getType(sDefaultFont));
    }

    public static void font(String font, View view) {
        setFont(view, getType(font));
    }

    private static void setFont(View view, Typeface typeface) {
        if (null == view) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LinkedList<ViewGroup> queue = new LinkedList<>();
            queue.add(viewGroup);
            while (!queue.isEmpty()) {
                ViewGroup current = queue.removeFirst();
                for (int i = 0; i < current.getChildCount(); i++) {
                    View child = current.getChildAt(i);
                    if (child instanceof ViewGroup) {
                        queue.addLast((ViewGroup) current.getChildAt(i));
                    } else {
                        if (child instanceof TextView) {
                            ((TextView) child).setTypeface(typeface);
                        }
                    }
                }
            }
        } else {
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(typeface);
            }
        }
    }

    private static Typeface getType(String name) {
        Typeface typeface = cache.get(name);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(sApplication.getAssets(), "font/" + name);
            cache.put(name, typeface);
        }
        return typeface;
    }

    public static void setDefaultFont(String sDefaultFont) {
        FontUtil.sDefaultFont = sDefaultFont;
    }
}
