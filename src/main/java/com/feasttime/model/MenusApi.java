package com.feasttime.model;

import com.feasttime.model.bean.CreateOrderInfo;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.OrderInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by chen on 2017/4/17.
 */

public interface MenusApi {


      //创建购物车
      @FormUrlEncoded
      @POST("createOrder")
      Observable<CreateOrderInfo> createOrder(@Field("token") String token);

      //获取菜单
      @FormUrlEncoded
      @POST("menu")
      Observable<MenuInfo> getMenu(@Field("mobileNO") String mobileNO, @Field("token") String token, @Field("orderID") String orderID, @Field("classType") String classType, @Field("page") String page);

      //获取购物车详单
      @FormUrlEncoded
      @POST("getShoppingCartList")
      Observable<OrderInfo> getShoppingCartList(@Field("orderID") String orderID);
}
