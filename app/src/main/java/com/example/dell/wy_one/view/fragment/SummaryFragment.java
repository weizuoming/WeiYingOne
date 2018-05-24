package com.example.dell.wy_one.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.presenter.SummaryPresenter;
import com.example.dell.wy_one.view.activity.PlayActivity;
import com.example.dell.wy_one.view.adapter.SummaryAdapter;
import com.example.dell.wy_one.view.custom.StretchyTextView;
import com.example.dell.wy_one.view.interfaces.OnItemListner;
import com.example.dell.wy_one.view.interfaces.SummaryIView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SummaryFragment extends BaseFragment<SummaryPresenter> implements SummaryIView {


    Unbinder unbinder;
    @BindView(R.id.summary_textview)
    StretchyTextView summaryTextview;
    @BindView(R.id.zhankai)
    TextView zhankai;
    @BindView(R.id.summary_recycler)
    RecyclerView summaryRecycler;
    private SummaryPresenter summaryPresenter;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList;

    @Override
    View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    void initView(View view) {
        summaryTextview.setMaxLineCount(3);
        summaryTextview.setBottomTextGravity(Gravity.CENTER_VERTICAL);
        summaryTextview.setContentTextColor(Color.WHITE);


    }

    @Override
    void initData(@Nullable Bundle savedInstanceState) {
        summaryPresenter = getPresenter();
        summaryPresenter.SummpayShuju();

    }

    @Override
    SummaryPresenter newPresenter() {
        return new SummaryPresenter();
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
    public void onSuccess(final ChoicenessBean choicenessBean) {
        List<ChoicenessBean.RetBean.ListBean> list = choicenessBean.getRet().getList();
        for (int i = 0; i <list.size() ; i++) {
            childList = list.get(i).getChildList();
            for (int j = 0; j < childList.size(); j++) {
                summaryTextview.setContent(childList.get(j).getDescription());
            }
        }
        summaryRecycler.setLayoutManager(new GridLayoutManager(getActivity(),3));
        SummaryAdapter summaryAdapter=new SummaryAdapter(getActivity(),childList);
        summaryRecycler.setAdapter(summaryAdapter);
        summaryRecycler.setNestedScrollingEnabled(false);
        summaryAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), PlayActivity.class);
                intent.putExtra("loadUrl",childList .get(position).getLoadURL().toString());
                intent.putExtra("shareUrl", childList.get(position).getShareURL().toString());
                intent.putExtra("slt", childList.get(position).getPic().toString());
                intent.putExtra("title",childList.get(position).getTitle().toString());
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

    }


    @Override
    public void onError(Throwable throwable) {

    }
}
