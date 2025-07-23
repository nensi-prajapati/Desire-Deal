package com.example.desiredeal.model;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;


public class UserDetailModel extends LitePalSupport implements Serializable {

    private String name,name2,phonenumber,imagepath;

    public UserDetailModel() {
        // Default constructor
    }

    public UserDetailModel(String name, String name2, String phonenumber,String imagepath) {
        this.name = name;
        this.name2 = name2;
        this.phonenumber = phonenumber;
        this.imagepath = imagepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
