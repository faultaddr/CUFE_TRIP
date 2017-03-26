package com.example.panda.myapplication.Tools;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by panda on 2016/10/24.
 */

public class Picture extends BmobObject {
    private String Id;
    private String Name;
    private BmobFile data;

    //get and set 方法
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public BmobFile getdata() {
        return data;
    }

    public void setdata(BmobFile Data) {
        data = Data;
    }



}
