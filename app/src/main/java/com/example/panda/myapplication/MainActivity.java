package com.example.panda.myapplication;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.panda.myapplication.search.BusLineSearchDemo;
import com.example.panda.myapplication.search.PoiSearchDemo;
import com.example.panda.myapplication.search.RoutePlanDemo;
import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;





import static anetwork.channel.http.NetworkSdkSetting.context;
import static java.lang.Integer.MAX_VALUE;



public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {
    private  boolean record = false;
    private LinearLayout Imageview;
    private LinearLayout Imageview_setting;
    private LinearLayout Imageview_enroll;
    private LinearLayout Imageview_mine;
    private ImageView weather;
    private ImageView calender;
    private ImageView require;
    private ImageView dating;
    private ViewPager viewPager;
    private ViewPager viewPager1;
    private PagerAdapter pageradapter;
    private PagerAdapter pageradapter1;
    private TextView textview;
    private  ArrayList<List_info> informations = new ArrayList<>();
    private ListView listView;
    public  ArrayList<ImageView> mImageViews = new ArrayList<>();
    public  ArrayList<ImageView> mImageViews1 = new ArrayList<>();
    private int[] imgIdArray;
    private int[] imgIdArray1;
    private  String[] textIdArray;
    private String[] text = new String[5];
    public  int count = 0;
    public  Bitmap[] bmp = new Bitmap[5];
    private ScheduledExecutorService scheduledExecutorService;
    private boolean judge=false;
    private int current=0;
    private int current1=0;
    private Integer[]   mImageIds   =
            {
                    R.drawable.newone_small,
                    R.drawable.newtwo_small,
                    R.drawable.newthree_small,
                    R.drawable.newfour_small

            };
    private String mtext[]={
            "路线一","路线二","路线三","路线四"
    };
/*
TODO:做图片的异步加载，从Bmob中获取图片地址，然后按加载网络图片的方法去加载图片。
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        //PushAgent.getInstance(context).onAppStart();
        //Bmob.initialize(this, "b2a75d2c36f8166500b4c27832a78bb8");
        // Set up the login form.
        // 使用推送服务时的初始化操作
        //BmobInstallation.getCurrentInstallation().save();
        // 启动推送服务
        //BmobPush.startWork(this);
        //BmobPushManager bmobPushManager = new BmobPushManager();


        //载入图片资源ID
        InitView();




        setContentView(R.layout.activity_main);
        GridView gridView=(GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new gridviewAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:

                        Intent intent=new Intent();
                        intent.setClass(MainActivity.this,RoutePlanDemo.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent();
                        intent1.setClass(MainActivity.this, BusLineSearchDemo.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent();
                        intent2.setClass(MainActivity.this, PoiSearchDemo.class);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }
        });
        textview = (TextView) findViewById(R.id.text);
        textview.setText(textIdArray[0]);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager1.setPageTransformer(true, new ZoomOutPageTransformer());

        //设置适配器
        PagerAdapter();



        weather = (ImageView) findViewById(R.id.weather);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;
                uri = Uri.parse("http://www.weather.com.cn/weather/101010100.shtml");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        calender = (ImageView) findViewById(R.id.calender);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent();
                    ComponentName cn = null;
                    if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
                        cn = new ComponentName("com.android.calendar",
                                "com.android.calendar.LaunchActivity");

                    } else {
                        cn = new ComponentName("com.google.android.calendar",
                                "com.android.calendar.LaunchActivity");
                    }
                    i.setComponent(cn);
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    // TODO: handle exception
                    Log.e("NotFoundException", e.toString());
                }
            }
        });
        require = (ImageView) findViewById(R.id.require);
        require.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Uri uri = Uri.parse("tel:17888823635");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
        dating = (ImageView) findViewById(R.id.dating);
        dating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
                intent.setType("text/plain"); // 分享发送的数据类型
                String msg = "推荐给大家";
                intent.putExtra(Intent.EXTRA_TEXT, msg); // 分享的内容
                startActivity(Intent.createChooser(intent, "选择分享"));// 目标应用选择对话框的标题
            }
        });
        //设置点击导航栏路线显示的内容。

/*
fragment 碎片显示
路线介绍
 */
        RelativeLayout r1=(RelativeLayout)findViewById(R.id.Relative1);
        RelativeLayout r2=(RelativeLayout)findViewById(R.id.Relative2);
        RelativeLayout r3=(RelativeLayout)findViewById(R.id.Relative3);
        RelativeLayout r4=(RelativeLayout)findViewById(R.id.Relative4);

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent();
                intent.putExtra("num","1");
                intent.setClass(MainActivity.this,TempActivity.class);
                startActivity(intent);
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("num","2");
                intent.setClass(MainActivity.this,TempActivity.class);
                startActivity(intent);
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("num","3");
                intent.setClass(MainActivity.this,TempActivity.class);
                startActivity(intent);
            }
        });

        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("num","4");
                intent.setClass(MainActivity.this,TempActivity.class);
                startActivity(intent);
            }
        });


        LayoutInflater inflater=null;
        inflater = getLayoutInflater();
        Imageview = (LinearLayout) findViewById(R.id.main_menu);
        Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //设置Intent的class属性，跳转到Route
                intent.setClass(MainActivity.this, Route.class);
                //启动Activity
                startActivity(intent);
            }
        });

        Imageview_setting = (LinearLayout) findViewById(R.id.setting);
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
        Imageview_enroll = (LinearLayout) findViewById(R.id.enroll);
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
        Imageview_mine=(LinearLayout)findViewById(R.id.mine);
        Imageview_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,Mine.class);
                startActivity(intent);
            }
        });

    }

    private void InitView() {
/*
        imgIdArray = new int[]{R.drawable.newone, R.drawable.newtwo, R.drawable.newthree, R.drawable.newfour};
  */
        imgIdArray1 = new int[]{R.drawable.view5, R.drawable.view6, R.drawable.view7, R.drawable.view8};
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
                for (int i = 0;i < textIdArray.length; i++) {
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
                mmImageViews.add(imageView1);
                mmImageViews.add(imageView2);
                mmImageViews.add(imageView3);
                mmImageViews.add(imageView4);
                mImageViews.addAll(mmImageViews);

                ImageView imageView11 = new ImageView(MainActivity.this);
                ImageView imageView22 = new ImageView(MainActivity.this);
                ImageView imageView33 = new ImageView(MainActivity.this);
                ImageView imageView44 = new ImageView(MainActivity.this);
                ArrayList<ImageView> mmImageViews1 = new ArrayList<>();
                mmImageViews1.add(imageView11);
                mmImageViews1.add(imageView22);
                mmImageViews1.add(imageView33);
                mmImageViews1.add(imageView44);
                mImageViews1.addAll(mmImageViews1);
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);
            }
        }.start();


    }

    private void PagerAdapter(){


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
                Log.i("destroy--->>>",""+position);
                ((ViewPager) container).removeView(mImageViews.get(position % mImageViews.size()));

            }

            /**
             * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
             */

            @Override
            public Object instantiateItem(View container, int position) {
                Log.i("pos--->>",""+position);
                int pos = position % 4;
                current = position;
                textview.setText(textIdArray[pos]);
                mImageViews.get(position % mImageViews.size()).setScaleType(ImageView.ScaleType.FIT_XY);
                ((ViewPager) container).addView(mImageViews.get(position % mImageViews.size()), 0);
                return mImageViews.get(position % mImageViews.size());

            }

        };

        pageradapter1 = new PagerAdapter() {


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
                ((ViewPager) container).removeView(mImageViews1.get(position % mImageViews1.size()));

            }

            /**
             * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
             */
            @Override
            public Object instantiateItem(View container, int position) {
                current1=position;
                mImageViews1.get(position % mImageViews1.size()).setBackgroundResource(imgIdArray1[position % mImageViews1.size()]);
                ((ViewPager) container).addView(mImageViews1.get(position % mImageViews1.size()), 0);
                return mImageViews1.get(position % mImageViews1.size());
            }

        };
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0:
                    record = true;
                    //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动


                    //viewPager.setCurrentItem((mImageViews.size()) * 100);

                    //viewPager.setOnPageChangeListener(MainActivity.this);
                    String Url[]=new String[]{"http://bmob-cdn-7049.b0.upaiyun.com/2016/10/24/0c7a7005405a1d87803cf6a44eaf54aa.png","http://bmob-cdn-7049.b0.upaiyun.com/2016/10/24/2951776740a4af9e80883db571bcb301.png","http://bmob-cdn-7049.b0.upaiyun.com/2016/10/24/d4caeabf40e76e9b80597c48fded760a.png","http://bmob-cdn-7049.b0.upaiyun.com/2016/10/24/9701488f40e44533807b2508b749e980.png"};

                    for(int i=0;i<4;i++) {
                        AsynImageLoader asynImageLoader = new AsynImageLoader();
                        asynImageLoader.showImageAsyn((ImageView) mImageViews.get(i),Url[i],0x7f020163);
                    }
                    viewPager.setAdapter(pageradapter);
                    viewPager1.setAdapter(pageradapter1);
                    break;
                case 1:
                    listView = (ListView) findViewById(R.id.listview);

                    list_Adapter myListAdapter = new list_Adapter(MainActivity.this, informations);
                    listView.setOnItemClickListener(MainActivity.this);
                    listView.setAdapter(myListAdapter);
                    break;
                case 2:
                    //viewPager1.setCurrentItem((mImageViews1.size()) * 100);

                    //viewPager1.setOnPageChangeListener(MainActivity.this);

                    break;
                case 3:

                    viewPager.setCurrentItem(current);
                    viewPager1.setCurrentItem(current1);
                    break;
            }
            super.handleMessage(msg);
        }

    };
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            //actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);

        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //viewPager.setAnimation();
        judge=true;
        if(judge!=false){
            scheduledExecutorService.shutdown();
        }

    }

    @Override
    public void onPageSelected(int position) {
        //System.out.println(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔2秒钟切换一张图片

        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 3, 4, TimeUnit.SECONDS);


    }

    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = 3;
            mHandler.sendMessage(msg);
        }

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
@Override
    protected void onPause(){
    super.onPause();
    scheduledExecutorService.shutdown();
}
    @Override
    protected  void onStop(){
        super.onStop();
    }
}

