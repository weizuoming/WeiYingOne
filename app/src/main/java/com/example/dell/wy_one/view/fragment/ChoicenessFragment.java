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
import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.presenter.ChoicenessPresenter;
import com.example.dell.wy_one.utils.GlideImageLoader;
import com.example.dell.wy_one.view.adapter.ChoicenessAdapter;
import com.example.dell.wy_one.view.interfaces.ChoicenessIView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


public class ChoicenessFragment extends BaseFragment<ChoicenessPresenter> implements ChoicenessIView {

    @BindView(R.id.choiceness_banner)
    Banner choicenessBanner;
    @BindView(R.id.choiceness_recycler)
    RecyclerView choicenessRecycler;
    Unbinder unbinder;
    private ChoicenessPresenter choicenessPresenter;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> listAll  = new ArrayList<>();//装当前页面所有的数据
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList;

    @Override
    View getLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_choiceness, container, false);
    }

    @Override
    void initView(View view) {

    }



    @Override
    void initData(@Nullable Bundle savedInstanceState) {
        choicenessPresenter = getPresenter();
        choicenessPresenter.shuju();
        //设置banner样式...CIRCLE_INDICATOR_TITLE包含标题
        //choicenessBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        choicenessBanner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        choicenessBanner.isAutoPlay(true);
        //设置轮播时间
        choicenessBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        choicenessBanner.setIndicatorGravity(BannerConfig.CENTER);



    }

    @Override
    ChoicenessPresenter newPresenter() {
        return new ChoicenessPresenter();
    }

    @Override
    public void onSuccess(ChoicenessBean choicenessBean) {
        //banner图展示
        List<ChoicenessBean.RetBean.ListBean> beans = choicenessBean.getRet().getList();
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0;i<beans.size();i++){
            if(beans.get(i).getShowType().equals("banner")){
                childList = beans.get(i).getChildList();
                for (int j = 0; j< childList.size(); j++){
                    String pic = childList.get(j).getPic();
                    imageUrls.add(pic);
                }
            }
        }
        choicenessBanner.setImages(imageUrls);
        choicenessBanner.start();

        //精彩推荐展示数据
        choicenessRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        listAll.addAll(childList);
        ChoicenessAdapter choicenessAdapter=new ChoicenessAdapter(getActivity(),listAll);
        choicenessRecycler.setAdapter(choicenessAdapter);
        choicenessRecycler.setNestedScrollingEnabled(false);


    }

    @Override
    public void onError(Throwable throwable) {

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
