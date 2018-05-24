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
import com.example.dell.wy_one.model.bean.WelfareBean;
import com.example.dell.wy_one.model.http.RetrofitUtils;
import com.example.dell.wy_one.presenter.WelfarePresenter;
import com.example.dell.wy_one.view.adapter.WelfareAdapter;
import com.example.dell.wy_one.view.custom.ObservableScrollView;
import com.example.dell.wy_one.view.custom.SpacesItemDecoration;
import com.example.dell.wy_one.view.interfaces.WelfareView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

public class WelfareFragment extends BaseFragment<WelfarePresenter> implements WelfareView {


    @BindView(R.id.recycler_welfare)
    RecyclerView recyclerWelfare;
    @BindView(R.id.scrollView_welfare)
    ObservableScrollView scrollViewWelfare;
    @BindView(R.id.welfare_refresh)
    SmartRefreshLayout welfareRefresh;
    Unbinder unbinder;
    @BindView(R.id.back_welfare)
    ImageView backWelfare;
    private int imageHeight = 500;
    private int page = 1;
    private WelfarePresenter welfarePresenter;
    private WelfareAdapter welfareAdapter;
    private List<WelfareBean.ResultsBean> list = new ArrayList<>();
    private Map<String, String> map;

    @Override
    protected View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_welfare, container, false);
    }

    @Override
    protected void initView(View view) {
        backWelfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }


    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

        welfarePresenter = getPresenter();
        welfarePresenter.WelfareShuju();
        //滑动监听
    }

    @Override
    WelfarePresenter newPresenter() {
        return new WelfarePresenter();
    }


    @Override
    public void onSuccess(ResponseBody responseBody) {
        //精彩推荐展示数据
        recyclerWelfare.setLayoutManager(new LinearLayoutManager(getActivity()));

        //下拉刷新的监听
        welfareRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                welfareRefresh.finishRefresh(2000);

            }
        });
        //上拉加载的监听
        welfareRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                map = new HashMap<>();
                RetrofitUtils.getService().getWelfare("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/"+page, map);

                welfareRefresh.finishLoadmore(2000);


            }
        });
        try {
            final WelfareBean fuLiBean = new Gson().fromJson(responseBody.string(), WelfareBean.class);
            List<WelfareBean.ResultsBean> results = fuLiBean.getResults();
            list.addAll(results);
            recyclerWelfare.setLayoutManager(new StaggeredGridLayoutManager
                    (2, StaggeredGridLayoutManager.VERTICAL));
            SpacesItemDecoration decoration=new SpacesItemDecoration(16);
            recyclerWelfare.addItemDecoration(decoration);
            welfareAdapter = new WelfareAdapter(getActivity(), list);
            recyclerWelfare.setAdapter(welfareAdapter);
            recyclerWelfare.setNestedScrollingEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
