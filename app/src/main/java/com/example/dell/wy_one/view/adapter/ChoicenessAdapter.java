package com.example.dell.wy_one.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.view.holder.ChoicenessHolder;
import com.example.dell.wy_one.view.interfaces.OnItemListner;

import java.util.List;



public class ChoicenessAdapter extends RecyclerView.Adapter<ChoicenessHolder> {
    private Context context;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> list;
    private OnItemListner onItemListner;

    public ChoicenessAdapter(Context context, List<ChoicenessBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChoicenessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.choiceness_item_layout, null);
        ChoicenessHolder choicenessHolder = new ChoicenessHolder(view);

        return choicenessHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChoicenessHolder holder, final int position) {
        holder.choicenessText.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getPic().split("\\|")[0]).into(holder.choicenessImage);
        holder.choicenessImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListner.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }else{
            return list.size();
        }

    }
    public void setOnItemListner(OnItemListner onItemListner) {
        this.onItemListner = onItemListner;
    }

}
