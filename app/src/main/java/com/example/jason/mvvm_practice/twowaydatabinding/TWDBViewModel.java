package com.example.jason.mvvm_practice.twowaydatabinding;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class TWDBViewModel extends ViewModel {

    public ObservableInt age = new ObservableInt();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean isMale = new ObservableBoolean(false);

}
