package com.basic.xy.smartbulter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.basic.xy.smartbulter.fragment.BulterFragment;
import com.basic.xy.smartbulter.fragment.GirlFragment;
import com.basic.xy.smartbulter.fragment.UserFragment;
import com.basic.xy.smartbulter.fragment.WechatFragment;
import com.basic.xy.smartbulter.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    //tab标题
    private List<String> mTitle;
    private List<Fragment> mFragment;
    private FloatingActionButton mSettingFloatActionBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去掉阴影
        getSupportActionBar().setElevation(0);
        initData();
        initView();
    }

    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getString(R.string.title_bulter));
        mTitle.add(getString(R.string.title_wechat));
        mTitle.add(getString(R.string.title_girl));
        mTitle.add(getString(R.string.title_user));

        mFragment = new ArrayList<>();
        mFragment.add(new BulterFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new GirlFragment());
        mFragment.add(new UserFragment());

        mSettingFloatActionBtn = (FloatingActionButton) findViewById(R.id.fab_setting);
        mSettingFloatActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        mViewPager.setOffscreenPageLimit(mFragment.size());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //ViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    mSettingFloatActionBtn.setVisibility(View.GONE);
                } else {
                    mSettingFloatActionBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("TAG", "position:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
