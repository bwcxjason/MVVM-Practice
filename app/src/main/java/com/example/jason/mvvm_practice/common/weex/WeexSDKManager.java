package com.example.jason.mvvm_practice.common.weex;

import android.app.Application;

import com.alibaba.weex.plugin.loader.WeexPluginContainer;
import com.example.jason.mvvm_practice.common.weex.extend.ImageAdapter;
import com.example.jason.mvvm_practice.common.weex.extend.WXEventModule;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

public class WeexSDKManager {

    public static final String MODULE_EVENT = "event";

    public static void init(Application application) {
        WXSDKEngine.initialize(application,
                new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build()
        );
        try {
            WXSDKEngine.registerModule(MODULE_EVENT, WXEventModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
        WeexPluginContainer.loadAll(application);
    }

}
