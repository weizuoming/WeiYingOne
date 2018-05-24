package com.example.dell.wy_one.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.wy_one.R;
import com.example.dell.wy_one.model.bean.ChoicenessBean;

import java.util.List;

public class FindAdapter extends BaseAdapter {
    private Context context;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList;
    public FindAdapter(Context context, List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList) {
        this.context = context;
        this.childList = childList;
    }

    @Override
    public int getCount() {
        return childList.size();
    }

    @Override
    public Object getItem(int i) {
        return childList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.finditem, null);
            holder.img = convertView.findViewById(R.id.item_img);
            holder.find_title=convertView.findViewById(R.id.find_title);
            holder.find_show=convertView.findViewById(R.id.find_show);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(Uri.parse(childList.get(i).getPic())).placeholder(R.drawable.ic_launcher_background).into(holder.img);
        holder.find_show.setText(childList.get(i).getDescription());
        holder.find_title.setText(childList.get(i).getTitle());
//        holder.img.setImageURI(Uri.parse(list.get(position).getIcon()));
        return convertView;
    }
    class ViewHolder {
        ImageView img;
        TextView find_title;
        TextView find_show;
    }
}
