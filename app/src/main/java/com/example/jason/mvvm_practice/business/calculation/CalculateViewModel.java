package com.example.jason.mvvm_practice.business.calculation;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;

public class CalculateViewModel extends BaseObservable {

    public ObservableField<Drawable> test = new ObservableField<>();
    public ObservableInt price = new ObservableInt();
    public ObservableInt number = new ObservableInt();
    public ObservableBoolean isCouponsEnabled = new ObservableBoolean(false);
    public ObservableInt couponValue = new ObservableInt(20);
    public ObservableInt totalPrice = new ObservableInt();
    private int discount = 0;

    public CalculateViewModel() {
        ObservableField order = new ObservableField<>(price, number);
        order.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
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
