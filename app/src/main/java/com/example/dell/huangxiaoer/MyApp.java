package com.example.dell.huangxiaoer;

import android.app.Application;
import android.content.Context;
import android.provider.SyncStateContract;

import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by DELL on 2017/11/17.
 */

public class MyApp extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        MobSDK.init(this, "2279be1cc3dcc", "2b503337c03ead047be4e71e42df6e33");

        //BugLy初始化
        CrashReport.initCrashReport(getApplicationContext(), "413768cac8", false);

        //leackCanary初始化
        LeakCanary.install(this);
    }
}
