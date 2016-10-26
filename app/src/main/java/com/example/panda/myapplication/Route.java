package com.example.panda.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.widget.LinearLayout;

import com.umeng.message.PushAgent;

import java.util.ArrayList;


import butterknife.ButterKnife;

import static anetwork.channel.http.NetworkSdkSetting.context;


public class Route extends AppCompatActivity implements GestureDetector.OnGestureListener,View.OnTouchListener
{
    LayoutInflater minflater;
    RouteLayout myview1;
    RouteLayout myview2;
    RouteLayout myview3;
    RouteLayout myview4;
    private int count=0;
    private ArrayList<LinearLayout> List=new ArrayList<>();
    private GestureDetector gestureDetector;
    private View.OnTouchListener listener1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //PushAgent.getInstance(context).onAppStart();
        gestureDetector=new GestureDetector(this);
        setContentView(R.layout.activity_route);
        //FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frame);
        //ButterKnife.bind(this)
        // ;
        Log.i("hhhh","jkkk");
        //minflater = getLayoutInflater();
        myview1 = (RouteLayout) findViewById(R.id.view1);
        myview2 = (RouteLayout) findViewById(R.id.view2);
        myview3 = (RouteLayout) findViewById(R.id.view3);
        myview4 = (RouteLayout) findViewById(R.id.view4);
        myview1.setVisibility(View.INVISIBLE);
        myview2.setVisibility(View.INVISIBLE);
        myview3.setVisibility(View.INVISIBLE);
        myview4.setVisibility(View.VISIBLE);
        myview4.setOnTouchListener(this);
        myview3.setOnTouchListener(this);
        myview2.setOnTouchListener(this);
        myview1.setOnTouchListener(this);
        List.add(myview4);
        List.add(myview3);
        List.add(myview2);
        List.add(myview1);
        //myview1.setVisibility(View.VISIBLE);

        //gestureDetector.setIsLongpressEnabled(true);


    }

public void switchframe(){

    count=count%4;
    for(int i=0;i<4;i++){
        List.get(i).setVisibility(View.INVISIBLE);
    }
    List.get(count).setVisibility(View.VISIBLE);


    count++;
}


    public boolean onDown(MotionEvent arg0)

    {

        Log.i("MyGesture", "onDown");

        //Toast.makeText(this, "onDown", Toast.LENGTH_SHORT).show();

        return true;

    }

    /*
     * * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发 *
     * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
     */

    public void onShowPress(MotionEvent e)

    {

        Log.i("MyGesture", "onShowPress");

        //Toast.makeText(this, "onShowPress", Toast.LENGTH_SHORT).show();

    }

    // 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发

    public boolean onSingleTapUp(MotionEvent e)

    {

        Log.i("MyGesture", "onSingleTapUp");

        //Toast.makeText(this, "onSingleTapUp", Toast.LENGTH_SHORT).show();

        return true;

    }

    // 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发


    // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY)

    {

        Log.i("MyGesture", "onScroll");

        //Toast.makeText(this, "onScroll", Toast.LENGTH_LONG).show();

        return true;

    }

    // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发

    public void onLongPress(MotionEvent e)

    {

        Log.i("MyGesture", "onLongPress");

        //Toast.makeText(this, "onLongPress", Toast.LENGTH_LONG).show();

    }
    boolean Gesture=false;
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Gesture=false;
        Log.i(">>>>>","FLING");
        //Log.i(">>>>>",e1.toString()+"<>"+e2.toString());
        //if(abs(e1.getX()-e2.getX())>abs(e1.getY()-e2.getY())){
            switchframe();
            Gesture=true;
        //}
        return Gesture;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


}
