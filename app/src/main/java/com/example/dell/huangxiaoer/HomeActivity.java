package com.example.dell.huangxiaoer;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.VpFragmentAdapter;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.PicFragment;

public class HomeActivity extends BaseActivity {


    private ImageView iv_yuyue;
    private ImageView iv_saoma;
    private ImageView iv_wode;
    private ViewPager vp;
    private FrameLayout fragment;

    @Override
    public void initView() {
        iv_yuyue = findViewById(R.id.iv_yuyue);
        iv_saoma = findViewById(R.id.iv_saoma);
        iv_wode = findViewById(R.id.iv_wode);
        fragment = findViewById(R.id.fragment);
        iv_saoma.setOnClickListener(this);
        iv_yuyue.setOnClickListener(this);
        iv_wode.setOnClickListener(this);
        vp = findViewById(R.id.vp);
        iv_yuyue.setImageResource(R.drawable.yuyue);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new PicFragment()).commit();

    }

    @Override
    public void initData() {
        List<Fragment> list=new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        VpFragmentAdapter vpFragmentAdapter=new VpFragmentAdapter(getSupportFragmentManager(),list,HomeActivity.this);
        vp.setAdapter(vpFragmentAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    iv_yuyue.setImageResource(R.drawable.yuyuehuang);
                    iv_wode.setImageResource(R.drawable.wode);
                }
                else if(position==1)
                {
                    iv_yuyue.setImageResource(R.drawable.yuyue);
                    iv_wode.setImageResource(R.drawable.wodehuang);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.iv_yuyue:
                vp.setVisibility(View.VISIBLE);
                iv_yuyue.setImageResource(R.drawable.yuyuehuang);
                iv_wode.setImageResource(R.drawable.wode);
                vp.setCurrentItem(0);
                fragment.setVisibility(View.GONE);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment1()).commit();
                break;
            case R.id.iv_saoma:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new PicFragment()).commit();
                vp.setVisibility(View.GONE);
                fragment.setVisibility(View.VISIBLE);
                iv_wode.setImageResource(R.drawable.wode);
                iv_yuyue.setImageResource(R.drawable.yuyue);
                break;
            case R.id.iv_wode:
                vp.setVisibility(View.VISIBLE);
                iv_yuyue.setImageResource(R.drawable.yuyue);
                iv_wode.setImageResource(R.drawable.wodehuang);
                vp.setCurrentItem(1);
                fragment.setVisibility(View.GONE);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment2()).commit();
                break;
        }

    }
}
