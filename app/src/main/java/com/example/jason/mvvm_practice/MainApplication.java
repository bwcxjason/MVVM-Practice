package com.example.jason.mvvm_practice;

import android.app.Application;

import com.example.jason.mvvm_practice.common.weex.WeexSDKManager;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        WeexSDKManager.init(this);
    }

}
