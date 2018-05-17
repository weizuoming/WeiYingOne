package com.example.dell.wy_one.view.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.wy_one.view.presenter.BasePresenter;
import com.example.dell.wy_one.view.view.interfaces.BaseIView;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseIView{
    private View view;
    private P p;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = getLayout(inflater, container);
        p = newPresenter();
        p.attachView(this);
        initView(view);
         initData(savedInstanceState);
        return view;
    }
    abstract View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);
    abstract void initView(View view);
    abstract void initData(@Nullable Bundle savedInstanceState);
    abstract P newPresenter();
    public P getPresenter(){
        return p;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p.detachView();
    }

}
