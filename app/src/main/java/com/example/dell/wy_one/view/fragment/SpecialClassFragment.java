package com.example.dell.wy_one.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.SpecialClassBean;
import com.example.dell.wy_one.presenter.SpecialClassPresenter;
import com.example.dell.wy_one.view.adapter.SpecialClassAdapter;
import com.example.dell.wy_one.view.custom.ObservableScrollView;
import com.example.dell.wy_one.view.interfaces.OnItemListner;
import com.example.dell.wy_one.view.interfaces.SpecialIClassView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SpecialClassFragment extends BaseFragment<SpecialClassPresenter> implements SpecialIClassView {


    @BindView(R.id.recycler_SpecialClass)
    RecyclerView recyclerSpecialClass;
    @BindView(R.id.scrollView_SpecialClass)
    ObservableScrollView scrollViewSpecialClass;
    @BindView(R.id.smart_SpecialClass)
    SmartRefreshLayout smartSpecialClass;
    Unbinder unbinder;
    @BindView(R.id.back_special_class)
    ImageView backSpecialClass;
    private int imageHeight = 500;

    private SpecialClassPresenter specialClassPresenter;
    private SpecialClassAdapter specialAdapter;

    @Override
    protected View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_special_class, container, false);

    }

    @Override
    protected void initView(View view) {
        backSpecialClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SpecialClassActivity.class);
//                startActivity(intent);
                onDestroy();
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        specialClassPresenter = getPresenter();
        specialClassPresenter.SpecialClassShuju();
        //滑动监听
        scrollViewSpecialClass.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    //AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                } else {

                }
            }
        });
    }

    @Override
    public void onSuccess(SpecialClassBean specialClassBean) {
        //精彩推荐展示数据
        recyclerSpecialClass.setLayoutManager(new LinearLayoutManager(getActivity()));

        //下拉刷新的监听
        smartSpecialClass.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartSpecialClass.finishRefresh(2000);
            }
        });
        //上拉加载的监听
        smartSpecialClass.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                smartSpecialClass.finishLoadmore(2000);


            }
        });


        recyclerSpecialClass.setLayoutManager(new StaggeredGridLayoutManager
                (3, StaggeredGridLayoutManager.VERTICAL));
        specialAdapter = new SpecialClassAdapter(getActivity(), specialClassBean);
        recyclerSpecialClass.setAdapter(specialAdapter);
        recyclerSpecialClass.setNestedScrollingEnabled(false);


        specialAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int position) {
//                Intent intent = new Intent(getActivity(), SpecialClassActivity.class);
////               intent.putExtra("shareURL",beans.get(position).getChildList().get(position).getShareURL());
//                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }


    @Override
    protected SpecialClassPresenter newPresenter() {
        return new SpecialClassPresenter();
    }

    @Override
    public void onError(Throwable throwable) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

}
