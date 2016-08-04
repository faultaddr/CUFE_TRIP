package com.example.panda.myapplication;

import cn.bmob.v3.BmobObject;

/**
 * Created by panda on 2016/7/29.
 */
public class User extends BmobObject {
    private String name;
    private String key;

    public String getName() {
        return name;
    }
    public void setName(String name1) {
        this.name = name1;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key1) {
        this.key = key1;
    }
}
