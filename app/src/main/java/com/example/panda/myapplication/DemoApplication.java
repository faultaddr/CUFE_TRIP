package com.example.panda.myapplication;

import android.app.Application;
import android.media.Image;


import com.baidu.mapapi.SDKInitializer;
import com.example.panda.myapplication.Data.ImageData;


import cn.bmob.v3.Bmob;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        Bmob.initialize(this, "b2a75d2c36f8166500b4c27832a78bb8");
        new Thread() {
            public void run() {
                ImageData.getImageData(getApplicationContext());
                ImageData.getImageUrl(getApplicationContext());
            }
        }.start();
    }

}