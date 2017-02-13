package com.basic.xy.smartbulter.util;

import android.content.Context;
import android.graphics.Typeface;
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
}
