package com.example.jason.mvvm_practice.common.async;

public interface ListenableFuture<T> {

    void addCallback(SuccessCallback<T> successCallback, FailureCallback failureCallback);

}
