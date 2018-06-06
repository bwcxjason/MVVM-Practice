package com.example.jason.mvvm_practice.business.homepage.activity;

import android.arch.lifecycle.ViewModel;

public class HomePageViewModel extends ViewModel {

    public interface Navigator {
        void setCurrentFragmentByIndex(int index);
    }

    private Navigator mNavigator;

    public void setNavigator(Navigator navigator) {
        this.mNavigator = navigator;
    }

    public void setCurrentFragmentByIndex(int index) {
        if (mNavigator != null) {
            mNavigator.setCurrentFragmentByIndex(index);
        }
    }

}
