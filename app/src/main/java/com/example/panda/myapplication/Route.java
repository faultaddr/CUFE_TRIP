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
import com.umeng.message.PushAgent;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static anetwork.channel.http.NetworkSdkSetting.context;

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
        PushAgent.getInstance(context).onAppStart();
        setContentView(R.layout.activity_route);
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frame);
        //ButterKnife.bind(this)
        // ;
        Log.i("hhhh","jkkk");
        //minflater = getLayoutInflater();
        myview1 = (LinearLayout) findViewById(R.id.view1);
        myview2 = (LinearLayout) findViewById(R.id.view2);
        myview3 = (LinearLayout) findViewById(R.id.view3);
        myview4 = (LinearLayout) findViewById(R.id.view4);
        myview1.setVisibility(View.INVISIBLE);
        myview2.setVisibility(View.INVISIBLE);
        myview3.setVisibility(View.INVISIBLE);
        myview4.setVisibility(View.VISIBLE);
        myview4.setOnTouchListener(new View.OnTouchListener() {

            @Override

            public boolean onTouch(View v, MotionEvent event) {
                Log.i("ssss","---->>>>4");
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        switchframe();

                }
                return true;
            }
        });
    myview3.setOnTouchListener(new View.OnTouchListener() {

    @Override

    public boolean onTouch(View v, MotionEvent event) {
        Log.i("ssss","---->>>>3");
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                switchframe();

        }
        return true;
    }
});
        myview2.setOnTouchListener(new View.OnTouchListener() {

            @Override

            public boolean onTouch(View v, MotionEvent event) {
                Log.i("ssss","---->>>>2");
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        switchframe();

                }
                return true;
            }
        });
        myview1.setOnTouchListener(new View.OnTouchListener() {

            @Override

            public boolean onTouch(View v, MotionEvent event) {
                Log.i("ssss","---->>>>1");
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        switchframe();

                }
                return true;
            }
        });
        List.add(myview4);
        List.add(myview3);
        List.add(myview2);
        List.add(myview1);
        //myview1.setVisibility(View.VISIBLE);



    }
public void switchframe(){
    count++;
    count=count%4;
    for(int i=0;i<4;i++){
        List.get(i).setVisibility(View.INVISIBLE);
    }
    List.get(count).setVisibility(View.VISIBLE);
    List.get(count).setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                switchframe();
            }
            return true;
        }
    });
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
