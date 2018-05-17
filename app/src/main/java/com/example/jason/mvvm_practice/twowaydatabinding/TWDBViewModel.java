package com.example.jason.mvvm_practice.twowaydatabinding;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class TWDBViewModel extends ViewModel {

    public ObservableInt age = new ObservableInt();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean isMarried = new ObservableBoolean(false);
    public MutableLiveData<String> result = new MutableLiveData<>();

    public void submit() {
        result.setValue("{age:" + age.get() + ",name:" + name.get() + ",isMarried:" + isMarried.get() + "}");
    }

    public void update() {
        age.set(18);
        name.set("jason");
        isMarried.set(true);
    }

    public LiveData<String> getResult() {
        return result;
    }

}
