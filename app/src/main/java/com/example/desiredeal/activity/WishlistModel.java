package com.example.desiredeal.activity;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class WishlistModel implements Serializable {
    private String name;
    private String price;
    private String description;
    private int image;

    public WishlistModel(String name, String price, String description, int image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void save() {
        Context context = null;
        SharedPreferences prefs = context.getSharedPreferences("wishlist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", this.name);
        editor.putString("description", this.description);
        editor.putInt("image", this.image);
        editor.putInt("price", Integer.parseInt(this.price));
        editor.apply();
    }


}


