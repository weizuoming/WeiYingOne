package com.example.dell.wy_one.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.wy_one.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialClassHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name)
       public TextView text_name;
        @BindView(R.id.sdv_SpecialClass)
       public SimpleDraweeView sdv_SpecialClass;


    public SpecialClassHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
