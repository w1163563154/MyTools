package com.xiaoyu.mytools.MyHttp;

import android.support.v7.util.SortedList;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 网络框架  添加依赖
 *
 * compile 'com.squareup.retrofit2:retrofit:2.5.0'
 compile 'com.squareup.retrofit2:converter-gson:2.0.2'
 compile 'com.squareup.retrofit2:converter-scalars:2.0.0'
 *
 * Created by yfb on 2019/1/29.
 */

public class MyHttpUtils {

    public static void HttpGet(String baseUrl, String url, Map<String,String> map, Callback<String> callback){
        Retrofit builder = new Retrofit.Builder()
                //指定baseurl，这里有坑，最后后缀出带着“/”
                .baseUrl(baseUrl)
                //设置内容格式,这种对应的数据返回值是String类型
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                //定义client类型
                .client(new OkHttpClient())
                //创建
                .build();

        //通过retrofit和定义的有网络访问方法的接口关联
        HttpApi Get = builder.create(HttpApi.class);
        //在这里又重新设定了一下baidu的地址，是因为Retrofit要求传入具体，如果是决定路径的话，路径会将baseUrl覆盖掉
        Call<String> call = Get.HttpGet(url);
        //执行异步请求
        call.enqueue(callback);
    }


    public static void HttpPost(String token, String baseUrl, Map<String, Object> map, Callback<String> callback){

       // map.put("Device","Android");
       // map.put("MerNo", AppConfig.MERNO);
        Retrofit builder = new Retrofit.Builder()
                //指定baseurl，这里有坑，最后后缀出带着“/”
                .baseUrl(baseUrl)
                //设置内容格式,这种对应的数据返回值是String类型
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())  //这点需要注意解析器
                //定义client类型
                .client(new OkHttpClient())
                //创建
                .build();

        //通过retrofit和定义的有网络访问方法的接口关联
        HttpApi Get = builder.create(HttpApi.class);
        //在这里又重新设定了一下baidu的地址，是因为Retrofit要求传入具体，如果是决定路径的话，路径会将baseUrl覆盖掉
        Call<String> call = Get.HttpPost(token,baseUrl,map);
        //执行异步请求
        call.enqueue(callback);
    }


}

interface HttpApi{
    @GET
    Call<String> HttpGet(@Url String url);

    @FormUrlEncoded

    @POST
    Call<String> HttpPost(@Header("Token")String token, @Url String url, @FieldMap Map<String, Object> map);
}
