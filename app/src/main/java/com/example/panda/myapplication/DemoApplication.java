package com.example.panda.myapplication;

import android.app.Application;


import com.baidu.mapapi.SDKInitializer;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;


import cn.bmob.v3.Bmob;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        Bmob.initialize(this, "b2a75d2c36f8166500b4c27832a78bb8");
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

}