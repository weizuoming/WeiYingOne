package com.example.dell.wy_one.view.activity;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.view.fragment.WelfareFragment;

/*
* 福利
* */
public class WelfareActivity extends BaseActivity {



    @Override
    void initView() {

    }

    @Override
    void initData() {
        WelfareFragment welfareFragment = new WelfareFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_welfare, welfareFragment).commit();
    }

    @Override
    int getLayout() {
        return R.layout.activity_welfare;
    }
}
