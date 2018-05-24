package com.example.dell.wy_one.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.SpecialClassBean;
import com.example.dell.wy_one.view.activity.PlayActivity;
import com.example.dell.wy_one.view.holder.SpecialClassHolder;
import com.example.dell.wy_one.view.interfaces.OnItemListner;


public class SpecialClassAdapter extends RecyclerView.Adapter<SpecialClassHolder> {
    private Context context;
    private final SpecialClassBean specialClassBean;
    private OnItemListner onItemListner;

    public SpecialClassAdapter(Context context, SpecialClassBean specialClassBean) {
        this.context = context;
        this.specialClassBean = specialClassBean;
    }


    @NonNull
    @Override
    public SpecialClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.specialclass_item_layout, null);
        SpecialClassHolder specialClassHolder = new SpecialClassHolder(view);

        return specialClassHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialClassHolder holder, final int position) {
        final SpecialClassBean.RetBean dataBean = specialClassBean
                .getRet();
        holder.sdv_SpecialClass.setImageURI(dataBean.getList().get(position).getPic());
        holder.text_name.setText(dataBean.getList().get(position).getTitle());
        holder.sdv_SpecialClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayActivity.class);
                intent.putExtra("url",dataBean.getList().get(position).getShareURL()+"");
                intent.putExtra("title",dataBean.getList().get(position).getTitle()+"");
                context.startActivity(intent);
            }
        });
    }







    @Override
    public int getItemCount() {
//        if(specialClassBean.getRet().getList()==null){
//            return 0;
//        }else{
            return specialClassBean.getRet().getList().size();
//        }

    }
    public void setOnItemListner(OnItemListner onItemListner) {
        this.onItemListner = onItemListner;
    }

}
