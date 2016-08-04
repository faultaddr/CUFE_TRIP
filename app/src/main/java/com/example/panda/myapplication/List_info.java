package com.example.panda.myapplication;

import android.graphics.Bitmap;

/**
 * Created by panda on 2016/7/28.
 */
public class List_info {
    /**
     * 代表天气状况的图片
     */
    private Bitmap weatherBitmap;
    /**
     * 具体天气的文字说明
     */
    private String weatherText;

    public List_info(Bitmap weatherBitmap, String weatherText) {
        super();
        this.weatherBitmap = weatherBitmap;
        this.weatherText = weatherText;
    }

    public Bitmap getWeatherBitmap() {
        return weatherBitmap;
    }

    public void setWeatherBitmap(Bitmap weatherBitmap) {
        this.weatherBitmap = weatherBitmap;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

}