package com.example.panda.myapplication;

/**
 * Created by panda on 2016/8/7.
 */

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import static java.lang.Integer.MAX_VALUE;



/**
 * Created by panda on 2016/7/23.
 */
public class lineViewPager extends PagerAdapter implements ViewPager.OnPageChangeListener{
    private ImageView[] mImageViews;

    private static int count=0;
    public lineViewPager(ImageView imgview[]){
        this.mImageViews=imgview;

    }

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
        ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);

    }

    /**
     * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
     */
    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
        return mImageViews[position % mImageViews.length];
    }
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {

    }


}