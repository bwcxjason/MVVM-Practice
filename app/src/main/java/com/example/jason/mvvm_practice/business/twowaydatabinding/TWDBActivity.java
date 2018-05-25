package com.example.jason.mvvm_practice.business.twowaydatabinding;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.databinding.ActivityTwdbBinding;

public class TWDBActivity extends AppCompatActivity {

    private ActivityTwdbBinding mTwdbBinding;
    private TWDBViewModel mTwdbViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTwdbBinding = DataBindingUtil.setContentView(this, R.layout.activity_twdb);
        mTwdbViewModel = ViewModelProviders.of(this).get(TWDBViewModel.class);
        mTwdbBinding.setTwdbViewModel(mTwdbViewModel);

        setUpResultListener();
    }

    private void setUpResultListener() {
        mTwdbViewModel.getResult().observe(this, this::onResultChanged);
    }

    private void onResultChanged(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

}
