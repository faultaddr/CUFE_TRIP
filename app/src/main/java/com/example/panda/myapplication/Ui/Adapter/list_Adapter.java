package com.example.panda.myapplication.Ui.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.myapplication.R;
import com.example.panda.myapplication.Tools.AsynImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 2016/7/28.
 */
public class list_Adapter extends BaseAdapter{
    private Activity context;
    private List<String> list;
    private List<String>dataText;
    public static int count=0;
    public list_Adapter(Activity context, List<String> list, List<String>data) {
        Log.i(">>List",list.size()+"");
        this.context = context;
        this.list = list;
        this.dataText=data;
    }

    private void initData(ImageView imageview,int position) {
        AsynImageLoader asynImageLoader = new AsynImageLoader();
        asynImageLoader.showImageAsyn( imageview,list.get(position),0x7f0200e9);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(">>getView", "success" + "  " + position);
        System.out.print("" + position);
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);
            holder.imageSight=(ImageView)convertView.findViewById(R.id.list_image);
            holder.textSight = (TextView) convertView.findViewById(R.id.list_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //String info = list.get(position);
        holder.textSight.setText(dataText.get(position));
        holder.imageSight.setImageBitmap(null);
        initData(holder.imageSight,position);

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView imageSight;
        TextView textSight;

    }
}