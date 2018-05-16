package com.example.jason.mvvm_practice.twowaydatabinding;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
    }


}
