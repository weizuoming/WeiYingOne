package com.example.dell.wy_one.view.initialize;


import android.app.Application;

import com.example.dell.wy_one.view.custom.DensityHelper;
import com.facebook.drawee.backends.pipeline.Fresco;

//initialize初始化
public class InitializeApp extends Application{
    private float DESIGN_WIDTH = 720;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        new DensityHelper(this, DESIGN_WIDTH).activate();
        //DESIGN_WIDTH为设计图宽度，同样不要忘记清单文件配置Application，另 布局中使用pt
    }
}
