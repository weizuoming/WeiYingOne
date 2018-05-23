package com.example.dell.wy_one.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.dl7.player.media.IjkPlayerView;
import com.example.dell.wy_one.R;
import com.example.dell.wy_one.view.fragment.CommentFragment;
import com.example.dell.wy_one.view.fragment.SummaryFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Url;

public class PlayActivity extends BaseActivity {

    @BindView(R.id.play_ijkplayer)
    IjkPlayerView playIjkplayer;
    @BindView(R.id.play_tab)
    TabLayout playTab;
    @BindView(R.id.video_viewpager)
    ViewPager videoViewpager;
    ArrayList<Fragment> fragmentList02 = new ArrayList<Fragment>();
    ArrayList<String> titleList02 = new ArrayList<String>();
    private String shareUrl;
    private String loadUrl;
    private String slt;
    private Uri mUri;
    @Override
    void initView() {
        //获取信息
        Intent intent = getIntent();
        loadUrl = intent.getStringExtra("loadUrl");
        shareUrl = intent.getStringExtra("shareUrl");
        slt = intent.getStringExtra("slt");
        if(!TextUtils.isEmpty(shareUrl) && !TextUtils.isEmpty(loadUrl)){
            initIJK();
        }
        return;
    }
    /**
     * 设置IJK播放器
     */
    private void initIJK() {
        mUri = Uri.parse(shareUrl);
        playIjkplayer.init()
                .setVideoPath(mUri)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                .enableDanmaku()
                .start();
    }

    @Override
    void initData() {


        fragmentList02.add(new SummaryFragment());//对应的布局
        fragmentList02.add(new CommentFragment());
        titleList02.add("简介");//标题
        titleList02.add("评论");

        PlayMyAdapter adapter = new PlayMyAdapter(getSupportFragmentManager(), PlayActivity.this, fragmentList02, titleList02);
        videoViewpager.setAdapter(adapter);
        playTab.setupWithViewPager(videoViewpager);

    }
    private class PlayMyAdapter extends FragmentPagerAdapter {
        public PlayMyAdapter(FragmentManager fm, PlayActivity playActivity, ArrayList<Fragment> fragmentList02, ArrayList<String> titleList02) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //返回对应布局
            return fragmentList02.get(position);
        }

        @Override
        public int getCount() {
            return titleList02.size();//菜单数量
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList02.get(position);//返回对应菜单标题
        }
    }


    @Override
    int getLayout() {
        return R.layout.activity_play;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        playIjkplayer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playIjkplayer.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playIjkplayer.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        playIjkplayer.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (playIjkplayer.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (playIjkplayer.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }



}
