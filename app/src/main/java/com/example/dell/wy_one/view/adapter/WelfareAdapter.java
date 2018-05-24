package com.example.dell.wy_one.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.WelfareBean;
import com.example.dell.wy_one.view.holder.WelfareHolder;
import com.example.dell.wy_one.view.interfaces.OnItemListner;

import java.util.List;


public class WelfareAdapter extends RecyclerView.Adapter<WelfareHolder> {
    private Context context;
    private final List<WelfareBean.ResultsBean> list;
    private OnItemListner onItemListner;

    public WelfareAdapter(Context context,List<WelfareBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public WelfareHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = View.inflate(context, R.layout.welfare_item_layout, null);
        View view = LayoutInflater.from(context).inflate(R.layout.welfare_item_layout, parent, false);
        WelfareHolder welfareHolder = new WelfareHolder(view);

        return welfareHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WelfareHolder holder, int position) {
        WelfareBean.ResultsBean bean = list.get(position);
        if (bean.getUrl()==null){
            holder.sdv_fuli.setImageURI("http://ww1.sinaimg.cn/large/0065oQSqly1fri9zqwzkoj30ql0w3jy0.jpg");
        }else {
            holder.sdv_fuli.setImageURI(bean.getUrl());
        }
    }

    @Override
    public int getItemCount() {
            return list.size();
    }
    public void setOnItemListner(OnItemListner onItemListner) {
        this.onItemListner = onItemListner;
    }

}
