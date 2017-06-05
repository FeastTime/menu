package com.feasttime.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feasttime.adapter.MyOrderAdapter;
import com.feasttime.adapter.RecommendOrderAdapter;
import com.feasttime.menu.R;
import com.feasttime.model.bean.MyOrderListItemInfo;
import com.feasttime.model.bean.RecommendOrderListItemInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.order.OrderContract;
import com.feasttime.presenter.order.OrderPresenter;
import com.feasttime.widget.RecyclerViewDivider;

import java.util.List;

import butterknife.Bind;

/**
 * Created by chen on 2017/5/11.
 */

public class MyOrderFragment extends BaseFragment implements OrderContract.IOrderView{


    @Bind(R.id.my_order_detail_rv)
    RecyclerView myOrderRv;

    @Bind(R.id.my_order_recommend_rv)
    RecyclerView recommendOrderRv;


    OrderPresenter orderPresenter = new OrderPresenter();


    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[]{orderPresenter};
    }

    @Override
    protected void onInitPresenters() {
        orderPresenter.init(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.my_order_fragment;
    }

    @Override
    protected void initViews() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //orderPresenter.getShoppingCartList("5555555");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showMyOrder(List<MyOrderListItemInfo> myOrderList) {
        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderList,this.getActivity());
        myOrderRv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        myOrderRv.addItemDecoration(new RecyclerViewDivider(this.getActivity(), LinearLayoutManager.HORIZONTAL, 30, Color.TRANSPARENT));
        myOrderRv.setAdapter(myOrderAdapter);
    }

    @Override
    public void showRecommendOrder(List<RecommendOrderListItemInfo> recommendOrderList) {
        RecommendOrderAdapter recommendOrderAdapter = new RecommendOrderAdapter(recommendOrderList,this.getActivity());
        recommendOrderRv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        recommendOrderRv.addItemDecoration(new RecyclerViewDivider(this.getActivity(), LinearLayoutManager.HORIZONTAL, ScreenTools.dip2px(this.getActivity(),10)), Color.TRANSPARENT);
        recommendOrderRv.setAdapter(recommendOrderAdapter);

    }

    @Override
    public void createOrderComplete() {

    }
}
