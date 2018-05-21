package com.example.dell.wy_one.view.initialize;


import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

//initialize初始化
public class InitializeApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
