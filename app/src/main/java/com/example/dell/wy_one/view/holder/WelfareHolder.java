package com.example.dell.wy_one.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dell.wy_one.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sdv_fuli)
       public SimpleDraweeView sdv_fuli;


    public WelfareHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
