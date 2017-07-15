package com.feasttime.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feasttime.model.bean.CreateOrderInfo;
import com.feasttime.model.bean.DishesCategoryInfo;
import com.feasttime.model.bean.LoginInfo;
import com.feasttime.model.bean.MenuInfo;
import com.feasttime.model.bean.OrderInfo;
import com.feasttime.model.bean.PayOrderInfo;
import com.feasttime.model.bean.PersonalStatisticsInfo;
import com.feasttime.model.bean.PlaceOrderInfo;
import com.feasttime.model.bean.ShoppingCartInfo;
import com.feasttime.model.bean.WaitTimeAdInfo;
import com.feasttime.model.bean.WaitTimeMenuInfo;
import com.feasttime.tools.DeviceTool;
import com.feasttime.tools.LogUtil;
import com.feasttime.tools.PreferenceUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * 整个网络通信服务的启动控制，必须先调用初始化函数才能正常使用网络通信接口
 */
public class RetrofitService {

    private static final String TAG = "RetrofitService";
    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=3600";
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    // 阿里云服务器 http://47.94.16.58:9798/feast-web/PersonalStatistics/getPersonalStatisticsDetail/?token=%22333333%22

//    private static final String BASE_URL = "http://shengyan.com/";
    private static final String BASE_URL = "http://47.94.16.58:9798/feast-web/";


    private static MenusApi sMenuService;

    // 递增页码
    private static final int INCREASE_PAGE = 20;


    private RetrofitService() {
        throw new AssertionError();
    }

    private static String imei = "";
    private static String androidID = "";
    private static String ipv4 = "";
    private static String mac = "";
    private static String mobileNO = "";

    /**
     * 初始化网络通信服务
     */
    public static void init(Context context) {
        imei = DeviceTool.getIMEI(context);
        androidID = DeviceTool.getAndroidId(context);
        ipv4 = DeviceTool.getIP(context);
        mobileNO = DeviceTool.getPhoneNumber(context);
        mac = DeviceTool.getLocalMacAddress(context);

        //BasicParamsInterceptor aa;

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(sLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        sMenuService = retrofit.create(MenusApi.class);
    }


    /**
     * 打印返回的json数据拦截器
     */
    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {

            final Request request = chain.request().newBuilder()
//                    .addHeader("imei",imei)
//                    .addHeader("androidID",androidID)
//                    .addHeader("mac",mac)
//                    .addHeader("ipv4",ipv4)
                    .addHeader("Content-Type","application/json")
                    .build();

            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                LogUtil.d(TAG, "request.body() == null");
            }
            //打印url信息
            LogUtil.d(TAG,"net info request---> " + request.url() + (request.body() != null ? "?" + _parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            LogUtil.d(TAG,"net info response---> " + responseBody.string());
            return response;
        }
    };

    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }

    //转换成
    private static void addDeviceInfo(HashMap <String,Object> infoMap) {
        String mobileNO = PreferenceUtil.getStringKey("mobileNO");

        infoMap.put("mobileNO",mobileNO);
        infoMap.put("imei",imei);
        infoMap.put("androidID",androidID);
        infoMap.put("mac",mac);
        infoMap.put("ipv4",ipv4);
    }

    private static RequestBody getRequestBody(HashMap<String,Object> infoMap) {
        JSONObject jobj = new JSONObject(infoMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jobj.toString());
        return body;
    }


    public static Observable<CreateOrderInfo> createOrder(HashMap<String,Object> infoMap){
        addDeviceInfo(infoMap);
        return sMenuService.createOrder(getRequestBody(infoMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<MenuInfo> getMenu(HashMap<String,Object> infoMap){
        addDeviceInfo(infoMap);
        return sMenuService.getMenu(getRequestBody(infoMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<OrderInfo> getShoppingCartList(HashMap<String,Object> infoMap){
        addDeviceInfo(infoMap);
        return sMenuService.getShoppingCartList(getRequestBody(infoMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<PersonalStatisticsInfo> getPersonalStatistics(String token){
        return sMenuService.getPersonalStatistics(token)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<DishesCategoryInfo> getDishesCategoryList(){
        return sMenuService.getDishesCategoryList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<LoginInfo> login(HashMap<String,Object> infoMap){
        addDeviceInfo(infoMap);
        return sMenuService.login(getRequestBody(infoMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<OrderInfo> addShoppingCart(HashMap<String,Object> infoMap){
        addDeviceInfo(infoMap);
        return sMenuService.addShoppingCart(getRequestBody(infoMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<OrderInfo> removeShoppingCart(HashMap<String,Object> infoMap){
        addDeviceInfo(infoMap);
        return sMenuService.removeShoppingCart(getRequestBody(infoMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<PlaceOrderInfo> placeOrder(HashMap<String,Object> infoMap){
        addDeviceInfo(infoMap);
        return sMenuService.placeOrder(getRequestBody(infoMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<PayOrderInfo> payOrder(HashMap<String,Object> infoMap){
        addDeviceInfo(infoMap);
        return sMenuService.payOrder(getRequestBody(infoMap))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<WaitTimeAdInfo> getWaitTimeAdList(RequestBody requestBody){
        return sMenuService.getWaitTimeADList(requestBody)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<WaitTimeMenuInfo> getWaitTimeMenuList(RequestBody requestBody){
        return sMenuService.getWaitTimeMenuList(requestBody)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
