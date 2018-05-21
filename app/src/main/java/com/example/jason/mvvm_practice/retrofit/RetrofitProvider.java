package com.example.jason.mvvm_practice.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dingzhihu on 15/5/7.
 */
public class RetrofitProvider {

    private static Retrofit retrofit;

    private RetrofitProvider() {
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://120.76.205.241:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}