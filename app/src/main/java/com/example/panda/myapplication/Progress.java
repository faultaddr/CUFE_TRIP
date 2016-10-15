package com.example.panda.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.umeng.message.PushAgent;

import static anetwork.channel.http.NetworkSdkSetting.context;

public class Progress extends Activity {

        private AnimatedCircleLoadingView animatedCircleLoadingView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_progress);
            PushAgent.getInstance(context).onAppStart();
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
                        Thread.sleep(1500);
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(65);
                            changePercent(i);
                        }
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
}

