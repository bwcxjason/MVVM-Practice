package com.example.jason.mvvm_practice.calculation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.databinding.ActivityCalculationBinding;


public class CalculateActivity extends AppCompatActivity {

    private ActivityCalculationBinding mCalculationBinding;
    private CalculateViewModel mCalculateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCalculationBinding = DataBindingUtil.setContentView(this, R.layout.activity_calculation);
        mCalculateViewModel = new CalculateViewModel();
        mCalculationBinding.setCalculateViewModel(mCalculateViewModel);
    }
}
