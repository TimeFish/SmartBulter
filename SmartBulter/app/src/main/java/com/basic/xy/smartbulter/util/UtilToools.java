package com.basic.xy.smartbulter.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.util
 * 文件名:    UtilToools
 * 创建者:    XY
 * 创建时间:   2017/2/10 17:02
 * 描述:       工具统一类
 */
public class UtilToools {
    /**
     * 设置字体
     * @param mContext
     * @param mTextView
     */
    public static void setFont(Context mContext, TextView mTextView) {
        Typeface fontType = Typeface.createFromAsset(mContext.getAssets(), "fonts/FONT.TTF");
        mTextView.setTypeface(fontType);
    }

    public static void requestPermission(String[] permission,Activity activity,int request_code) {
        for (String p : permission) {
            if (ContextCompat.checkSelfPermission(activity,
                   p)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        p)) {
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    ActivityCompat.requestPermissions(activity,
                            new String[]{p},
                            request_code);
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(activity,
                            new String[]{p},
                            request_code);
                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        }
    }
}
