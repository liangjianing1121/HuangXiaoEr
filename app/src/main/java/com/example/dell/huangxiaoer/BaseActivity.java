package com.example.dell.huangxiaoer;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    public boolean isStatus=false;//沉浸式
    public boolean isShowActionBar=false;//是否显示ActionBar
    public boolean isFullScreen=false;//是否全屏


    public abstract void initView();
    public abstract void initData();
    public abstract int setLayoutID();
    public abstract void setListener();
    public abstract void Click(View view);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutID());
        setStatus(true);
        setFullScreen(true);
        setShowActionBar(false);
        initView();
        setListener();
        initData();

    }
    @Override
    public void onClick(View view) {
        Click(view);
    }


    /**
     * 沉浸式
     * @param b
     */
    public void setStatus(boolean b){
        isStatus=b;
        if(isStatus)
        {
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT)
            {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * 设置是否全屏显示
     * @param b
     */
    public void setFullScreen(boolean b){
        isFullScreen=b;
        if(isFullScreen)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 设置是否显示标题栏
     * @param b
     */
    public void setShowActionBar(boolean b){
        isShowActionBar=b;
        if(isShowActionBar)
        {
            getSupportActionBar().show();
        }
        else
        {
            getSupportActionBar().hide();
        }
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 无参跳转
     */
    public void startActivity(Class<?> clz){
        Intent intent=new Intent(this,clz);
        startActivity(intent);
    }

    /**
     * 有参跳转
     * @param bundle
     * @param clz
     */
    public void startActivity(Bundle bundle,Class<?> clz){
        Intent intent=new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
