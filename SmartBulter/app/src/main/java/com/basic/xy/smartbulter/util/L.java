package com.basic.xy.smartbulter.util;

import android.util.Log;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.util
 * 文件名:    L
 * 创建者:    XY
 * 创建时间:   2017/2/13 9:06
 * 描述:       Log工具类封装,可以通过修改开关控制是否输出Log信息
 */
public class L {
    //开关
    private static final boolean DEBUG = true;
    //TAG
    private static final String TAG = "Smartbulter";

    //五个等级 DIWEF
    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text);
        }
    }
    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text);
        }
    }
    

}
