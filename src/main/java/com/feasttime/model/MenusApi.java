package com.feasttime.model;

import com.feasttime.model.bean.CreateOrderInfo;
import com.feasttime.model.bean.DishesCategoryInfo;
import com.feasttime.model.bean.LoginInfo;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.OrderInfo;
import com.feasttime.model.bean.PersonalStatisticsInfo;
import com.feasttime.model.bean.ShoppingCartInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by chen on 2017/4/17.
 */

public interface MenusApi {


      //创建购物车
      @POST("order/createOrder/")
      Observable<CreateOrderInfo> createOrder(@Body RequestBody route);

      //获取菜单
      @FormUrlEncoded
      @POST("menu")
      Observable<MenuInfo> getMenu(@Field("mobileNO") String mobileNO, @Field("token") String token, @Field("orderID") String orderID, @Field("classType") String classType, @Field("page") String page);

      //获取购物车详单
      @POST("getShoppingCartList")
      Observable<OrderInfo> getShoppingCartList(@Body RequestBody route);


      //获取用户统计数据
      @FormUrlEncoded
      @POST("PersonalStatistics/getPersonalStatisticsDetail/")
      Observable<PersonalStatisticsInfo> getPersonalStatistics(@Field("token") String token);


      //获取菜品类型列表
      @GET("getDishesCategoryList")
      Observable<DishesCategoryInfo> getDishesCategoryList();

      //登录
      @POST("user/login/")
      Observable<LoginInfo> login(@Body RequestBody route);


      //添加购物车
      @POST("order/addShoppingCart/")
      Observable<ShoppingCartInfo> addShoppingCart(@Body RequestBody route);


      //去除购物车
      @POST("order/removeShoppingCart/")
      Observable<ShoppingCartInfo> removeShoppingCart(@Body RequestBody route);


}
