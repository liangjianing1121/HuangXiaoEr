package com.example.dell.huangxiaoer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class Welcomectivity extends BaseActivity {

    private ImageView iv_gif;
    private ImageView iv_welcome;

    @Override
    public void initView() {
        iv_gif = findViewById(R.id.iv_gif);
        iv_welcome = findViewById(R.id.iv_welcome);
        iv_welcome.setOnClickListener(this);

    }

    @Override
    public void initData() {
        Glide.with(Welcomectivity.this).load(R.drawable.welcome).into(new GlideDrawableImageViewTarget(iv_gif,1));
    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.iv_welcome:
                startActivity(Login2Activity.class);
                finish();
                break;
        }
    }
}
