package com.example.jason.mvvm_practice.business.calculation;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.example.jason.mvvm_practice.common.util.CalculationUtil;

public class CalculateViewModel extends BaseObservable {

    public ObservableInt price = new ObservableInt();
    public ObservableInt number = new ObservableInt();
    public ObservableBoolean isCouponsEnabled = new ObservableBoolean(false);
    public ObservableInt couponValue = new ObservableInt(20);
    public ObservableInt totalPrice = new ObservableInt();
    private int discount = 0;
    ObservableField order;

    public CalculateViewModel() {
        order = new ObservableField<>(price, number);
        order.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                showTotalPrice();
            }
        });

        isCouponsEnabled.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (isCouponsEnabled.get()) {
                    discount = couponValue.get();
                } else {
                    discount = 0;
                }

                showTotalPrice();
            }
        });
    }

    private void showTotalPrice() {
        totalPrice.set(CalculationUtil.calculateTotalPrice(price.get(), number.get(), discount));
    }

}
