package com.example.panda.myapplication;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Message;

import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;



import java.util.logging.LogRecord;


import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.view.ViewGroup;




public class initiateActivity extends Activity implements OnPageChangeListener {

    public Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            ImageView imageView1 = new ImageView(initiateActivity.this);
            ImageView imageView2= new ImageView(initiateActivity.this);
            ImageView imageView3 = new ImageView(initiateActivity.this);
            ImageView imageView4 = new ImageView(initiateActivity.this);
            switch(msg.what)
            {
                case 0:
                    imageView1.setImageResource(imgIdArray[0]);
                    mImageViews[0] = imageView1;


                    imageView2.setImageResource(imgIdArray[1]);
                    mImageViews[1] = imageView2;


                    imageView3.setImageResource(imgIdArray[2]);
                    mImageViews[2] = imageView3;


                    imageView4.setImageResource(imgIdArray[3]);
                    mImageViews[3] = imageView4;
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };
    private final String line_introduction1 =
            "路线简介：紫禁城一日游\n" +
                    "路线开销：200元/人\n" +
                    "人气指数: *** \n";
    private final String line_introduction2 =
            "路线简介：欢乐谷一日游\n" +
                    "路线开销：230元/人\n" +
                    "人气指数: **** \n";
    private final String line_introduction3 =
            "路线简介：平谷一日游\n" +
                    "路线开销：100元/人\n" +
                    "人气指数: ** \n";
    private final String line_introduction4 =
            "路线简介：十渡一日游\n" +
                    "路线开销：150元/人\n" +
                    "人气指数: *** \n";
    /**
     * ViewPager
     */
    private ViewPager viewPager;

    /**
     * 装点点的ImageView数组
     */
    public ImageView[] tips;

    /**
     * 装ImageView数组
     */
    public static ImageView[] mImageViews;

    /**
     * 图片资源id
     */
    private int[] imgIdArray;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences share=getSharedPreferences("first",Activity.MODE_WORLD_READABLE);

        int i=share.getInt("first",0);
        if(i==0);//如果是第一次进入则继续执行，否则跳过initActivity.
        else{
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_initiate);
        viewPager = (ViewPager) findViewById(R.id.initviewPager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        //载入图片资源ID
        imgIdArray = new int[]{R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.forth};

        //做初次登陆设置
        SharedPreferences sharedPreferences = getSharedPreferences("first", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("first", 1);
        editor.commit();//提交修改




        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        new Thread() {
            public void run(){
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);
        }
        }.start();
        //设置Adapter
        Log.d("viewpager== null ? " , String.valueOf((viewPager== null)));
        viewPager.setAdapter(new initViewPagerAdapter(mImageViews));


        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        //viewPager.setCurrentItem((mImageViews.length) * 100);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
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
