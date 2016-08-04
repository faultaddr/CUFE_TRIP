package com.example.panda.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by panda on 2016/7/28.
 */
public class list_Adapter extends BaseAdapter{
    private Activity context;
    private List<List_info> list;
    public static int count=0;
    public list_Adapter(Activity context, List<List_info> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.list_item, null);
        List_info info = list.get(position);
        TextView textView = (TextView) itemView.findViewById(R.id.list_text);
        ImageView imageView = (ImageView) itemView
                .findViewById(R.id.list_image);
        textView.setText(info.getWeatherText());
        imageView.setImageBitmap(info.getWeatherBitmap());
        return itemView;
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


}