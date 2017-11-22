package com.example.dell.huangxiaoer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Login2Activity extends BaseActivity {
    private ImageView bt_send;
    private EditText et_tel;
    private EditText et_yzm;
    private Button bt_login;
    private TextView tv_daojishi;


    private int TIME = 30;
    private final int SECOND = 1000;
    private EventHandler eventHandler;


    Handler timeHandler = new Handler();

    Runnable timeRunable = new Runnable() {
        @Override
        public void run() {

            TIME--;

            if (TIME == 0) {
                timeHandler.removeCallbacks(this);
                TIME = 30;
                tv_daojishi.setEnabled(true);
                bt_send.setVisibility(View.VISIBLE);
                bt_send.setImageResource(R.drawable.chongxin);
            }
            else {
                tv_daojishi.setEnabled(false);
                tv_daojishi.setText(TIME + "s后");
                timeHandler.postDelayed(this, SECOND);
            }
        }
    };
    private TextView tv_logintype;
    private ImageView iv_yzm;
    private ImageView iv_xianshi;

    @Override
    public void initView() {
        bt_send = findViewById(R.id.iv_login_yzm);
        et_tel = findViewById(R.id.et_login_tel);
        et_yzm = findViewById(R.id.ed_login_yz);
        bt_login = findViewById(R.id.but_log);
        tv_daojishi = findViewById(R.id.tv_daojishi);
        tv_logintype = findViewById(R.id.tv_logintype);
        iv_yzm = findViewById(R.id.iv_yzm);
        iv_xianshi = findViewById(R.id.iv_xianshi);
        tv_logintype.setOnClickListener(this);
        bt_send.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        tv_logintype.setText("常规登录方式");
        registerSMS();
    }

    private void registerSMS() {
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Login2Activity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //只有回服务器验证成功，才能允许用户登录
                        //回调完成,提交验证码成功
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Login2Activity.this, "服务器验证成功", Toast.LENGTH_SHORT).show();

                                   startActivity(HomeActivity.class);
                                }
                            });

                        }

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login2Activity.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_login2;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.iv_login_yzm:
                if (TextUtils.isEmpty(et_tel.getText().toString())) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                timeHandler.postDelayed(timeRunable, SECOND);
                SMSSDK.getVerificationCode("86", et_tel.getText().toString());
                tv_daojishi.setVisibility(View.VISIBLE);
                bt_send.setVisibility(View.GONE);

            break;

            case R.id.but_log:
                verify();
                SMSSDK.submitVerificationCode("86", et_tel.getText().toString(), et_yzm.getText().toString());



                break;

            case R.id.tv_logintype:
                if(tv_logintype.getText().toString().equals("常规登录方式"))
                {
                    tv_logintype.setText("短信验证码登录方式");
                    iv_yzm.setImageResource(R.drawable.mima);
                    iv_xianshi.setVisibility(View.VISIBLE);
                    bt_send.setVisibility(View.GONE);
                    tv_daojishi.setVisibility(View.GONE);
                    bt_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(Login2Activity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else
                {
                    tv_logintype.setText("常规登录方式");
                    iv_yzm.setImageResource(R.drawable.yzm);
                    iv_xianshi.setVisibility(View.GONE);
                    bt_send.setVisibility(View.VISIBLE);

                }
                break;
        }

    }

    private void verify() {
        if (TextUtils.isEmpty(et_tel.getText().toString())) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
