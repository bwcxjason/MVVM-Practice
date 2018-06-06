package com.example.jason.mvvm_practice.business.homepage.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.business.articles.fragment.ArticleFragment;
import com.example.jason.mvvm_practice.databinding.ActivityHomepageBinding;

public class HomePageActivity extends AppCompatActivity implements HomePageViewModel.Navigator {

    private ActivityHomepageBinding mBinding;
    private Fragment[] mFragments = new Fragment[]{new ArticleFragment(), new ArticleFragment()};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_homepage);
        HomePageViewModel viewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
        viewModel.setNavigator(this);
        mBinding.setViewModel(viewModel);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.length; i++) {
            Fragment fragment = mFragments[i];
            transaction.add(R.id.containerLayout, fragment);
        }
        transaction.commit();
        setCurrentFragmentByIndex(0);

    }

    @Override
    public void setCurrentFragmentByIndex(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.length; i++) {
            Fragment fragment = mFragments[i];
            if (i == index) {
                transaction.show(fragment);
            } else {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

}
