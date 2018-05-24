package com.example.dell.wy_one.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.dell.wy_one.R;
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

                startActivity(new Intent(MainActivity.this
                        , WelfareActivity.class));
                break;
            case R.id.relative_fenxiang:

                break;
            case R.id.relative_jianyi:

                break;
            case R.id.relative_shezhi:

                break;
            case R.id.relative_guanyu:

                break;
            case R.id.relative_zhuti:

                break;
        }
    }
}
