package com.example.jason.mvvm_practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jason.mvvm_practice.business.calculation.CalculateActivity;
import com.example.jason.mvvm_practice.business.articles.activity.ArticlesActivity;
import com.example.jason.mvvm_practice.business.homepage.activity.HomePageActivity;
import com.example.jason.mvvm_practice.business.twowaydatabinding.TWDBActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTWDBBtn(View v) {
        Intent intent = new Intent(this, TWDBActivity.class);
        startActivity(intent);
    }

    public void onClickTaskBtn(View v) {
        Intent intent = new Intent(this, ArticlesActivity.class);
        startActivity(intent);
    }

    public void onClickCalculateBtn(View v) {
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    public void onClickHomePageBtn(View v) {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

}
