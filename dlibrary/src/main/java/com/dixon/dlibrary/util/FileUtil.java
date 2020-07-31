package com.dixon.dlibrary.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: 文件操作工具类
 */
public class FileUtil {

    public static String getFromAssets(String fileName, Context context) {

        // load text
        try {
            // get input stream for text
            InputStream is = context.getAssets().open(fileName);
            // check size
            int size = is.available();
            // create buffer for IO
            byte[] buffer = new byte[size];
            // get data to buffer
            is.read(buffer);
            // close stream
            is.close();
            // set result to TextView
            return new String(buffer);
        } catch (IOException ex) {
            return "";
        }
    }
}
