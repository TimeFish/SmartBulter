package com.basic.xy.smartbulter.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.basic.xy.smartbulter.R;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.view
 * 文件名:    CustomDialog
 * 创建者:    XY
 * 创建时间:   2017/2/20 11:30
 * 描述:       TODO
 */
public class CustomDialog extends Dialog {

    //定义模板
    public CustomDialog(Context context,int layout,int style) {
        this(context, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, layout, style, Gravity.CENTER);
    }

    //定义属性
    public CustomDialog(Context context,int width,int height,int layout,int style,int gravity,int anim) {
        super(context, style);
        //设置属性
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
        window.setWindowAnimations(anim);
    }

    //实例
    public CustomDialog(Context context,int width,int height,int layout,int style,int gravity) {
        this(context, width, height, layout, style, gravity, R.style.pop_anim_style);
    }
}
