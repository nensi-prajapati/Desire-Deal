package com.example.desiredeal.model;

import android.widget.TextView;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductForuModel extends LitePalSupport implements Serializable {

    private int id;
    private int image;
    private String name;
    private String price;
    private String description;
    private boolean isWishlisted; // new field
    public int count = 1;
    private int initialPrice;

    TextView product_name;

    ArrayList<Integer> relevantimagelist;

    public ProductForuModel() {
    }

    public ProductForuModel(String product_name, String product_image) {
    }

    public ArrayList<Integer> getRelevantimagelist() {
        return relevantimagelist;
    }

    public void setRelevantimagelist(ArrayList<Integer> relevantimagelist) {
        this.relevantimagelist = relevantimagelist;
    }

    public ProductForuModel(int image, String name, ArrayList<Integer> relevantimagelist, String price, String description) {
        this.image = image;
        this.name = name;
        this.relevantimagelist = relevantimagelist;
        this.price = price;
        this.description = description;
        this.isWishlisted = false; // default value is false
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isWishlisted() {
        return isWishlisted;
    }

    public void setWishlisted(boolean wishlisted) {
        isWishlisted = wishlisted;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
