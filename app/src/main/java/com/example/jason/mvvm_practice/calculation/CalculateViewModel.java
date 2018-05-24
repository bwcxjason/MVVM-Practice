package com.example.jason.mvvm_practice.calculation;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

public class CalculateViewModel extends BaseObservable {

    public ObservableInt price = new ObservableInt();
    public ObservableInt number = new ObservableInt();
    public ObservableBoolean isCouponsEnabled = new ObservableBoolean(false);
    public ObservableInt couponValue = new ObservableInt(20);
    public ObservableInt totalPrice = new ObservableInt();
    private int discount = 0;

    public CalculateViewModel() {
        price.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                calculateTotalPrice();
            }
        });

        number.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                calculateTotalPrice();
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

                calculateTotalPrice();
            }
        });
    }

    private void calculateTotalPrice() {
        totalPrice.set(price.get() * number.get() - discount);
    }

}
