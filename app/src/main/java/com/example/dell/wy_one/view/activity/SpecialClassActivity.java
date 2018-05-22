package com.example.dell.wy_one.view.activity;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.view.fragment.SpecialClassFragment;

public class SpecialClassActivity extends BaseActivity {

    @Override
    void initView() {

    }

    @Override
    void initData() {
        SpecialClassFragment specialFragment = new SpecialClassFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameclass, specialFragment).commit();
    }

    @Override
    int getLayout() {
        return R.layout.activity_special_class;
    }


}
