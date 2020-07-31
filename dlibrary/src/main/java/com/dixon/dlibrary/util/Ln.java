package com.dixon.dlibrary.util;

import android.util.Log;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: 日志输出 支持分段输出 解决长log无法显示的问题
 */
public class Ln {

    private static int SPLIT_LENGTH = 4000;

    public static void i(String key, String content) {
        if (!Constant.sDebug) {
            return;
        }
        if (content.length() > SPLIT_LENGTH) {
            for (int index = 0; index < content.length(); index += SPLIT_LENGTH) {
                if (index + SPLIT_LENGTH < content.length()) {
                    Log.i(key + " " + index, content.substring(index, index + SPLIT_LENGTH));
                } else {
                    Log.i(key + " " + index, content.substring(index));
                }
            }
        } else {
            Log.i(key, content);
        }
    }

    public static void e(String key, String content) {
        if (!Constant.sDebug) {
            return;
        }
        if (content.length() > SPLIT_LENGTH) {
            for (int index = 0; index < content.length(); index += SPLIT_LENGTH) {
                if (index + SPLIT_LENGTH < content.length()) {
                    Log.e(key + " " + index, content.substring(index, index + SPLIT_LENGTH));
                } else {
                    Log.e(key + " " + index, content.substring(index));
                }
            }
        } else {
            Log.e(key, content);
        }
    }
}
