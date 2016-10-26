package com.example.panda.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.zip.Inflater;

public class gridviewAdapter extends BaseAdapter
{
    // 定义Context
    private Context     mContext;
    // 定义整型数组 即图片源
    private Integer[]   mImageIds   =
            {
                    R.drawable.routline_search,
                    R.drawable.busline_search,
                    R.drawable.poi_search,

            };
    private String mtext[]={
            "路线查询","公交查询","附近查询"
    };
    public gridviewAdapter(Context c)
    {
        mContext = c;
    }

    // 获取图片的个数
    public int getCount()
    {
        return mImageIds.length;
    }

    // 获取图片在库中的位置
    public Object getItem(int position)
    {
        return position;
    }


    // 获取图片ID
    public long getItemId(int position)
    {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view=View.inflate(mContext,R.layout.activity_gridview,null);
        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.RelativeLayout1);

        ImageView image = (ImageView) rl.findViewById(R.id.image_item);
        TextView text = (TextView) rl.findViewById(R.id.text_item);

        image.setImageResource(mImageIds[position]);
        text.setText(mtext[position]);
        return view;
    }

}