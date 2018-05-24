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
import com.example.dell.wy_one.view.holder.SummaryHolder;
import com.example.dell.wy_one.view.interfaces.OnItemListner;

import java.util.List;


public class SummaryAdapter extends RecyclerView.Adapter<SummaryHolder> {
    private Context context;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> list;
    private OnItemListner onItemListner;

    public SummaryAdapter(Context context, List<ChoicenessBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public SummaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.choiceness_item_layout, null);
        SummaryHolder summaryHolder = new SummaryHolder(view);

        return summaryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryHolder holder, final int position) {
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
