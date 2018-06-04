package com.example.jason.mvvm_practice.common.retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import retrofit2.Call;

public class RetrofitToCommonProxyAdapterFactory {

    public static <T> T getProxyInstance(Class<T> targetInterface) {
        TargetRetrofitService annotation = targetInterface.getAnnotation(TargetRetrofitService.class);
        Class<?> targetRetrofitServiceClass = annotation.value();
        Object targetRetrofitService = RetrofitProvider.getInstance().create(targetRetrofitServiceClass);

        InvocationHandler invocationHandler = newInvocationHandler(targetRetrofitService);
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(RetrofitToCommonProxyAdapterFactory.class.getClassLoader(), new Class[]{targetInterface}, invocationHandler);
    }

    private static InvocationHandler newInvocationHandler(final Object target) {
        return new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Method targetMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
                Call<?> call = (Call<?>) targetMethod.invoke(target, args);
                return new RetrofitCallToListenableFutureAdapter<>(call);
            }
        };
    }

}
