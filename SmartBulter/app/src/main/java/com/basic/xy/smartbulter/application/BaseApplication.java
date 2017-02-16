package com.basic.xy.smartbulter.application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

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

        initBugly();
        initBmob();
    }

    private void initBmob() {
        //第一种：默认初始化
        Bmob.initialize(this, "Your Application ID");

        //第二种：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    private void initBugly() {
        //腾讯Bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), "145b04cd70", true);
    }

}
