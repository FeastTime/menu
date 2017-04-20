package com.feasttime.model;

import com.feasttime.model.bean.CreateOrderInfo;

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


}
