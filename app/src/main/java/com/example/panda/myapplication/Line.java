package com.example.panda.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageView;





import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static java.lang.Integer.MAX_VALUE;

public class Line extends AppCompatActivity implements OnPageChangeListener {
    private static int k=0;

    private final String line_introduction1=
            "路线简介：紫禁城一日游\n" +
                    "路线开销：200元/人\n" +
                    "人气指数: *** \n";
    private final String line_introduction2=
            "路线简介：欢乐谷一日游\n" +
                    "路线开销：230元/人\n" +
                    "人气指数: **** \n";
    private final String line_introduction3=
            "路线简介：平谷一日游\n" +
                    "路线开销：100元/人\n" +
                    "人气指数: ** \n";
    private final String line_introduction4=
            "路线简介：十渡一日游\n" +
                    "路线开销：150元/人\n" +
                    "人气指数: *** \n";

    private ViewPager viewPager;

    private PagerAdapter pageradapter;


    public static ArrayList<ImageView> mImageViews=new ArrayList<>();

    private TextView textView;
    private int[] imgIdArray;
    private int[] textArray;
    public Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {

            switch(msg.what)
            {
                case 0:
                    viewPager.setAdapter(pageradapter);
                    //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
                    viewPager.setCurrentItem((mImageViews.size()) * 100);
                    viewPager.setOnPageChangeListener(Line.this);

                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        textView=(TextView) findViewById(R.id.mtext);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //载入图片资源ID
        imgIdArray = new int[]{R.drawable.newone, R.drawable.newtwo, R.drawable.new_three, R.drawable.newfour};
        textArray=new int[]{
                R.string.point1,R.string.point2,R.string.point1,R.string.point2};



        new Thread() {
            public void run(){
                ImageView imageView1 = new ImageView(Line.this);
                ImageView imageView2 = new ImageView(Line.this);
                ImageView imageView3 = new ImageView(Line.this);
                ImageView imageView4 = new ImageView(Line.this);
                ArrayList<ImageView>mmImageViews=new ArrayList<>();
                imageView1.setBackgroundResource(imgIdArray[0]);
                mmImageViews.add( imageView1);
                imageView2.setBackgroundResource(imgIdArray[1]);
                mmImageViews.add( imageView2);
                imageView3.setBackgroundResource(imgIdArray[2]);
                mmImageViews.add( imageView3);
                imageView4.setBackgroundResource(imgIdArray[3]);
                mmImageViews.add( imageView4);
                mImageViews.addAll(mmImageViews);
                textView.setText(textArray[0]);
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);

            }
        }.start();


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


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //System.out.println(position);
        int pos=position%4;
        k=pos;
        switch(pos){
            case 0:
                Toast toast1=Toast.makeText(getApplicationContext(),line_introduction1,Toast.LENGTH_SHORT);
                toast1.show();
                textView.setText(textArray[pos]);
                break;
            case 1:
                Toast toast2=Toast.makeText(getApplicationContext(),line_introduction2,Toast.LENGTH_SHORT);
                toast2.show();
                textView.setText(textArray[pos]);
                break;
            case 2:
                Toast toast3=Toast.makeText(getApplicationContext(),line_introduction3,Toast.LENGTH_SHORT);
                toast3.show();
                textView.setText(textArray[pos]);
                break;
            case 3:
                Toast toast4=Toast.makeText(getApplicationContext(),line_introduction4,Toast.LENGTH_SHORT);
                toast4.show();
                textView.setText(textArray[pos]);

                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理

    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
//        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理

    }
    // 初始化View



}