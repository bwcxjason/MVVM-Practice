package com.example.jason.mvvm_practice.common.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private static final int DEFAULT_TIMEOUT = 10;

    private static Retrofit retrofit;

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://qa.hjb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                //create() by default means all reactive types execute their requests synchronously,
                //you can use subscribeOn() on the returned reactive type with a Scheduler of your choice
                //createAsync() means creating the factory which will use OkHttp's internal pool
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(okHttpClient)
                .build();
    }

    private RetrofitProvider() {
    }

    public static Retrofit getInstance() {
        return retrofit;
    }

}
