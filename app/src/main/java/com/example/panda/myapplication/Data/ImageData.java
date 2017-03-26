package com.example.panda.myapplication.Data;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panyunyi on 2017/3/25.
 */

public class ImageData {
    public static ArrayList<String> imageData=new ArrayList<>();
    public static ArrayList<String>imageUrl=new ArrayList<>();
    public static boolean TAG_URl=false;
    public static boolean TAG_Data=false;
    public static  ArrayList<String> getImageUrl(final Context context)  {
        if(imageUrl.size()>100){
            return imageUrl;
        }
         new Thread() {
             public void run() {
                 try{
                 readFileByLines(context.getClass().getClassLoader().getResourceAsStream("assets/" + "image.txt"),imageUrl);
                 TAG_URl=true;
                 }catch (Exception ex){
                     ex.printStackTrace();
                 }
            }
        }.start();
        while(TAG_URl==false) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return imageUrl;
    }
    public static ArrayList<String> getImageData(final Context context){
        if(imageData.size()>100){
            return imageData;
        }
        new Thread() {
            public void run() {
                try{
                readFileByLines(context.getClass().getClassLoader().getResourceAsStream("assets/" + "data.txt"),imageData);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                TAG_Data=true;
            }
        }.start();
        while(TAG_Data==false) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return imageData;
    }
    public static synchronized void readFileByLines(InputStream fileName, ArrayList<String>imageInfo) {
        //File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(fileName));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                imageInfo.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }

        }
    }

}
