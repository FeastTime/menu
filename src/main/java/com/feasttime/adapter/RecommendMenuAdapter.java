package com.feasttime.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feasttime.menu.R;

import java.util.List;

/**
 * Created by chen on 2017/5/8.
 */

public class RecommendMenuAdapter extends  RecyclerView.Adapter<RecommendMenuAdapter.MyViewHolder>{

    private List<Integer> datas;
    private Context context;

    public RecommendMenuAdapter(List<Integer> datas, Activity activity){
        context = activity;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recommend_activity_list_menu_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        //holder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public MyViewHolder(View view)
        {
            super(view);
        }
    }
}
