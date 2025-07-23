package com.example.desiredeal.model;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class MyAccountRecyclerModel extends LitePalSupport implements Serializable {
    private int id;
    private String name;
    private String addres_first;
    private String address_second;
    private String state;
    private String phonenumber;
    private String zip_code;
    private boolean selected;

    private String getImagePath;

    public MyAccountRecyclerModel() {
        // Default constructor
    }


    public MyAccountRecyclerModel(String name, String addres_first, String address_second, String state, String phonenumber, String zip_code) {
        this.name = name;
        this.addres_first = addres_first;
        this.address_second = address_second;
        this.state = state;
        this.phonenumber = phonenumber;
        this.zip_code = zip_code;
        this.selected = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres_first() {
        return addres_first;
    }

    public void setAddres_first(String addres_first) {
        this.addres_first = addres_first;
    }

    public String getAddress_second() {
        return address_second;
    }

    public void setAddress_second(String address_second) {
        this.address_second = address_second;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getImagePath() {
        return getImagePath;
    }

    public void setImagePath(String toString) {
        this.getImagePath = getImagePath;
    }
}
