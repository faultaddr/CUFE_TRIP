package com.example.panda.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by panda on 2016/7/26.
 */
public class initViewPagerAdapter extends PagerAdapter implements OnPageChangeListener {
    private ImageView[] imageview;
    public initViewPagerAdapter(ImageView iimageview[]){
        this.imageview=iimageview;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(imageview[position % imageview.length]);

    }
    @Override
    public Object instantiateItem(View container, int position) {
       ((ViewPager)container).addView(imageview[position % imageview.length], 0);
        return imageview[position % imageview.length];
    }
    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public int getCount() {
        return MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}
