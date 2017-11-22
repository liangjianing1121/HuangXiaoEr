package com.example.dell.huangxiaoer;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import adapter.VpAdapter;

public class MainActivity extends BaseActivity {


    private ViewPager vp;
    private ImageView iv_gif;
    private ImageView iv_welcone;

    @Override
    public void initView() {
        vp = findViewById(R.id.vp);
        iv_gif = findViewById(R.id.iv_gif);
        iv_welcone = findViewById(R.id.iv_welcome);
        iv_welcone.setOnClickListener(this);
        SharedPreferences sp = getSharedPreferences("frist", MODE_PRIVATE);
        boolean status = sp.getBoolean("status", false);
        if(status)
        {
            startActivity(Welcomectivity.class);
            finish();
        }
    }
    @Override
    public void initData() {
        //Glide.with(MainActivity.this).load(R.drawable.welcome).asGif().into(iv_gif);
        List<Integer> imgs=new ArrayList<>();
        imgs.add(R.drawable.vp_1);
        imgs.add(R.drawable.vp_2);
        imgs.add(R.drawable.vp_3);
        imgs.add(0);
        VpAdapter vpAdapter=new VpAdapter(imgs,MainActivity.this);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if(position==3)
                {
                    iv_gif.setVisibility(View.VISIBLE);
                    iv_welcone.setVisibility(View.VISIBLE);
                    Glide.with(MainActivity.this).load(R.drawable.welcome).into(new GlideDrawableImageViewTarget(iv_gif,1));

                }
                else
                {
                    iv_welcone.setVisibility(View.GONE);
                    iv_gif.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.iv_welcome:
                SharedPreferences sp = getSharedPreferences("frist", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("status",true);
                edit.commit();
                startActivity(Login2Activity.class);
                finish();
                break;
        }

    }
}
