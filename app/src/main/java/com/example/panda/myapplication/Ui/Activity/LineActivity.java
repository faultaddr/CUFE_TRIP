package com.example.panda.myapplication.Ui.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.example.panda.myapplication.Data.ImageData;
import com.example.panda.myapplication.Data.List_info;
import com.example.panda.myapplication.Model.LoadingView;
import com.example.panda.myapplication.Model.MyListView;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.Ui.Adapter.list_Adapter;

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    //view 初始化
    private static MyListView listView;
    private View footerView;//底部视图
    //数据初始化
    boolean isLoading;// 判断变量
    int totalItemCount;// 总数量
    int lastVisibieItem;// 最后一个可见的item;
    int tempRecord = 0;//分页计数器
    private ArrayList<List_info> arrayList = new ArrayList<>();
    public Bitmap[] bmp = new Bitmap[5];
    String[] text = new String[100];

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        listView = (MyListView) findViewById(R.id.friend_circle);

        //Log.i(">>imageUrl", ImageData.imageUrl.size() + "");
        LoadingView mLoadingView = (LoadingView) findViewById(R.id.loading);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        footerView = layoutInflater.inflate(R.layout.footer_listview, null);


        listView.setEmptyView(mLoadingView);
        listView.setAdapter(new list_Adapter(this,
                ImageData.imageUrl.subList(tempRecord, tempRecord + 14),
                ImageData.imageData.subList(tempRecord,tempRecord + 14)));
        listView.getEmptyView().setVisibility(View.INVISIBLE);
        footerView.findViewById(R.id.footer_layout).setVisibility(View.VISIBLE);//设置底部布局不可见


        listView.addFooterView(footerView);
        listView.setOnScrollListener(this);
        listView.setonRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (tempRecord >= 15) {
                    tempRecord -= 15;
                    listView.setAdapter(new list_Adapter(
                                    LineActivity.this,
                                    ImageData.imageUrl.subList(tempRecord, tempRecord + 15),
                                    ImageData.imageData.subList(tempRecord,tempRecord + 15))
                            );
                }
                listView.onRefreshComplete();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                //Log.i(">>position",position+"");
                intent.putExtra("contentPosition",tempRecord+position);
                intent.setClass(LineActivity.this,TempActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initData() {


        text[0] = "线路1详情                        >>>>";
        text[1] = "线路2详情                        >>>>";
        text[2] = "线路3详情                        >>>>";
        text[3] = "线路4详情                        >>>>";


    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibieItem && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                footerView.setVisibility(View.VISIBLE);
                footerView.findViewById(R.id.footer_layout).setVisibility(View.VISIBLE);
                // 加载更多（获取接口）
                //iLoadListener.onLoad();
                tempRecord += 15;
                listView.setAdapter(new list_Adapter(
                        LineActivity.this,
                        ImageData.imageUrl.subList(tempRecord, tempRecord + 15),
                        ImageData.imageData.subList(tempRecord,tempRecord + 15))
                );
                isLoading = false;
                footerView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibieItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
        footerView.setVisibility(View.VISIBLE);
    }

//        for(int i = 0;i<imageUrl.size();i++)
//
//    {
//        AsynImageLoader asynImageLoader = new AsynImageLoader();
//        asynImageLoader.showImageAsyn((ImageView) mImageViews.get(i), imageUrl.get(i), 0x7f020163);
//        //Log.i("imageUrl",imageUrl.get(i));
//        Log.i(">>>", "url");
//    }
//    }


}

