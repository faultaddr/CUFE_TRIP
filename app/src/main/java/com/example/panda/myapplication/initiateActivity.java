package com.example.panda.myapplication;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Message;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.logging.LogRecord;


import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.view.ViewGroup;


import com.umeng.message.PushAgent;

import static anetwork.channel.http.NetworkSdkSetting.context;
import static java.lang.Integer.MAX_VALUE;


public class initiateActivity extends Activity implements OnPageChangeListener {

    private ViewPager mviewPager;
    private PagerAdapter pageradapter;
    private static  boolean record=false;
    public ImageView[] tips;

    private int[] imgIdArray;
    public  ArrayList<ImageView> mImageViews=new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public  int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onJudge();//判断是否是第一次下载应用

        initView();//初始化图片加载

        PushAgent.getInstance(context).onAppStart();
        setContentView(R.layout.activity_initiate);

        mviewPager = (ViewPager) findViewById(R.id.initviewPager);
        pageradapter=new PagerAdapter() {


            @Override
            public int getCount() {
                return MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager) container).removeView(mImageViews.get(position % mImageViews.size()));

            }

            /**
             * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
             */
            @Override
            public Object instantiateItem(View container, int position) {


                ((ViewPager) container).addView(mImageViews.get(position % mImageViews.size()), 0);
                return mImageViews.get(position % mImageViews.size());
            }

        };





    }

    public Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {

            switch(msg.what)
            {
                case 0:
                    record=true;
                    //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
                    mviewPager.setCurrentItem((mImageViews.size()));
                    mviewPager.setOnPageChangeListener(initiateActivity.this);
                    mviewPager.setAdapter(pageradapter);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };
    private void onJudge(){
        SharedPreferences share=getSharedPreferences("first",Activity.MODE_WORLD_READABLE);
        i=share.getInt("first",0);



        if(i==0)    {

            SharedPreferences sharedPreferences = getSharedPreferences("first", Context.MODE_PRIVATE); //私有数据
            SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
            editor.putInt("first", 1);
            editor.commit();//提交修改
        }

        else{
            Intent intent = new Intent();
            intent.setClass(initiateActivity.this, start.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView(){
        imgIdArray = new int[]{R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.forth};




        new Thread() {
            public void run(){
                ImageView imageView1 = new ImageView(initiateActivity.this);
                ImageView imageView2 = new ImageView(initiateActivity.this);
                ImageView imageView3 = new ImageView(initiateActivity.this);
                ImageView imageView4 = new ImageView(initiateActivity.this);
                ArrayList<ImageView> mmImageViews = new ArrayList<>();
                imageView1.setImageResource(imgIdArray[0]);
                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mmImageViews.add(imageView1);
                imageView2.setImageResource(imgIdArray[1]);
                imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mmImageViews.add(imageView2);
                imageView3.setImageResource(imgIdArray[2]);
                imageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mmImageViews.add(imageView3);
                imageView4.setImageResource(imgIdArray[3]);
                imageView4.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mmImageViews.add(imageView4);
                mImageViews.addAll(mmImageViews);
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);

            }
        }.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //System.out.println(position);
        if (position % 4 == 3) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
