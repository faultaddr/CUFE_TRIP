package com.example.panda.myapplication;

import android.app.Activity;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.plus.model.people.Person;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class Route extends AppCompatActivity{
    LayoutInflater minflater;
    LinearLayout myview1;
    LinearLayout myview2;
    LinearLayout myview3;
    LinearLayout myview4;
    private int count=0;
    private float x1;
    private float x2;
    private ArrayList<LinearLayout> List=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frame);
        //ButterKnife.bind(this)
        // ;
        Log.i("hhhh","jkkk");
        minflater = getLayoutInflater();
        myview1 = (LinearLayout) minflater.inflate(R.layout.view1,null);
        myview2 = (LinearLayout) minflater.inflate(R.layout.view2,null );
        myview3 = (LinearLayout) minflater.inflate(R.layout.view3, null);
        myview4 = (LinearLayout) minflater.inflate(R.layout.view4, null);
        myview1.setVisibility(View.INVISIBLE);
        myview2.setVisibility(View.INVISIBLE);
        myview3.setVisibility(View.INVISIBLE);
        myview4.setVisibility(View.INVISIBLE);
        List.add(myview1);
        List.add(myview2);
        List.add(myview3);
        List.add(myview4);
        myview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage();
            }
        });
    }
    private void showImage()
    {
        //myview1.setVisibility(View.VISIBLE);
        count=count%4;
        for(int i=0;i<4;i++)
        {
            LinearLayout v=(LinearLayout) List.get(i);
            v.setVisibility(View.INVISIBLE);
        }
        LinearLayout v=(LinearLayout) List.get(count);
        v.setVisibility(View.INVISIBLE);
        count++;
    }

@Override
    protected void onPause() {
    super.onPause();
}
    @Override
    protected  void onStop(){
        super.onStop();
    }
}
