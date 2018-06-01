package com.example.jason.mvvm_practice.business.articles.service;

import com.example.jason.mvvm_practice.common.retrofit.RetrofitProvider;

public class ApiService {

    public static <T> T of(Class<T> clazz) {
        return RetrofitProvider.getInstance().create(clazz);
    }

}
