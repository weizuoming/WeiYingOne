package com.example.dell.wy_one.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.ChoicenessBean;
import com.example.dell.wy_one.view.activity.SpecialClassActivity;
import com.example.dell.wy_one.view.holder.SpecialHolder;
import com.example.dell.wy_one.view.interfaces.OnItemListner;


public class SpecialAdapter extends RecyclerView.Adapter<SpecialHolder> {
    private Context context;
    private final ChoicenessBean choicenessBean;
    private OnItemListner onItemListner;

    public SpecialAdapter(Context context, ChoicenessBean choicenessBean) {
        this.context = context;
        this.choicenessBean = choicenessBean;
    }


    @NonNull
    @Override
    public SpecialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.special_item_layout, null);
        SpecialHolder specialHolder = new SpecialHolder(view);

        return specialHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialHolder holder, int position) {
        final ChoicenessBean.RetBean dataBean = choicenessBean
                .getRet();
        holder.sdv_shipin.setImageURI(dataBean.getList().get(position+2).getChildList().get(0).getPic());
        holder.text_class.setText(dataBean.getList().get(position+2).getTitle());
        holder.sdv_shipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpecialClassActivity.class);

                context.startActivity(intent);
            }
        });



    }



    @Override
    public int getItemCount() {
        if(choicenessBean.getRet().getList()==null){
            return 0;
        }else{
            return choicenessBean.getRet().getList().size()-2;
        }

    }
    public void setOnItemListner(OnItemListner onItemListner) {
        this.onItemListner = onItemListner;
//        Intent intent = new Intent(context, SpecialClassActivity.class);
//        context.startActivity(intent);
    }

}
