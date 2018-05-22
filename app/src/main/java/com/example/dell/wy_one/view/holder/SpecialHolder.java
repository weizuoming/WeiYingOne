package com.example.dell.wy_one.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.wy_one.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_class)
       public TextView text_class;
        @BindView(R.id.sdv_shipin)
       public SimpleDraweeView sdv_shipin;


    public SpecialHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
