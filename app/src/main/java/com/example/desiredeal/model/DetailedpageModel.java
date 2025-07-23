package com.example.desiredeal.model;

import java.io.Serializable;

public class DetailedpageModel implements Serializable {
    private int image;

    public DetailedpageModel(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
