package com.basic.xy.smartbulter.application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.application
 * 文件名:    BaseApplication
 * 创建者:    XY
 * 创建时间:   2017/2/10 16:40
 * 描述:       TODO
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //腾讯Bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), "145b04cd70", true);
    }
}
