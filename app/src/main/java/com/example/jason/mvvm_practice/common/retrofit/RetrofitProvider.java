package com.example.jason.mvvm_practice.common.retrofit;

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
                    .baseUrl("http://qa.hjb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
