package com.feasttime.model;

import com.feasttime.model.bean.MyLocation;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.feasttime.model.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * Created by chen on 2017/4/17.
 */

public interface MenusApi {
//    /**
//     * 获取新闻列表
//     * eg: http://c.m.163.com/nc/article/headline/T1348647909107/60-20.html
//     *      http://c.m.163.com/nc/article/list/T1348647909107/60-20.html
//     *
//     * @param type 新闻类型
//     * @param id 新闻ID
//     * @param startPage 起始页码
//     * @return
//     */
//    @Headers(CACHE_CONTROL_NETWORK)
//    @GET("nc/article/{type}/{id}/{startPage}-20.html")
//    Observable<Map<String, List<NewsInfo>>> getNewsList(@Path("type") String type, @Path("id") String id,
//                                                        @Path("startPage") int startPage);


      @GET("attendance/getAllLocation")
      Observable<MyLocation> getLocationList();
}
