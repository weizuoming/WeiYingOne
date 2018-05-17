package com.example.dell.wy_one.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.view.fragment.ChoicenessFragment;
import com.example.dell.wy_one.view.fragment.FindFragment;
import com.example.dell.wy_one.view.fragment.MyFragment;
import com.example.dell.wy_one.view.fragment.SpecialFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    private ChoicenessFragment choicenessFragment;
    private FindFragment findFragment;
    private MyFragment myFragment;
    private SpecialFragment specialFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    @OnClick({R.id.jingxuan, R.id.zhuanti, R.id.faxian, R.id.wode})
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
        }
    }
}
