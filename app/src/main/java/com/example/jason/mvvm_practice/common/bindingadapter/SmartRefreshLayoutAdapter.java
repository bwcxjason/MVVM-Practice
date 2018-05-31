package com.example.jason.mvvm_practice.common.bindingadapter;

import android.databinding.BindingAdapter;

import com.example.jason.mvvm_practice.common.command.ReplyCommand;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class SmartRefreshLayoutAdapter {

    @BindingAdapter({"onRefreshCommand"})
    public static void setOnRefreshCommand(SmartRefreshLayout refreshLayout, ReplyCommand replyCommand) {
        if (replyCommand != null) {
            refreshLayout.setOnRefreshListener(refreshLayout1 -> replyCommand.action());
        }
    }

    @BindingAdapter({"onLoadMoreCommand"})
    public static void setOnLoadMoreCommand(SmartRefreshLayout refreshLayout, ReplyCommand replyCommand) {
        if (replyCommand != null) {
            refreshLayout.setOnLoadMoreListener(refreshLayout1 -> replyCommand.action());
        }
    }

}
