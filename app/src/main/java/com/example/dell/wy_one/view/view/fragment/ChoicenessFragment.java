package com.example.dell.wy_one.view.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.view.presenter.BasePresenter;
import com.example.dell.wy_one.view.presenter.ChoicenessPresenter;
import com.example.dell.wy_one.view.view.interfaces.ChoicenessIView;


public class ChoicenessFragment extends BaseFragment<ChoicenessPresenter> implements ChoicenessIView{

    private ChoicenessPresenter choicenessPresenter;
    @Override
    View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_choiceness, container, false);
    }

    @Override
    void initView(View view) {
//        choicenessPresenter = getPresenter();
////        choicenessPresenter.shuju();
    }


    @Override
    void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    ChoicenessPresenter newPresenter() {
        return new ChoicenessPresenter();
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
