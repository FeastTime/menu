package com.feasttime.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feasttime.adapter.MyOrderAdapter;
import com.feasttime.adapter.RecommendOrderAdapter;
import com.feasttime.menu.R;
import com.feasttime.model.bean.MyOrderListItemInfo;
import com.feasttime.model.bean.OrderInfo;
import com.feasttime.model.bean.RecommendOrderListItemInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.order.OrderContract;
import com.feasttime.presenter.order.OrderPresenter;
import com.feasttime.presenter.shoppingcart.ShoppingCartContract;
import com.feasttime.presenter.shoppingcart.ShoppingCartPresenter;
import com.feasttime.tools.PreferenceUtil;
import com.feasttime.widget.RecyclerViewDivider;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by chen on 2017/5/11.
 */

public class MyOrderFragment extends BaseFragment implements ShoppingCartContract.IShoppingCartView,View.OnClickListener,OrderContract.IOrderView,RecommendOrderAdapter.RecommendOrderListener {


    @Bind(R.id.my_order_detail_rv)
    RecyclerView myOrderRv;

    @Bind(R.id.my_order_recommend_rv)
    RecyclerView recommendOrderRv;

    @Bind(R.id.my_order_place_order_tv)
    TextView placeOrderTv;

    ShoppingCartPresenter mShoppingCartPresenter = new ShoppingCartPresenter();
    OrderPresenter mOrderPresenter = new OrderPresenter();

    MyOrderAdapter myOrderAdapter;
    RecommendOrderAdapter recommendOrderAdapter;
    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[]{mShoppingCartPresenter,mOrderPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mShoppingCartPresenter.init(this);
        mOrderPresenter.init(this);
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
        String orderID = PreferenceUtil.getStringKey("orderID");
        mShoppingCartPresenter.getShoppingCartList(orderID);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void addShoppingCartComplete(OrderInfo orderInfo) {
        myOrderAdapter.refreshList(orderInfo.getMyOrderList());
        recommendOrderAdapter.refreshList(orderInfo.getRecommendOrderList());
    }

    @Override
    public void removeShoppingCartComplete(OrderInfo orderInfo) {
        myOrderAdapter.refreshList(orderInfo.getMyOrderList());
        recommendOrderAdapter.refreshList(orderInfo.getRecommendOrderList());
    }

    @Override
    public void getShoppingcartListComplete() {

    }

    @Override
    public void showRecommendList(List<RecommendOrderListItemInfo> recommendOrderList) {
        recommendOrderAdapter = new RecommendOrderAdapter(recommendOrderList,this.getActivity());
        recommendOrderAdapter.setListener(this);
        recommendOrderRv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        recommendOrderRv.addItemDecoration(new RecyclerViewDivider(this.getActivity(), LinearLayoutManager.HORIZONTAL, ScreenTools.dip2px(this.getActivity(),10)), Color.TRANSPARENT);
        recommendOrderRv.setAdapter(recommendOrderAdapter);
    }

    @Override
    public void showOrderList(List<MyOrderListItemInfo> myOrderList) {
        myOrderAdapter = new MyOrderAdapter(myOrderList,this.getActivity());
        myOrderRv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        myOrderRv.addItemDecoration(new RecyclerViewDivider(this.getActivity(), LinearLayoutManager.HORIZONTAL, 30, Color.TRANSPARENT));
        myOrderRv.setAdapter(myOrderAdapter);
    }

    @OnClick({R.id.my_order_place_order_tv})
    @Override
    public void onClick(View v) {
        if (v == placeOrderTv) {
            String orderID = PreferenceUtil.getStringKey("orderID");
            mOrderPresenter.placeOrder(orderID);
        }
    }

    @Override
    public void payOrderComplete() {

    }

    @Override
    public void placeOrderComplete() {

    }

    @Override
    public void createOrderComplete() {

    }

    @Override
    public void onAddClicked(String ID) {
        String orderID = PreferenceUtil.getStringKey("orderID");
        mShoppingCartPresenter.addShoppingCart(ID,orderID);
    }

    @Override
    public void onReduceClicked(String ID) {
        String orderID = PreferenceUtil.getStringKey("orderID");
        mShoppingCartPresenter.removeShoppingCart(ID,orderID);
    }
}
