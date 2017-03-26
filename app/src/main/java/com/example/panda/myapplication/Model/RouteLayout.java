package com.example.panda.myapplication.Model;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by panda on 2016/10/19.
 */

public class RouteLayout extends LinearLayout {
    //private static final String TAG="RouteLayout";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;

    private int mLastX=0;
    private int mLastY=0;

    private int mLastXIntercept=0;
    private int mLastYIntercept=0;

    private Scroller mScroller=new Scroller(getContext());;
    private VelocityTracker mVeocityTracker= VelocityTracker.obtain();

    public RouteLayout(Context context) {
        super(context);
    }

    public RouteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RouteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        boolean intercepted=false;
        int x=(int)event.getX();
        int y=(int)event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                intercepted=false;
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                    intercepted=true;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                int deltaX=x-mLastXIntercept;
                int deltaY=y-mLastYIntercept;
                if(Math.abs(deltaX)>Math.abs(deltaY)){
                    intercepted=true;
                }else{
                    intercepted=false;
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                intercepted=false;
                break;
            }
            default:
                break;
        }
        mLastX=x;
        mLastY=y;
        mLastXIntercept=x;
        mLastYIntercept=y;

        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mVeocityTracker.addMovement(event);
        int x=(int)event.getX();
        int y=(int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                int deltaX=x-mLastX;
                int deltaY=y-mLastY;
                scrollBy(-deltaX,0);
                break;

            }
            case MotionEvent.ACTION_UP:{
                int scrollX=getScrollX();
                if(mChildWidth!=0){
                int scrollToChildIndex=scrollX/mChildWidth;
                }
                mVeocityTracker.computeCurrentVelocity(1000);
                float xVelocity=mVeocityTracker.getXVelocity();
                if(Math.abs(xVelocity)>=50){
                    mChildIndex=xVelocity>0?mChildIndex-1:mChildIndex+1;
                }
                else{
                    if(mChildWidth!=0){
                    mChildIndex=(scrollX+mChildWidth/2)/mChildWidth;
                }
                }
                mChildIndex=Math.max(0,Math.min(mChildIndex,mChildrenSize-1));
                int dx=mChildIndex*mChildWidth-scrollX;
                smoothScrollBy(dx,0);
                mVeocityTracker.clear();
                break;
            }
            default:
                break;
        }
        mLastX=x;
        mLastY=y;
        return true;
    }
    private void smoothScrollBy(int dx,int dy){
        mScroller.startScroll(getScrollX(),0,dx,0,500);
        invalidate();
    }
    @Override
    public void computeScroll(){
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
