package com.example.jason.mvvm_practice.common.bindingadapter;

import android.databinding.BindingAdapter;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class SmartRefreshLayoutAdapter {

    @BindingAdapter({"onRefreshCommand"})
    public static void setOnRefreshCommand(SmartRefreshLayout refreshLayout, final Runnable refreshRunnable) {
        if (refreshRunnable != null) {
            refreshLayout.setOnRefreshListener(refreshLayout1 -> refreshRunnable.run());
        }
    }

    @BindingAdapter({"onLoadMoreCommand"})
    public static void setOnLoadMoreCommand(SmartRefreshLayout refreshLayout, final Runnable loadMoreRunnable) {
        if (loadMoreRunnable != null) {
            refreshLayout.setOnLoadMoreListener(refreshLayout1 -> loadMoreRunnable.run());
        }
    }

}
