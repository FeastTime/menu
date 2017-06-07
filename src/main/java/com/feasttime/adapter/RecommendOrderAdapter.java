package com.feasttime.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feasttime.menu.R;
import com.feasttime.model.bean.MyOrderListItemInfo;
import com.feasttime.model.bean.RecommendOrderListItemInfo;

import java.util.List;

/**
 * Created by chen on 2017/5/11.
 */

public class RecommendOrderAdapter extends RecyclerView.Adapter<RecommendOrderAdapter.MyViewHolder> {
    private List<RecommendOrderListItemInfo> datas;
    private Context context;
    private RecommendOrderListInterface recommendOrderListInterface;

    public interface RecommendOrderListInterface {
        public void onAddClicked();
        public void onReduceClicked();
    }

    public void setListener(RecommendOrderListInterface listener) {
        recommendOrderListInterface = listener;
    }

    public RecommendOrderAdapter(List<RecommendOrderListItemInfo> datas, Activity activity) {
        context = activity;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recommend_order_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecommendOrderListItemInfo recommendOrderListItemInfo = datas.get(position);
        holder.nameTv.setText(recommendOrderListItemInfo.getDishName());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView addIv;
        public TextView nameTv;
        public ImageView reduceIv;

        public MyViewHolder(View view) {
            super(view);
            nameTv = (TextView) view.findViewById(R.id.recommend_order_list_item_name_tv);
            addIv = (ImageView) view.findViewById(R.id.recommend_order_list_item_add_iv);
            reduceIv = (ImageView) view.findViewById(R.id.recommend_order_list_item_add_iv);
        }
    }
}