package com.example.panda.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Line extends Activity implements OnPageChangeListener {
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
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //载入图片资源ID
        imgIdArray = new int[]{R.drawable.newone, R.drawable.newtwo, R.drawable.new_three, R.drawable.newfour};


        //将点点加入到ViewGroup中
        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LayoutParams(10, 10));
            tips[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }


        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(this);
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }
        //设置Adapter
        viewPager.setAdapter(new ViewPagerAdapter(mImageViews));


        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem((mImageViews.length) * 100);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //System.out.println(position);
        int pos=position%4;
        switch(pos){
            case 0:
                Toast toast1=Toast.makeText(this,line_introduction1,Toast.LENGTH_SHORT);
                toast1.show();
                break;
            case 1:
                Toast toast2=Toast.makeText(this,line_introduction2,Toast.LENGTH_SHORT);
                toast2.show();
                break;
            case 2:
                Toast toast3=Toast.makeText(this,line_introduction3,Toast.LENGTH_SHORT);
                toast3.show();
                break;
            case 3:
                Toast toast4=Toast.makeText(this,line_introduction4,Toast.LENGTH_SHORT);
                toast4.show();
                break;
        }
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
}