package com.feasttime.model;

import com.feasttime.model.bean.MyLocation;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.feasttime.model.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * Created by chen on 2017/4/17.
 */

public interface MenusApi {

      //test
      @FormUrlEncoded
      @POST("announcement/announcementList")
      Observable<MyLocation> getLocationList(@Field("rows") String rows,@Field("page") String page);
}
