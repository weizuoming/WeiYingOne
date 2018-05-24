package com.example.dell.wy_one.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dell.wy_one.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView img;

    @Override
    void initView() {

    }

    @Override
    void initData() {
        int[]arr={1,2,3,4,5,6,7};
        int index= (int) (Math.random()*arr.length-1);
        int rand=arr[index];
        List<Integer> integers=new ArrayList<>();
        integers.add(R.drawable.a);
        integers.add(R.drawable.b);
        integers.add(R.drawable.c);
        integers.add(R.drawable.d);
        integers.add(R.drawable.e);
        integers.add(R.drawable.f);
        integers.add(R.drawable.g);

      img.setImageResource(integers.get(rand));

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.welcome);
        img.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    int getLayout() {
        return R.layout.activity_welcome;
    }
}
