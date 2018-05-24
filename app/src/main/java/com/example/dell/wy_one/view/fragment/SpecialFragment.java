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

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.presenter.SpecialPresenter;
import com.example.dell.wy_one.view.adapter.SpecialAdapter;
import com.example.dell.wy_one.view.custom.ObservableScrollView;
import com.example.dell.wy_one.view.interfaces.SpecialIView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SpecialFragment extends BaseFragment<SpecialPresenter> implements SpecialIView {

    @BindView(R.id.recycler_shipin)
    RecyclerView recyclerShipin;

    @BindView(R.id.scrollView_zhuanti)
    ObservableScrollView scrollView;
    @BindView(R.id.smart_refresh2)
    SmartRefreshLayout smart_refresh2;


    Unbinder unbinder;
    private int imageHeight = 500;

    private SpecialPresenter choicenessPresenter;
    private SpecialAdapter specialAdapter;

    @Override
    protected View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_special, container, false);
    }

    @Override
    protected void initView(View view) {

    }


    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {


        choicenessPresenter = getPresenter();
        choicenessPresenter.SpecialShuju();


        //滑动监听
        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
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
    protected SpecialPresenter newPresenter() {
        return new SpecialPresenter();
    }

    @Override
    public void onSuccess(ChoicenessBean choicenessBean) {

        //精彩推荐展示数据
        recyclerShipin.setLayoutManager(new LinearLayoutManager(getActivity()));

        //下拉刷新的监听
        smart_refresh2.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smart_refresh2.finishRefresh(2000);
            }
        });
        //上拉加载的监听
        smart_refresh2.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                smart_refresh2.finishLoadmore(2000);


            }
        });


        recyclerShipin.setLayoutManager(new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL));
        specialAdapter = new SpecialAdapter(getActivity(),choicenessBean);
        recyclerShipin.setAdapter(specialAdapter);
        recyclerShipin.setNestedScrollingEnabled(false);





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
