package com.example.dell.wy_one.view.presenter;

import com.example.dell.wy_one.view.view.interfaces.BaseIView;

public class BasePresenter<V extends BaseIView> {
    private V v;
    //产生关联
    public void attachView(V v1){
        this.v = v1;
    }
    //解除关联
    public void detachView(){
        v = null;
    }
    //返回当前View
    public V getView(){
        return v;
    }



}
