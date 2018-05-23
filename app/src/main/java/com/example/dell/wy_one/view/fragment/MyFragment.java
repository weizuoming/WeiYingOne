package com.example.dell.wy_one.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.presenter.MyPresenter;
import com.example.dell.wy_one.view.activity.SettingActivity;
import com.example.dell.wy_one.view.interfaces.MyIView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MyFragment extends BaseFragment<MyPresenter> implements MyIView {


    Unbinder unbinder;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.image_shezhi, R.id.relative_lishi, R.id.relative_huancun, R.id.relative_shoucang, R.id.relative_zhuti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_shezhi:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_lishi:
                break;
            case R.id.relative_huancun:
                Toast.makeText(getActivity(),"敬请期待",Toast.LENGTH_LONG).show();
                break;
            case R.id.relative_shoucang:
                break;
            case R.id.relative_zhuti:
                break;
        }
    }
}
