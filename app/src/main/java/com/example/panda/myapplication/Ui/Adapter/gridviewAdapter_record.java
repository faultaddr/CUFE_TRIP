package com.example.panda.myapplication.Ui.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.panda.myapplication.R;

import java.util.zip.Inflater;

public class gridviewAdapter_record extends BaseAdapter
{
    // 定义Context
    private Context     mContext;
    // 定义整型数组 即图片源
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
    public gridviewAdapter_record(Context c)
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
        View view=View.inflate(mContext,R.layout.activity_gridview_record,null);
        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.RelativeLayout2);

        ImageView image = (ImageView) rl.findViewById(R.id.image_item_record);
        TextView text = (TextView) rl.findViewById(R.id.text_item_record);

        image.setImageResource(mImageIds[position]);
        text.setText(mtext[position]);
        return view;
    }

}