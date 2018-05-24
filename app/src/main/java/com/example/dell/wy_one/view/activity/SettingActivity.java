package com.example.dell.wy_one.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dell.wy_one.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Override
    void initView() {

    }

    @Override
    void initData() {

    }

    @Override
    int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imag_back, R.id.relative_tuijian, R.id.relative_qingli, R.id.relative_guanyu, R.id.relative_jianyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imag_back:
                finish();
                break;
            case R.id.relative_tuijian:
                Toast.makeText(SettingActivity.this,"敬请期待"
                        ,Toast.LENGTH_LONG).show();
                break;
            case R.id.relative_qingli:
                Toast.makeText(SettingActivity.this,"敬请期待"
                        ,Toast.LENGTH_LONG).show();
                break;
            case R.id.relative_guanyu:
                Toast.makeText(SettingActivity.this,"敬请期待"
                        ,Toast.LENGTH_LONG).show();
                break;
            case R.id.relative_jianyi:
                Toast.makeText(SettingActivity.this,"敬请期待"
                        ,Toast.LENGTH_LONG).show();
                break;
        }
    }
}
