package com.example.jason.mvvm_practice.common.weex.activity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jason.mvvm_practice.R;
import com.taobao.weex.WXRenderErrorCode;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.component.NestedContainer;


public class WXPageActivity extends AbsWeexActivity implements
        WXSDKInstance.NestedInstanceInterceptor {

    private static final String TAG = "WXPageActivity";
    private ProgressBar mProgressBar;
    private TextView mTipView;

    @Override
    public void onCreateNestInstance(WXSDKInstance instance, NestedContainer container) {
        Log.d(TAG, "Nested Instance created.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpage);
        mContainer = findViewById(R.id.container);
        mProgressBar = findViewById(R.id.progress);
        mTipView = findViewById(R.id.index_tip);

        mUri = Uri.parse("http://192.168.2.71:8081/dist/index.js");
        loadUrl(mUri.toString());
    }

    protected void preRenderPage() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        mProgressBar.setVisibility(View.GONE);
        mTipView.setVisibility(View.GONE);
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        mProgressBar.setVisibility(View.GONE);
        mTipView.setVisibility(View.VISIBLE);
        if (TextUtils.equals(errCode, WXRenderErrorCode.WX_NETWORK_ERROR)) {
            mTipView.setText(R.string.index_tip);
        } else {
            mTipView.setText("render error:" + errCode);
        }
    }

}
