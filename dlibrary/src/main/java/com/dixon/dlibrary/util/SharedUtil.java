package com.dixon.dlibrary.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.Set;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: SharedPreference工具类 支持putBean
 */
public class SharedUtil {

    private static SharedPreferences sPreferences;

    private SharedUtil() {
    }

    static void init(Application context, String sharedName) {
        sPreferences = context.getApplicationContext().getSharedPreferences(sharedName, Context.MODE_PRIVATE);
    }

    public static boolean putString(String key, String value) {
        SharedPreferences.Editor edit = sPreferences.edit();
        edit.putString(key, value);
        return edit.commit();
    }

    public static String getString(String key, String defaultString) {
        return sPreferences.getString(key, defaultString);
    }

    public static boolean putInt(String key, int value) {
        SharedPreferences.Editor edit = sPreferences.edit();
        edit.putInt(key, value);
        return edit.commit();
    }

    public static int getInt(String key, int defaultInt) {
        return sPreferences.getInt(key, defaultInt);
    }

    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = sPreferences.edit();
        edit.putBoolean(key, value);
        return edit.commit();
    }

    public static boolean getBoolean(String key, boolean defaultBoolean) {
        return sPreferences.getBoolean(key, defaultBoolean);
    }

    public static boolean putFloat(String key, float value) {
        SharedPreferences.Editor edit = sPreferences.edit();
        edit.putFloat(key, value);
        return edit.commit();
    }

    public static float getFloat(String key, float defaultFloat) {
        return sPreferences.getFloat(key, defaultFloat);
    }

    public static boolean putLong(String key, long value) {
        SharedPreferences.Editor edit = sPreferences.edit();
        edit.putLong(key, value);
        return edit.commit();
    }

    public static long getLong(String key, long defaultLong) {
        return sPreferences.getLong(key, defaultLong);
    }

    public static boolean putStringSet(String key, Set<String> value) {
        SharedPreferences.Editor edit = sPreferences.edit();
        edit.putStringSet(key, value);
        return edit.commit();
    }

    public static Set<String> getStringSet(String key, Set<String> defaultString) {
        return sPreferences.getStringSet(key, defaultString);
    }

    public static <T> boolean putBean(String key, T value) {
        SharedPreferences.Editor edit = sPreferences.edit();
        String json = new Gson().toJson(value);
        edit.putString(key, json);
        return edit.commit();
    }

    public static <T> T getBean(String key, Class<T> clazz, T defaultBean) {
        String json = sPreferences.getString(key, null);
        if (TextUtils.isEmpty(json)) {
            return defaultBean;
        }
        T bean = new Gson().fromJson(json, clazz);
        if (bean == null) {
            return defaultBean;
        }
        return bean;
    }
}
