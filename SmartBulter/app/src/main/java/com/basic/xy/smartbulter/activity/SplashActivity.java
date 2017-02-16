package com.basic.xy.smartbulter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.basic.xy.smartbulter.MainActivity;
import com.basic.xy.smartbulter.R;
import com.basic.xy.smartbulter.util.ShareUtil;
import com.basic.xy.smartbulter.util.StaticClass;
import com.basic.xy.smartbulter.util.UtilToools;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.ui
 * 文件名:    SplashActivity
 * 创建者:    XY
 * 创建时间:   2017/2/13 10:30
 * 描述:       闪屏页
 */
public class SplashActivity extends AppCompatActivity {
    private TextView mSplashTV ;
    /**
     * 1.延时2000ms
     * 2.判断是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     * 5.上下滑动
     */

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
        UtilToools.setFont(this,mSplashTV);
    }

    private boolean isFirst() {
        boolean isFirst = ShareUtil.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst) {
            //标记已经运行过
            ShareUtil.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
            //是第一次运行
            return true;
        } else {
            return false;
        }
    }

    private void initView() {
        mSplashTV = (TextView) findViewById(R.id.tv_splash);
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
