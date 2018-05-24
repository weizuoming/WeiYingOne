package com.example.dell.wy_one.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.presenter.FindPresenter;
import com.example.dell.wy_one.view.adapter.FindAdapter;
import com.example.dell.wy_one.view.interfaces.FindIView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import link.fls.swipestack.SwipeStack;
import steelkiwi.com.library.DotsLoaderView;

/*
 * 简介Fragment
 * */
public class FindFragment extends BaseFragment<FindPresenter> implements FindIView {


    private int i;
    private FindAdapter findAdapter;
    private FindPresenter findPresenter;
    private SwipeStack mySwipteStack;
    private DotsLoaderView dots;
    private Button btn;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean>list=new ArrayList();

    @Override
    protected View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {

        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    protected void initView(View view) {
        findPresenter = getPresenter();
        findPresenter.FindShuju();
        mySwipteStack = view.findViewById(R.id.my_swipte_stack);
        dots = view.findViewById(R.id.dotsLoaderView);
        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySwipteStack.resetStack();
            }
        });

        mySwipteStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

            }

            @Override
            public void onViewSwipedToRight(int position) {

            }

            @Override
            public void onStackEmpty() {
                i++;

                /*List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList = list.get(i).getChildList();
                findAdapter = new FindAdapter(getActivity(), childList);
                findAdapter.notifyDataSetChanged();*/
                mySwipteStack.resetStack();
            }
        });
    }


    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected FindPresenter newPresenter() {
        return new FindPresenter();
    }

    @Override
    public void onSuccess(ChoicenessBean choicenessBean) {
        List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList = choicenessBean.getRet().getList().get(i).getChildList();
        list.addAll(childList);
        findAdapter = new FindAdapter(getActivity(),list);
        mySwipteStack.setAdapter(findAdapter);
        findAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
