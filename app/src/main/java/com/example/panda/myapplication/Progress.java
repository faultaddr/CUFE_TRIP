package com.example.panda.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.umeng.message.PushAgent;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import static anetwork.channel.http.NetworkSdkSetting.context;

public class Progress extends Activity {

        private AnimatedCircleLoadingView animatedCircleLoadingView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_progress);
            //PushAgent.getInstance(context).onAppStart();
            animatedCircleLoadingView = (AnimatedCircleLoadingView) findViewById(R.id.circle_loading_view);
            startLoading();
            startPercentMockThread();
        }

        private void startLoading() {
            animatedCircleLoadingView.startDeterminate();
        }

        private void startPercentMockThread() {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(20);
                            changePercent(i);
                        }
                        //download_pic();
                        Intent intent=new Intent();
                        intent.setClass(Progress.this,MainActivity.class);
                        startActivity(intent);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(runnable).start();
        }

        private void changePercent(final int percent) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    animatedCircleLoadingView.setPercent(percent);
                }
            });
        }

        public void resetLoading() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    animatedCircleLoadingView.resetLoading();
                }
            });
        }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void download_pic(){
        Log.i(">>>>>","download_pic");
        BmobQuery<Picture> bmobQuery = new BmobQuery<Picture>();
        bmobQuery.addWhereEqualTo("Id", "main");
//返回50条数据，如果不加上这条语句，默认返回10条数据
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(new FindListener<Picture>() {
            @Override
            public void done(List<Picture> object, BmobException e) {
                if(e==null){
                    Log.i(">>>>>","查询成功：共"+object.size()+"条数据。");
                    for (Picture pic : object) {
                        BmobFile bmobfile = pic.getdata();
                        if(bmobfile!= null){
                            //调用bmobfile.download方法
                            String Name=pic.getName();
                            File saveFile = new File(Environment.getExternalStorageDirectory(), Name);
                            bmobfile.download(saveFile, new DownloadFileListener() {
                                @Override
                                public void done(String s, BmobException e) {
                                    Log.i(">>>>>","done");
                                    Intent intent=new Intent();
                                    intent.setClass(Progress.this,MainActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onProgress(Integer integer, long l) {
                                    changePercent(integer);
                                }
                            });

                        }
                        else{
                            Log.i(">>>>>","bombfile=null");
                        }
                    }
                }else{
                    Log.i(">>>>>","查询失败："+e.getMessage());
                }
            }
        });

    }
}

