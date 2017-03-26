package com.example.panda.myapplication.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by panda on 2016/8/4.
 */
public class enroll_list extends BmobObject {
    private String Name;
    private String Student_id;
    private String Sex;
    private String Line;
    private String Personal_id;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(String student_id) {
        Student_id = student_id;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    public String getPersonal_id() {
        return Personal_id;
    }

    public void setPersonal_id(String personal_id) {
        Personal_id = personal_id;
    }



}
