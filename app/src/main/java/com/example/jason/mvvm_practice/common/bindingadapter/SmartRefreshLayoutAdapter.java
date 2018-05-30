package com.example.jason.mvvm_practice.common.bindingadapter;

import android.databinding.BindingAdapter;

import com.example.jason.mvvm_practice.common.command.ReplyAction;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class SmartRefreshLayoutAdapter {

    @BindingAdapter({"onRefreshCommand"})
    public static void setOnRefreshCommand(SmartRefreshLayout refreshLayout, ReplyAction replyAction) {
        if (replyAction != null) {
            refreshLayout.setOnRefreshListener(refreshLayout1 -> replyAction.execute());
        }
    }

    @BindingAdapter({"onLoadMoreCommand"})
    public static void setOnLoadMoreCommand(SmartRefreshLayout refreshLayout, ReplyAction replyAction) {
        if (replyAction != null) {
            refreshLayout.setOnLoadMoreListener(refreshLayout1 -> replyAction.execute());
        }
    }

}
