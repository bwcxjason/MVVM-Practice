package com.example.jason.mvvm_practice.common.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter<T> extends ArrayAdapter<T> {

    private List<T> mItems;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mResource;
    private int mVariableId;

    public ListViewAdapter(Context context, int resource, int variableId) {
        this(context, resource, new ArrayList<>(), variableId);
    }

    public ListViewAdapter(Context context, int resource, List<T> items, int variableId) {
        super(context, resource, items);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mResource = resource;
        this.mItems = items;
        this.mVariableId = variableId;
    }

    public void replaceData(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, mResource, parent, false);
        binding.setVariable(mVariableId, mItems.get(position));
        binding.executePendingBindings();
        return binding.getRoot();
    }

}