package com.example.dell.wy_one.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.CommentBean;
import com.example.dell.wy_one.presenter.BasePresenter;
import com.example.dell.wy_one.presenter.CommentPresenter;
import com.example.dell.wy_one.view.adapter.CommentAdapter;
import com.example.dell.wy_one.view.interfaces.CommentIView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 * 评论Fragmeng
 * */
public class CommentFragment extends BaseFragment<CommentPresenter> implements CommentIView {
    @BindView(R.id.comment_recycle)
    RecyclerView commentRecycle;
    @BindView(R.id.comment_smart)
    SmartRefreshLayout commentSmart;
    Unbinder unbinder;
    private CommentPresenter presenter;

    @Override
    View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    void initView(View view) {
        presenter = getPresenter();
        presenter.CommentShuju();

    }

    @Override
    void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    CommentPresenter newPresenter() {
        return new CommentPresenter();
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

    @Override
    public void onSuccess(CommentBean commentBean) {
        commentRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        //下拉刷新的监听
        commentSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                commentSmart.finishRefresh(2000);

            }
        });
        //上拉加载的监听
        commentSmart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                commentSmart.finishLoadmore(2000);


            }
        });
        List<CommentBean.RetBean.ListBean> list = commentBean.getRet().getList();
        CommentAdapter commentAdapter=new CommentAdapter(getActivity(),list);
        commentRecycle.setAdapter(commentAdapter);
        commentRecycle.setNestedScrollingEnabled(false);


    }

    @Override
    public void onError(Throwable throwable) {

    }
}
