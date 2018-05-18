package com.example.dell.wy_one.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.presenter.BasePresenter;
import com.example.dell.wy_one.presenter.SpecialPresenter;
import com.example.dell.wy_one.view.interfaces.SpecialIView;

public class SpecialFragment extends BaseFragment<SpecialPresenter> implements SpecialIView {

    @Override
    View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_special, container, false);
    }

    @Override
    void initView(View view) {

    }


    @Override
    void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    SpecialPresenter newPresenter() {
        return new SpecialPresenter();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
