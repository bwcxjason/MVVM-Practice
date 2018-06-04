package com.example.jason.mvvm_practice.common.retrofit;

import com.example.jason.mvvm_practice.common.async.FailureCallback;
import com.example.jason.mvvm_practice.common.async.ListenableFuture;
import com.example.jason.mvvm_practice.common.async.SuccessCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallToListenableFutureAdapter<T> implements ListenableFuture<T> {

    private final Call<T> retrofitCall;

    public RetrofitCallToListenableFutureAdapter(Call<T> retrofitCall) {
        this.retrofitCall = retrofitCall;
    }

    @Override
    public void addCallback(SuccessCallback<T> successCallback, FailureCallback failureCallback) {
        retrofitCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    successCallback.onSuccess(response.body());
                } else {
                    RemoteServerException exception = new RemoteServerException(response.code(), response.message());
                    failureCallback.onFailure(exception);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                failureCallback.onFailure(t);
            }
        });
    }
}
