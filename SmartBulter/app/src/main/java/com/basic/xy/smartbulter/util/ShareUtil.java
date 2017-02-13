package com.basic.xy.smartbulter.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.util
 * 文件名:    ShareUtil
 * 创建者:    XY
 * 创建时间:   2017/2/13 9:32
 * 描述:       TODO
 */
public class ShareUtil {

    private static final String NAME = "config";

    public static void putString(Context mContext,String key,String value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context mContext, String key, String defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(Context mContext,String key,int value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context mContext, String key, int defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putBoolean(Context mContext,String key,boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context mContext, String key, boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void delShare(Context mContext,String key) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    public static void delAll(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
