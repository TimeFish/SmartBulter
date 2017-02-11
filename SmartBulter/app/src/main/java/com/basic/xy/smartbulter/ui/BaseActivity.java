package com.basic.xy.smartbulter.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.basic.xy.smartbulter.R;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.ui
 * 文件名:    BaseActivity
 * 创建者:    XY
 * 创建时间:   2017/2/10 16:52
 * 描述:       Activity 的基类
 *             统一的属性，接口，方法
 */


public class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //去掉阴影
        getSupportActionBar().setElevation(0);
    }

    //菜单栏操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
