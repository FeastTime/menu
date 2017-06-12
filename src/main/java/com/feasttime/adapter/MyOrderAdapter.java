package com.feasttime.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feasttime.menu.R;
import com.feasttime.model.bean.MyOrderListItemInfo;

import java.util.List;

/**
 * Created by chen on 2017/5/11.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder>{
    private List<MyOrderListItemInfo> datas;
    private Context context;

    public MyOrderAdapter(List<MyOrderListItemInfo> datas, Activity activity){
        context = activity;
        this.datas = datas;
    }

    public void refreshList(List<MyOrderListItemInfo> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.my_order_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        MyOrderListItemInfo myOrderListItemInfo = datas.get(position);
        holder.nameTv.setText(myOrderListItemInfo.getDishName());
    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameTv;
        public MyViewHolder(View view)
        {
            super(view);
            nameTv = (TextView)view.findViewById(R.id.my_order_list_item_name_tv);
        }
    }
}
