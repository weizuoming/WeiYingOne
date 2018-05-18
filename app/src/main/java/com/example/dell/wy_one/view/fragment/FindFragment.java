package com.example.dell.wy_one.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.dell.wy_one.R;
import com.example.dell.wy_one.presenter.BasePresenter;
import com.example.dell.wy_one.presenter.FindPresenter;
import com.example.dell.wy_one.view.interfaces.FindIView;


public class FindFragment extends BaseFragment<FindPresenter> implements FindIView{

    @Override
    View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    void initView(View view) {

    }


    @Override
    void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    FindPresenter newPresenter() {
        return new FindPresenter();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
