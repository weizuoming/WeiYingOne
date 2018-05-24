package com.example.dell.wy_one.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.onekeyshare.OnekeyShare;
import com.example.dell.wy_one.view.fragment.ChoicenessFragment;
import com.example.dell.wy_one.view.fragment.FindFragment;
import com.example.dell.wy_one.view.fragment.MyFragment;
import com.example.dell.wy_one.view.fragment.SpecialFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.jingxuan)
    RadioButton jingxuan;
    @BindView(R.id.zhuanti)
    RadioButton zhuanti;
    @BindView(R.id.faxian)
    RadioButton faxian;
    @BindView(R.id.wode)
    RadioButton wode;
    @BindView(R.id.rg)
    RadioGroup rg;

    //w
    @BindView(R.id.sdv_2)
    SimpleDraweeView sdv2;
    @BindView(R.id.drawer_relative)
    RelativeLayout drawerRelative;

    private ChoicenessFragment choicenessFragment;
    private FindFragment findFragment;
    private MyFragment myFragment;
    private SpecialFragment specialFragment;
    private FragmentManager fragmentManager;

    @Override
    void initView() {


    }


    @Override
    void initData() {
        sdv2.setImageURI("http://p2.so.qhimgs1.com/bdr/_240_/t01263daa2be6606e4e.jpg");

        choicenessFragment = new ChoicenessFragment();
        findFragment = new FindFragment();
        myFragment = new MyFragment();
        specialFragment = new SpecialFragment();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction add = fragmentManager.beginTransaction().add(R.id.frame, choicenessFragment).add(R.id.frame, findFragment).add(R.id.frame, myFragment).add(R.id.frame, specialFragment);
        add.commit();
        fragmentManager.beginTransaction().hide(findFragment).hide(myFragment).hide(specialFragment).show(choicenessFragment).commit();
        jingxuan.setChecked(true);

    }

    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.jingxuan, R.id.zhuanti, R.id.faxian, R.id.wode
            , R.id.relative_shoucang, R.id.relative_xiazai, R.id.relative_fuli
            , R.id.relative_fenxiang, R.id.relative_jianyi
            , R.id.relative_shezhi, R.id.relative_guanyu
    ,R.id.relative_zhuti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jingxuan:
                fragmentManager.beginTransaction().hide(findFragment).hide(myFragment).hide(specialFragment).show(choicenessFragment).commit();
                break;
            case R.id.zhuanti:
                fragmentManager.beginTransaction().hide(findFragment).hide(myFragment).hide(choicenessFragment).show(specialFragment).commit();
                break;
            case R.id.faxian:
                fragmentManager.beginTransaction().hide(choicenessFragment).hide(myFragment).hide(specialFragment).show(findFragment).commit();
                break;
            case R.id.wode:
                fragmentManager.beginTransaction().hide(findFragment).hide(choicenessFragment).hide(specialFragment).show(myFragment).commit();
                break;

                //w
            case R.id.relative_shoucang:
                //collect收藏
                Intent intent = new Intent(MainActivity.this
                        , MyCollectActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_xiazai:

                break;
                //福利
            case R.id.relative_fuli:

                break;
            case R.id.relative_fenxiang:
                //share  分享
                showShare();

                break;
            case R.id.relative_jianyi:

                break;
            case R.id.relative_shezhi:
                Intent intent1 = new Intent(MainActivity.this
                        , SettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.relative_guanyu:

                break;
            case R.id.relative_zhuti:

                break;
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("发现一个看片神器");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("https://github.com/WeiYingOne/WeiYingOne");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是响哥");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("https://github.com/WeiYingOne/WeiYingOne");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("响哥测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://github.com/WeiYingOne/WeiYingOne");

// 启动分享GUI
        oks.show(this);
    }
}
