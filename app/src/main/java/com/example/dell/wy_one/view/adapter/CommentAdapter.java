package com.example.dell.wy_one.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.CommentBean;
import com.example.dell.wy_one.view.holder.CommentHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {
    private Context context;
    private List<CommentBean.RetBean.ListBean> list;

    public CommentAdapter(Context context, List<CommentBean.RetBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.comment_item_layout, null);
        CommentHolder commentHolder = new CommentHolder(view);
        return commentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.title.setText(list.get(position).getPhoneNumber());
        holder.time.setText(list.get(position).getTime());
        holder.msg.setText(list.get(position).getMsg());

    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }else{
            return list.size();
        }
    }


}
