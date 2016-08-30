package com.example.panda.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

import cn.bmob.v3.Bmob;

import static java.lang.Integer.MAX_VALUE;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {
    private static boolean record=false;
    private ImageView Imageview;
    private ImageView Imageview_setting;
    private ImageView Imageview_enroll;
    private ViewPager viewPager;
    private PagerAdapter pageradapter;
    private TextView textview;
    private static ArrayList<List_info> informations = new ArrayList<>();
    private ListView listView;
    public static ArrayList<ImageView> mImageViews = new ArrayList<>();
    private int[] imgIdArray;
    private static String[] textIdArray;
    private String[] text = new String[5];
    public static int count = 0;
    public static Bitmap[] bmp = new Bitmap[5];
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0:
                    record=true;
                    //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
                    viewPager.setCurrentItem((mImageViews.size()) * 100);

                    viewPager.setOnPageChangeListener(MainActivity.this);
                    viewPager.setAdapter(pageradapter);
                    break;
                case 1:
                    listView = (ListView) findViewById(R.id.listview);

                    list_Adapter myListAdapter = new list_Adapter(MainActivity.this, informations);
                    listView.setOnItemClickListener(MainActivity.this);
                    listView.setAdapter(myListAdapter);
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //载入图片资源ID
        imgIdArray = new int[]{R.drawable.newone, R.drawable.newtwo, R.drawable.new_three, R.drawable.newfour};
        textIdArray = new String[]{
                "路线1简介：北京周边一日游\n" +
                        "路线开销：200元/人\n" +
                        "路线人气：***\n",
                "路线2简介：北京周边一日游\n" +
                        "路线开销：180元/人\n" +
                        "路线人气：****\n",
                "路线3简介：北京周边一日游\n" +
                        "路线开销：170元/人\n" +
                        "路线人气：*****\n",
                "路线4简介：北京周边一日游\n" +
                        "路线开销：160元/人\n" +
                        "路线人气：**\n"
        };

        text[0] = "线路1详情                        >>>>";
        text[1] = "线路2详情                        >>>>";
        text[2] = "线路3详情                        >>>>";
        text[3] = "线路4详情                        >>>>";

        new Thread() {
            public void run() {
                Resources res = getResources();
                bmp[0] = BitmapFactory.decodeResource(res, R.drawable.newone_small);
                bmp[1] = BitmapFactory.decodeResource(res, R.drawable.newtwo_small);
                bmp[2] = BitmapFactory.decodeResource(res, R.drawable.newthree_small);
                bmp[3] = BitmapFactory.decodeResource(res, R.drawable.newfour_small);

                for (
                        int i = 0;
                        i < textIdArray.length; i++)

                {
                    List_info list_info = new List_info(bmp[i], text[i]);
                    informations.add(list_info);
                }
                Message msg = new Message();
                msg.what = 1;
                mHandler.sendMessage(msg);
            }
        }.start();

        //将图片\文字装载到数组中
        new Thread() {
            public void run() {
                ImageView imageView1 = new ImageView(MainActivity.this);
                ImageView imageView2 = new ImageView(MainActivity.this);
                ImageView imageView3 = new ImageView(MainActivity.this);
                ImageView imageView4 = new ImageView(MainActivity.this);
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
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.text);
        textview.setText(textIdArray[0]);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        pageradapter = new PagerAdapter() {


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


        //设置点击导航栏路线显示的内容。
        Imageview = (ImageView) findViewById(R.id.main_menu);
        Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //设置Intent的class属性，跳转到SecondActivity
                intent.setClass(MainActivity.this, Line.class);
                //启动Activity
                startActivity(intent);
            }
        });

        Imageview_setting = (ImageView) findViewById(R.id.setting);
        Imageview_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //设置Intent的class属性，跳转到SecondActivity
                intent.setClass(MainActivity.this, SettingsActivity.class);
                //启动Activity
                startActivity(intent);
            }
        });
        Imageview_enroll = (ImageView) findViewById(R.id.enroll);
        Imageview_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //设置Intent的class属性，跳转到SecondActivity
                intent.setClass(MainActivity.this, enroll.class);
                //启动Activity
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //viewPager.setAnimation();
    }

    @Override
    public void onPageSelected(int position) {
        //System.out.println(position);
        int pos = position % 4;
        count = pos;


        textview.setText(textIdArray[count]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        count = i;
        new Thread() {
            public void run() {

                Uri uri;
                //intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                switch (count) {
                    case 0:
                        uri = Uri.parse("http://baike.so.com/doc/4880706-5098585.html");
                        break;
                    case 1:

                        uri = Uri.parse("http://www.chinanews.com/sh/2015/10-05/7555597_2.shtml");

                        break;
                    case 2:

                        uri = Uri.parse("http://baike.so.com/doc/7372469-7640406.html");

                        break;
                    default:

                        uri = Uri.parse("http://baike.baidu.com/view/3067177.html");

                        break;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }.start();
    }

}

