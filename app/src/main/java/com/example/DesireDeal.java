package com.example;

import android.app.Application;

import org.litepal.LitePal;

public class DesireDeal extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

}
