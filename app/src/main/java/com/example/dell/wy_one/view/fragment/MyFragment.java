package com.example.dell.wy_one.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.presenter.MyPresenter;
import com.example.dell.wy_one.view.interfaces.MyIView;


public class MyFragment extends BaseFragment<MyPresenter> implements MyIView {


    @Override
    protected View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initView(View view) {

    }


    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected MyPresenter newPresenter() {
        return new MyPresenter();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
