package com.basic.xy.smartbulter.entity;

import cn.bmob.v3.BmobUser;

/**
 * 项目名:    SmartBulter
 * 包名:      com.basic.xy.smartbulter.entity
 * 文件名:    MyUser
 * 创建者:    XY
 * 创建时间:   2017/2/17 11:48
 * 描述:       TODO
 */
public class MyUser extends BmobUser{
    private int age;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
