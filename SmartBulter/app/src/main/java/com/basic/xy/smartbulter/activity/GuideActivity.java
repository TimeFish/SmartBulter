package com.basic.xy.smartbulter.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.basic.xy.smartbulter.MainActivity;
import com.basic.xy.smartbulter.R;
import com.basic.xy.smartbulter.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class GuideActivity extends AppCompatActivity {
    private VerticalViewPager mGuideVVP;
    //引导子页List
    private List<View> mGuideViewList = new ArrayList<>();
    //三个引导子页
    private View view1,view2, view3;
    //小圆点
    private ImageView point1,point2, point3;
    private ImageView mBackIV;
    private Button mEnterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    private void initView() {
        mGuideVVP = (VerticalViewPager) findViewById(R.id.vvp_guide);

        view1 = View.inflate(this, R.layout.item_guide_one, null);
        view2 = View.inflate(this, R.layout.item_guide_two, null);
        view3 = View.inflate(this, R.layout.item_guide_three, null);

        mGuideViewList.add(view1);
        mGuideViewList.add(view2);
        mGuideViewList.add(view3);

        mGuideVVP.setAdapter(new GuideAdapter(mGuideViewList));
        mGuideVVP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当页面滑动完毕后，改变小圆点样式。如果用onPageScrolled，是在有滑动的时候，就改变小圆点样式
                setPointStyle(position);
                //设置跳过和进入主页是否隐藏
                if (position == 2) {
                    mBackIV.setVisibility(View.GONE);
                    mEnterBtn.setVisibility(View.VISIBLE);
                } else {
                    mBackIV.setVisibility(View.VISIBLE);
                    mEnterBtn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);
        setPointStyle(0);

        mBackIV = (ImageView) findViewById(R.id.iv_back);
        mBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));finish();
            }
        });
        mEnterBtn = (Button) findViewById(R.id.btn_enter);
        mEnterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));finish();
            }
        });
    }

    private void setPointStyle(int checkedPosition) {
        if (checkedPosition == 0) {
            point1.setImageResource(R.drawable.point_on);
        } else {
            point1.setImageResource(R.drawable.point_off);
        }

        if (checkedPosition == 1) {
            point2.setImageResource(R.drawable.point_on);
        } else {
            point2.setImageResource(R.drawable.point_off);
        }
        if (checkedPosition == 2) {
            point3.setImageResource(R.drawable.point_on);
        } else {
            point3.setImageResource(R.drawable.point_off);
        }
    }
}
