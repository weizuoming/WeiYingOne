package com.example.dell.wy_one.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.wy_one.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
      public   TextView title;
        @BindView(R.id.time)
      public   TextView time;
        @BindView(R.id.msg)
      public   TextView msg;



    public CommentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
