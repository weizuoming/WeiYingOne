package com.example.dell.wy_one.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.presenter.ChoicenessPresenter;
import com.example.dell.wy_one.utils.GlideImageLoader;
import com.example.dell.wy_one.view.activity.PlayActivity;
import com.example.dell.wy_one.view.activity.SearchActivity;
import com.example.dell.wy_one.view.adapter.ChoicenessAdapter;
import com.example.dell.wy_one.view.custom.ObservableScrollView;
import com.example.dell.wy_one.view.interfaces.ChoicenessIView;
import com.example.dell.wy_one.view.interfaces.OnItemListner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ChoicenessFragment extends BaseFragment<ChoicenessPresenter> implements ChoicenessIView {

    @BindView(R.id.line)
    RelativeLayout line;
    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.jingxuan)
    TextView jingxuan;
    @BindView(R.id.jingxuan_sousuo)
    LinearLayout jingxuanSousuo;
    private int imageHeight = 500; //设置渐变高度，一般为导航图片高度，自己控制


    @BindView(R.id.choiceness_banner)
    Banner choicenessBanner;
    @BindView(R.id.choiceness_recycler)
    RecyclerView choicenessRecycler;
    Unbinder unbinder;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;
    Unbinder unbinder1;
    private ChoicenessPresenter choicenessPresenter;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> listAll = new ArrayList<>();//装当前页面所有的数据
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList;
    private ChoicenessAdapter choicenessAdapter;

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


        //查找控件
//搜索框在布局最上面
        line.bringToFront();
//滑动监听
        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    line.setBackgroundColor(Color.argb((int) 0, 2, 255, 37));
                    jingxuan.setTextColor(Color.argb((int) 0, 255, 255, 255));
                    jingxuan.setText("精选");
                    //AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
// 只是layout背景透明
                    line.setBackgroundColor(Color.argb((int) alpha, 2, 255, 37));
                    jingxuan.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    line.setBackgroundColor(Color.argb((int) 255, 2, 255, 37));
                    jingxuan.setTextColor(Color.argb((int) 255, 255, 255, 255));
                    jingxuan.setText("精选");
                }
            }
        });

    }

    @Override
    ChoicenessPresenter newPresenter() {
        return new ChoicenessPresenter();
    }

    @Override
    public void onSuccess(ChoicenessBean choicenessBean) {
        //banner图展示
        final List<ChoicenessBean.RetBean.ListBean> beans = choicenessBean.getRet().getList();
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i).getShowType().equals("banner")) {
                childList = beans.get(i).getChildList();
                for (int j = 0; j < childList.size(); j++) {
                    String pic = childList.get(j).getPic();
                    imageUrls.add(pic);
                }
            }
        }
        choicenessBanner.setImages(imageUrls);
        choicenessBanner.start();

        //精彩推荐展示数据
        choicenessRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        //下拉刷新的监听
        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smart_refresh.finishRefresh(2000);

            }
        });
        //上拉加载的监听
        smart_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                smart_refresh.finishLoadmore(2000);


            }
        });


        listAll.addAll(childList);
        choicenessAdapter = new ChoicenessAdapter(getActivity(), listAll);
        choicenessRecycler.setAdapter(choicenessAdapter);
        choicenessRecycler.setNestedScrollingEnabled(false);


        choicenessAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), PlayActivity.class);
//               intent.putExtra("shareURL",beans.get(position).getChildList().get(position).getShareURL());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });


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
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.jingxuan_sousuo)
    public void onViewClicked() {
        Intent intent=new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }
}
