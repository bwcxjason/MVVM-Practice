package com.example.jason.mvvm_practice.common.retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import retrofit2.Call;

public class ProxyFactory {

    private Call call;

    private OnFinishedListener mListener;

    private Class clazz;

    private Class targetClazz;

    public ProxyFactory(OnFinishedListener listener, Class clazz, Class targetClazz) {
        this.mListener = listener;
        this.clazz = clazz;
        this.targetClazz = targetClazz;
    }

    public void create() {
        Object object = RetrofitProvider.getInstance().create(targetClazz);
        Method[] clazzes = object.getClass().getMethods();

        InvocationHandler invocationHandler = new ProxyInvocationHandler(clazz);
        Proxy.newProxyInstance(clazz.getClass().getClassLoader(), clazz.getClass().getInterfaces(), invocationHandler);

    }

}
