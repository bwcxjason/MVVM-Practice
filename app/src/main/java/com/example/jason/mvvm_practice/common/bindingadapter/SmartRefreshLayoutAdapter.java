package com.example.jason.mvvm_practice.common.bindingadapter;

import android.databinding.BindingAdapter;

import com.example.jason.mvvm_practice.common.command.Command;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class SmartRefreshLayoutAdapter {

    @BindingAdapter({"onRefresh"})
    public static void setOnRefreshCommand(SmartRefreshLayout refreshLayout, Command command) {
        if (command != null) {
            refreshLayout.setOnRefreshListener(refreshLayout1 -> command.execute());
        }
    }

    @BindingAdapter({"onLoadMore"})
    public static void setOnLoadMoreCommand(SmartRefreshLayout refreshLayout, Command command) {
        if (command != null) {
            refreshLayout.setOnLoadMoreListener(refreshLayout1 -> command.execute());
        }
    }

}
