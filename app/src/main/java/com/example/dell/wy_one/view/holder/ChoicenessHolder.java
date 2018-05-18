package com.example.dell.wy_one.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.wy_one.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoicenessHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.choiceness_image)
       public ImageView choicenessImage;
        @BindView(R.id.choiceness_text)
       public TextView choicenessText;


    public ChoicenessHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
