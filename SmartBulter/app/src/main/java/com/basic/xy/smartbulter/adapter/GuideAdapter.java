package com.basic.xy.smartbulter.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.adapter
 * 文件名:    GuideAdapter
 * 创建者:    XY
 * 创建时间:   2017/2/15 10:56
 * 描述:       作为VerticalViewPager的适配器
 */
public class GuideAdapter extends PagerAdapter {
    List mList;

    /**
     *
     * @param mList
     * 获取添加到ViewPager的View
     */
    public GuideAdapter(List mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView((View) mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) mList.get(position));
    }
}
